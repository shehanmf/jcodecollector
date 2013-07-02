/*
 * Copyright 2006-2013 Alessandro Cocco.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jcodecollector.data;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.swing.JOptionPane;

import jcodecollector.common.bean.Snippet;
import jcodecollector.common.bean.Tag;
import jcodecollector.data.settings.ApplicationSettings;
import jcodecollector.io.PackageManager;
import jcodecollector.util.ApplicationConstants;

public class DBMS {
    private static final String DBMS_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

    private void init() {
        String connectionURL = "jdbc:derby:";
        String databasePath = ApplicationSettings.getInstance().getDatabasePath() + "ecompilerLab1";
        connectionURL += databasePath;
        connectionURL += File.separator + ApplicationSettings.DB_DIR_NAME;

        try {
            Class.forName(DBMS_DRIVER);
        } catch (ClassNotFoundException ex) {
            String text = "<html><b>An error occurred while loading dbms driver.</b><br><br><font size=-1>";
            text += "Click OK and try again.";
            text += "</font></html>";
            JOptionPane.showMessageDialog(null, text, "", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        /* Se le cartelle che portano alla cartella del database non esistono le
         * creo: prima di creare il database ? importante le cartelle siano
         * pronte in quanto derby non le crea in automatico */
        File database = new File(databasePath);
        if (!database.exists()) {
            if (!database.mkdirs()) {
                /* se non riesco a creare le cartelle do un messaggio all'utente
                 * ed esco: non dovrebbe mai fallire, a meno che non ci sia un
                 * problema di permessi */
                String text = "<html><b>An error occurred while preparing database folder.</b><br><br><font size=-1>";
                text += "Click OK and try again.";
                text += "</font></html>";
                JOptionPane.showMessageDialog(null, text, "", JOptionPane.ERROR_MESSAGE);
                System.exit(2);
            }
        }

        connectionURL += ";create=true";
        System.out.println("CONNECTION URL: " + connectionURL);

        try {
            // effetto la connessione al database
            connection = DriverManager.getConnection(connectionURL);
        } catch (SQLException ex) {
            String text = "<html><b>Cannot start jCodeCollector because an error occurred.</b><br><br><font size=-1>";
            String message = ex.getMessage();
            System.out.println(message);
            if (message.contains("not found")) {
                text += "JCODECOLLECTOR_DB folder cannot be found in <i>"
                        + ApplicationSettings.getInstance().getDatabasePath()
                        + "jCodeCollector</i>";
            } else {
                text += "Only one client at time can access to the database.";
            }
            text += "</font></html>";

            JOptionPane.showMessageDialog(null, text, "", JOptionPane.ERROR_MESSAGE);
            System.exit(3);
        }

        /* Creo le tabelle SNIPPETS e TAGS e inserisco gli snippet di esempio.
         * Se createTables() restituisce false le tabelle sono state create
         * durante una precedente esecuzione. */
        if (createTables()) {
            insertDefaultSnippets();
        }
    }

    /** Inserisce nel database gli snippet di esempio. */
    private void insertDefaultSnippets() {
        try {
            ArrayList<Snippet> snippets = PackageManager.readPackage(new File(("../default_snippets.jccp")));
            // ArrayList<Snippet> snippets = PackageManager.readPackage(new
            // File(DBMS.class.getResource("default_snippets.jccp").getFile()));
            for (Snippet s : snippets) {
                insertNewSnippet(s);
            }
        } catch (Exception ex) {
            System.err.println("cannot find default snippets file");
        }
    }

    public boolean resetConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

        String databasePath = ApplicationSettings.getInstance().getDatabasePath() + "jCodeCollector";
        String connectionURL = "jdbc:derby:";
        connectionURL += databasePath;
        connectionURL += File.separator + ApplicationSettings.DB_DIR_NAME;
        System.out.println("CONNECTION URL: " + connectionURL);

        File databaseDirectory = new File(databasePath);
        if (!databaseDirectory.exists()) {
            if (!databaseDirectory.mkdirs()) {
                System.err.println("error creating dirs");
            }
        }

        try {
            connection = DriverManager.getConnection(connectionURL);
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    protected void finalize() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println("error during finalization: " + ex);
        }
    }

    private boolean createTables() {
        try {
            Statement statement = connection.createStatement();

            // creo la tabella degli snippet
            statement.execute("create table snippets("
                    + "id_snippet integer generated by default as identity primary key, "
                    + "snippet_name varchar(" + ApplicationConstants.SNIPPET_NAME_LENGTH
                    + ") unique not null, snippet_category varchar(" + ApplicationConstants.CATEGORY_LENGTH
                    + ") not null, snippet_code varchar(" + ApplicationConstants.CODE_LENGTH
                    + ") not null, snippet_comment varchar(" + ApplicationConstants.COMMENT_LENGTH
                    + "), snippet_lib varchar(" + ApplicationConstants.SNIPPET_LIB_LENGTH
                    + "), snippet_is_locked integer default 0, syntax_name varchar("
                    + ApplicationConstants.SYNTAX_NAME_LENGTH + "))");

            // creo la tabella dei tag
            statement.execute("create table tags("
                    + "id_snippet integer references snippets(id_snippet) on delete cascade, "
                    + "tag_name varchar(" + ApplicationConstants.TAG_LENGTH + "), "
                    + "primary key (id_snippet, tag_name))");

            return true;
        } catch (SQLException ex) {
            if (ex.getSQLState().equals("X0Y32")) {
                System.out.println("tabled already exists");
            } else {
                // non dovrebbero esserci altri problemi a meno che non ci sia
                // un fallimento del database: in questo caso il problema verra'
                // rilevato poco piu' avanti
            }

            return false;
        }
    }

    /**
     * Returns the ID of the snippet.
     * 
     * @param name The snippet.
     * @return the ID if the snippet is available, -1 otherwise
     */
    public int getSnippetId(String name) {
        String query = "select id_snippet from snippets where snippet_name = ?";
        int id = -1; // -1 = snippet not found

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);

            // get the ID
            id = statement.executeQuery().getInt("id_snippet");
        } catch (SQLException ex) {
            // restituira' -1
        }

        return id;
    }

    /**
     * Restituisce la lista di tutte le categorie presenti nel database.
     * 
     * @return la lista di tutte le categorie presenti nel database
     */
    public ArrayList<String> getCategories() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "select distinct snippet_category from snippets";
        ArrayList<String> array = null;

        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            array = new ArrayList<String>();

            while (resultSet.next()) {
                array.add(resultSet.getString("snippet_category"));
            }
        } catch (SQLException ex) {
            array = null;
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        Collections.sort(array);

        // dopo l'ordinamento metto "Uncategorized" alla fine
        if (array.contains("Uncategorized")) {
            array.remove("Uncategorized");
            array.add("Uncategorized");
        }

        return array;
    }

    /**
     * Restituisce la categoria a cui appartiene lo snippet indicato.
     * 
     * @param snippet Il nome dello snippet di cui cercare la categoria.
     * @return la categoria a cui appartiene lo snippet indicato
     */
    public String getCategoryOf(String snippet) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String category = null;

        try {
            statement = connection.prepareStatement("select " + "snippet_category from snippets where snippet_name = ?");
            statement.setString(1, snippet);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                category = resultSet.getString("snippet_category");
            }
        } catch (SQLException ex) {
            category = null;
        }

        return category;
    }

    /**
     * Inserts a new snippet into the database.
     * 
     * @param newSnippet Lo snippet da inserire (o aggiornare)
     * @return <code>true</code> se l'inserimento/aggiornamento viene eseguito
     *         con successo, <code>false</code> altrimenti
     */
    public boolean insertNewSnippet(Snippet newSnippet) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query_insert = "insert into snippets values(DEFAULT, ?, ?, ?, ?, ?, ?, ?)";
        String query_selectID = "select id_snippet from snippets where snippet_name = ?";
        String query_insertTag = "insert into tags values ";

        boolean success;

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(query_insert);
            statement.setString(1, newSnippet.getName());
            statement.setString(2, newSnippet.getCategory());
            statement.setString(3, newSnippet.getCode());
            statement.setString(4, newSnippet.getComment());
            statement.setString(5, toLibraryString(newSnippet.getLibIDs()));
            statement.setInt(6, newSnippet.isLocked() ? 1 : 0);
            statement.setString(7, newSnippet.getSyntax());
            statement.execute();
            statement = connection.prepareStatement(query_selectID);
            statement.setString(1, newSnippet.getName());
            resultSet = statement.executeQuery();
            resultSet.next();
            final int ID_SNIPPET = resultSet.getInt("id_snippet");

            // inserimento TAG
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i < newSnippet.getTags().length - 1; i++) {
                temp.append("(" + ID_SNIPPET + ", ?), ");
            }
            temp.append("(" + ID_SNIPPET + ", ?)");

            statement = connection.prepareStatement(query_insertTag + temp);
            for (int i = 0; i < newSnippet.getTags().length; i++) {
                statement.setString(i + 1, newSnippet.getTags()[i]);
            }

            statement.execute();
            connection.commit();
            success = true;
        } catch (SQLException ex) {
            success = false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                success = false;
            }
        }

        return success;
    }


    /**
     *
     * @param libIDs
     * @return
     */
    private String toLibraryString(ArrayList<String> libIDs)
    {
        StringBuffer buf = new StringBuffer();

        if(libIDs != null && libIDs.size() > 0)
        {
            boolean isFirst = true;
            for (String id :libIDs)
            {
                if(!isFirst)
                {
                    buf.append(",");
                }
                buf.append(id);
                isFirst = false;
            }
        }

        return buf.toString();
    }

    @SuppressWarnings("unchecked")
    public boolean updateSnippet(Snippet oldSnippet, Snippet newSnippet) {
        assert oldSnippet != null : "oldSnippet must be != null";

        try {
            // disabilito l'auto-commit, inizio una transazione.
            connection.setAutoCommit(false);


            // Preparo la query.
            PreparedStatement preparedStatement = connection.prepareStatement("update snippets "
                    + "set snippet_category = ?, "
                    + "snippet_name = ?, "
                    + "snippet_code = ?, "
                    + "snippet_comment = ?, "
                    + "snippet_is_locked = ?, "
                    + "snippets.syntax_name = ?, "
                    + "snippet_lib = ? "
                    + "where snippet_name = ?");
            preparedStatement.setString(1, newSnippet.getCategory());
            preparedStatement.setString(2, newSnippet.getName());
            preparedStatement.setString(3, newSnippet.getCode());
            preparedStatement.setString(4, newSnippet.getComment());
            preparedStatement.setInt(5, newSnippet.isLocked() ? 1 : 0);
            preparedStatement.setString(6, newSnippet.getSyntax());
            preparedStatement.setString(7, toLibraryString(newSnippet.getLibIDs()));
            preparedStatement.setString(8, oldSnippet.getName());
            preparedStatement.execute();

            // Inserisco in un albero i tag da eliminare.
            TreeSet<String> tagsToDelete = new TreeSet<String>();
            for (String temp : oldSnippet.getTags()) {
                tagsToDelete.add(temp);
            }

            // Inserisco in un nuovo albero i tag da aggiungere.
            TreeSet<String> tagsToAdd = new TreeSet<String>();
            for (String temp : newSnippet.getTags()) {
                tagsToAdd.add(temp);
            }

            // Faccio una copia del set dei vecchi tag, servira' per
            // l'operazione inversa a quella che sto per fare.
            TreeSet<String> oldTagsClone = (TreeSet<String>) tagsToDelete.clone();

            // Dall'albero dei tag da cancellare tolgo i tag da aggiungere.
            // Quelli che restano sono i tag effettivamente da cancellare
            tagsToDelete.removeAll(tagsToAdd);

            // Dall'albero dei tag da aggiungere rimuovo quelli gia' presenti.
            tagsToAdd.removeAll(oldTagsClone);

            // Rimuovo i tag non piu' presenti.
            for (String tag : tagsToDelete) {
                preparedStatement = connection.prepareStatement("delete from tags "
                        + "where tag_name=? and tags.id_snippet = "
                        + "(select snippets.id_snippet "
                        + "from snippets where snippets.snippet_name=?)");
                preparedStatement.setString(1, tag);
                preparedStatement.setString(2, newSnippet.getName());
                preparedStatement.executeUpdate();
            }

            // Inserisco i nuovi tag.
            for (String tag : tagsToAdd) {
                preparedStatement = connection.prepareStatement("insert into tags values("
                        + "(select snippets.id_snippet "
                        + "from snippets where snippets.snippet_name = ?), ?)");
                preparedStatement.setString(1, newSnippet.getName());
                preparedStatement.setString(2, tag);
                preparedStatement.executeUpdate();
            }

            // Effettuo il commit, transazione terminata.
            connection.commit();

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                // ripristino il commit automatico.
                connection.setAutoCommit(true);
            } catch (Exception ex) {
                return false;
            }
        }
    }

    /**
     * Returns all snippet of category.
     * 
     * @param category The category.
     * @return an <code>ArrayList</code> of all snippet of category
     */
    public ArrayList<String> getSnippetsNames(String category) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("select snippet_name from snippets where snippet_category = ?");
            preparedStatement.setString(1, category);

            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<String> array = new ArrayList<String>();

            while (resultSet.next()) {
                array.add(resultSet.getString("snippet_name"));
            }

            return array;
        } catch (SQLException ex) {
            return null;
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Restituisce tutti gli snippet presenti nel database.
     * 
     * @return tutti gli snippet presenti nel database
     */
    public ArrayList<Snippet> getAllSnippets() {
        ArrayList<String> categories = getCategories();
        ArrayList<Snippet> snippets = new ArrayList<Snippet>();

        for (String category : categories) {
            snippets.addAll(getSnippets(category));
        }

        return snippets;
    }

    public Snippet getSnippet(String name) {
        PreparedStatement statement = null;

        try {
            // Ricerco lo snippet dal nome passato.
            statement = connection
                    .prepareStatement("select id_snippet, snippet_category, snippet_code, snippet_comment, snippet_is_locked, snippet_lib, syntax_name "
                            + "from snippets where snippet_name = ?");
            statement.setString(1, name);

            // eseguo la query ed ottengo un unico risultato (clausola unique)
            ResultSet resultSet = statement.executeQuery();
            boolean next = resultSet.next();

            if (!next) {
                statement = connection.prepareStatement("select id_snippet, snippet_category, snippet_code, snippet_comment, snippet_lib , snippet_is_locked "
                        + "from snippets where snippet_name = ?");
                statement.setString(1, name);

                resultSet = statement.executeQuery();
                resultSet.next();
            }

            int id = resultSet.getInt("id_snippet");
            String category = resultSet.getString("snippet_category");
            String code = resultSet.getString("snippet_code");
            String comment = resultSet.getString("snippet_comment");
            String libIdString  = resultSet.getString("snippet_lib");
            String syntax = next ? resultSet.getString("syntax_name") : "";
            boolean locked = resultSet.getInt("snippet_is_locked") == 1;

            // Seleziono i tag di quello snippet.
            statement = connection.prepareStatement("select tag_name from tags where id_snippet = ?");
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            ArrayList<String> tags = new ArrayList<String>();
            while (resultSet.next()) {
                tags.add(resultSet.getString("tag_name"));
            }

            ArrayList<String> libIds = new ArrayList<String>();
            if(libIdString != null)
            {
                StringTokenizer tokenizer = new StringTokenizer(libIdString.trim(), ",");
                int libLength = tokenizer.countTokens();

                for (int i = 0; i < libLength; i++) {
                    libIds.add(tokenizer.nextToken().trim());
                }
            }


            return new Snippet(id, category, name, tags.toArray(new String[] { "" }), code, comment, syntax, locked,libIds.toArray(new String[] {}));
        } catch (SQLException ex) {
            System.err.println(ex);
            ex.printStackTrace();

            return null;
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Restituisce tutti gli {@link Snippet} che appartengono alla categoria
     * indicata.
     * 
     * @param category La categoria di cui restituire gli snippet.
     * @return un {@link ArrayList} contentente tutti gli {@link Snippet} che
     *         appartengono alla categoria indicata
     */
    public ArrayList<Snippet> getSnippets(String category) {
        PreparedStatement statement = null;
        ResultSet snippets = null;
        ResultSet tags = null;
        String select_snippets_query = "select id_snippet, snippet_category, snippet_name, "
                + "snippet_code, snippet_comment, snippet_is_locked, syntax_name ,snippet_lib"
                + "from snippets where snippet_category = ?";
        String select_tags_query = "select tag_name from tags where id_snippet = ?";
        ArrayList<Snippet> array = null;

        try {
            statement = connection.prepareStatement(select_snippets_query);
            statement.setString(1, category);

            // richiedo tutti gli snippet della categoria indicata
            snippets = statement.executeQuery();

            array = new ArrayList<Snippet>();
            while (snippets.next()) {
                // scorro gli snippet letti e da ognuno estraggo i tag
                // dall'altra tabella
                statement = connection.prepareStatement(select_tags_query);
                statement.setInt(1, snippets.getInt("id_snippet"));

                // ottengo i tag
                tags = statement.executeQuery();

                // uso i risultati delle due interrogazioni per istanziare uno
                // snippet completo
                Snippet snippet = makeSnippetBean(snippets, tags);
                if (snippet != null) {
                    array.add(snippet);
                }
            }
        } catch (SQLException ex) {
            array = null;
        }

        return array;
    }

    private Snippet makeSnippetBean(ResultSet snippet, ResultSet tags) {
        Snippet newSnippet = null;

        try {
            ArrayList<String> array = new ArrayList<String>();
            while (tags.next()) {
                array.add(tags.getString("tag_name").trim());
            }

            int id_snippet = snippet.getInt("id_snippet");
            String snippet_category = snippet.getString("snippet_category");
            String snippet_name = snippet.getString("snippet_name");
            String snippet_code = snippet.getString("snippet_code");
            String snippet_comment = snippet.getString("snippet_comment");
            boolean snippet_is_locked = snippet.getInt("snippet_is_locked") == 1;
            String syntax_name = snippet.getString("syntax_name");
            String snippet_lib = snippet.getString("snippet_lib");


            StringTokenizer tokenizer = new StringTokenizer(snippet_lib.trim(), ",");
            String []libs = new String[tokenizer.countTokens()];

            for (int i = 0; i < libs.length; i++) {
                libs[i] = tokenizer.nextToken().trim();
            }


            newSnippet = new Snippet(id_snippet, snippet_category, snippet_name,
                    array.toArray(new String[] {}), snippet_code, snippet_comment,syntax_name, snippet_is_locked,libs);
        } catch (SQLException ex) {
            newSnippet = null;
        }

        return newSnippet;
    }

    /**
     * Elimina dal database lo snippet indicato.
     * 
     * @param name Il nome (primary key) dello snippet da eliminare.
     * @return <code>true</code> se l'operazione va a buon fine,
     *         <code>false</code> altrimenti
     */
    public boolean removeSnippet(String name) {
        String query = "delete from snippets where snippet_name = ?";
        PreparedStatement statement = null;
        boolean success;

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.execute();
            success = true;
        } catch (SQLException ex) {
            success = false;
        }

        return success;
    }

    /**
     * Elimina dal database gli snippet indicati.
     * 
     * @param names I nomi degli snippet da rimuovere.
     * @return <code>true</code> se l'operazione va a buon fine,
     *         <code>false</code> altrimenti
     */
    public boolean removeSnippets(ArrayList<String> names) {
        boolean success;

        try {
            connection.setAutoCommit(false);
            for (String snippet : names) {
                removeSnippet(snippet);
            }
            connection.commit();
            success = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            success = false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return success;
    }

    public boolean renameCategory(String oldName, String newName) {
        String query = "update snippets set snippet_category = ? where snippet_category = ?";
        PreparedStatement statement = null;
        boolean success;

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, newName);
            statement.setString(2, oldName);
            statement.execute();
            success = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            success = false;
        }

        return success;
    }

    public boolean renameCategoryOf(ArrayList<String> snippets, String category) {
        String query = "update snippets set snippet_category = ? where snippet_name = ?";
        PreparedStatement statement = null;
        boolean success;

        try {
            connection.setAutoCommit(false);

            for (String name : snippets) {
                statement = connection.prepareStatement(query);
                statement.setString(1, category);
                statement.setString(2, name);
                statement.execute();
            }

            connection.commit();
            success = true;
        } catch (SQLException ex) {
            success = false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return success;
    }

    public boolean removeCategory(String name) {
        String query = "delete from snippets where snippet_category = ?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    // /**
    // * Inserisce nel database un nuovo stile di colorazione sintattica.
    // *
    // * @param syntax Lo stile di colorazione sintattica da inserire.
    // * @return <code>true</code> se l'operazione e' andata a buon fine,
    // * <code>false</code> altrimenti
    // */
    // public boolean insertNewSyntax(Syntax syntax) {
    // try {
    // String[] keywords = syntax.getKeywords();
    //
    // // inserisco ID e nome stile nella tabella degli stili
    // PreparedStatement preparedStatement =
    // connection.prepareStatement("insert into styles values (DEFAULT, ?)");
    // preparedStatement.setString(1, syntax.getName());
    // preparedStatement.execute();
    //
    // // ottengo l'ID dell'ultimo snippet inserito
    // preparedStatement =
    // connection.prepareStatement("select id_style from styles where style_name = ?");
    // preparedStatement.setString(1, syntax.getName());
    //
    // ResultSet resultSet = preparedStatement.executeQuery();
    // resultSet.next();
    //
    // final int ID_STYLE = resultSet.getInt("id_style");
    //
    // StringBuilder temp = new StringBuilder();
    // for (int i = 0; i < keywords.length; i++) {
    // temp.append("(" + ID_STYLE + ", ?) " + ((i < keywords.length - 1) ?
    // ", " : " "));
    // }
    //
    // // inserisco le parole chiave nella tabelle delle keyword
    // preparedStatement =
    // connection.prepareStatement("insert into keywords values " +
    // temp.toString());
    // for (int i = 0; i < keywords.length; i++) {
    // preparedStatement.setString(i + 1, keywords[i]);
    // }
    //
    // preparedStatement.execute();
    // } catch (SQLException ex) {
    // System.err.println("insertStyle(): " + ex);
    // return false;
    // }
    //
    // return true;
    // throw new UnsupportedOperationException("DO NOT USE THIS METHOD");
    // }

    public int countSnippets() {
        try {
            ResultSet resultSet = connection.prepareStatement("select count(*) from snippets").executeQuery();
            resultSet.next();

            return resultSet.getInt(1);
        } catch (SQLException ex) {
            return -1;
        }
    }

    public int countCategories() {
        try {
            ResultSet resultSet = connection.prepareStatement("select count(distinct snippet_category) from snippets").executeQuery();
            resultSet.next();

            return resultSet.getInt(1);
        } catch (SQLException ex) {
            return -1;
        }
    }

    /**
     * Restituisce tutti i tag degli <code>Snippet</code> della categoria
     * indicata.
     * 
     * @param category la categoria di cui trovare tutti k tag
     * @return un <code>ArrayList</code> contenente tutti k <code>Tag</code>
     *         trovati
     */
    public ArrayList<Tag> getTags(String category) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select distinct tag_name "
                    + "from tags inner join snippets on tags.id_snippet = snippets.id_snippet " + "where snippets.snippet_category = ?");
            preparedStatement.setString(1, category);

            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Tag> tags = new ArrayList<Tag>();
            while (resultSet.next()) {
                tags.add(new Tag(category, resultSet.getString("tag_name")));
            }

            return tags;
        } catch (SQLException ex) {
            System.err.println(ex);
            return null;
        }
    }

    public ArrayList<Tag> getAllTags() {
        ArrayList<String> categories = getCategories();

        ArrayList<Tag> tags = new ArrayList<Tag>();
        for (String category : categories) {
            tags.addAll(getTags(category));
        }

        return tags;
    }

    /**
     * Blocca/sblocca lo snippet indicato.
     * 
     * @param name Lo snippet da bloccare/sbloccare.
     * @param locked <code>true</code> per bloccare lo snippet,
     *        <code>false</code> per sbloccarlo.
     * @return <code>true</code> se l'operazione viene eseguita correttamente,
     *         <code>false</code> altrimenti
     */
    public boolean lockSnippet(String name, boolean locked) {
        String query = "update snippets set snippet_is_locked = ? where snippet_name = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, locked ? 1 : 0);
            preparedStatement.setString(2, name);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean setSyntaxToCategory(String syntax, String category, String selectedSnippet) {
        ArrayList<String> snippets = getSnippetsNames(category);
        snippets.remove(selectedSnippet);
        return setSyntaxToSnippets(syntax, snippets);
    }

    public boolean setSyntaxToSnippets(String syntax, ArrayList<String> snippets) {
        PreparedStatement statement = null;
        StringBuilder query = new StringBuilder("update snippets set syntax_name = ? where snippet_name = ?");
        for (int i = 0; i < snippets.size() - 1; i++) {
            query.append(" or snippet_name = ?");
        }

        try {
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, syntax);
            for (int i = 0; i < snippets.size(); i++) {
                statement.setString(i + 2, snippets.get(i));
            }
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean copyDatabase(String where) {
        if (!where.endsWith("/jCodeCollector")) {
            where += "/jCodeCollector";
        }

        try {
            String backup_query = "CALL SYSCS_UTIL.SYSCS_BACKUP_DATABASE(?)";
            CallableStatement statement = connection.prepareCall(backup_query);
            statement.setString(1, where);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public TreeMap<String, TreeSet<String>> search(String[] keywords, int search) {
        PreparedStatement statement = null;
        StringBuilder query = null;
        ResultSet resultSet = null;
        TreeMap<String, TreeSet<String>> data = null;

        // N e' il numero di opzioni di ricerca attivate dall'utente
        final int n = search;

        // le parti iniziali delle query usate per effettuare la ricerca
        // all'interno del database
        String query_select_code = "select snippet_name, snippet_category from snippets where ";
        String query_select_name = "select snippet_name, snippet_category from snippets where ";
        String query_select_comment = "select  snippet_name, snippet_category from snippets where ";
        String query_select_tags = "select snippet_name, snippet_category from snippets inner join tags on snippets.id_snippet=tags.id_snippet where ";

        query = new StringBuilder();

        if (!ApplicationSettings.getInstance().isSearchCaseSensitive()) {
            for (int i = 0; i < keywords.length; i++) {
                keywords[i] = keywords[i].toUpperCase();
            }
        }

        // cerco all'interno del codice
        if (ApplicationSettings.getInstance().isSearchInCodeEnabled()) {
            search--;

            query.append(query_select_code);

            for (int i = 0; i < keywords.length - 1; i++) {
                query.append(" {$1}snippet_code{$2} like ? or ");
            }

            query.append(" {$1}snippet_code{$2} like ? " + (search > 0 ? "union " : ""));
        }

        // cerco all'interno dei nomi degli snippet
        if (ApplicationSettings.getInstance().isSearchInNameEnabled()) {
            search--;

            query.append(query_select_name);

            for (int i = 0; i < keywords.length - 1; i++) {
                query.append(" {$1}snippet_name{$2} like ? or ");
            }

            query.append(" {$1}snippet_name{$2} like ? " + (search > 0 ? "union " : ""));
        }

        // cerco tra i commenti
        if (ApplicationSettings.getInstance().isSearchInCommentEnabled()) {
            search--;

            query.append(query_select_comment);

            for (int i = 0; i < keywords.length - 1; i++) {
                query.append(" {$1}snippet_comment{$2} like ? or ");
            }

            query.append(" {$1}snippet_comment{$2} like ? " + (search > 0 ? "union " : ""));
        }

        // cerco tra i tag
        if (ApplicationSettings.getInstance().isSearchInTagsEnabled()) {
            search--;

            query.append(query_select_tags);

            for (int j = 0; j < keywords.length - 1; j++) {
                query.append(" {$1}tag_name{$2} = ? or ");
            }

            query.append(" {$1}tag_name{$2} = ?");
        }

        String t = query.toString();
        if (ApplicationSettings.getInstance().isSearchCaseSensitive()) {
            query = new StringBuilder(t.replace("{$1}", "").replace("{$2}", ""));
        } else {
            query = new StringBuilder(t.replace("{$1}", "UPPER(").replace("{$2}", ")"));
        }

        // creo un array abbastanza grande per contenere tutte le chiavi
        ArrayList<String> temp = new ArrayList<String>(n * keywords.length);

        for (int i = 0; i < n; i++) {
            temp.addAll(Arrays.asList(keywords));
        }

        try {
            statement = connection.prepareStatement(query.toString());

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < keywords.length; j++) {
                    String c = i == n - 1 && ApplicationSettings.getInstance().isSearchInTagsEnabled() ? "" : "%";
                    statement.setString(1 + j + i * keywords.length, c + keywords[j] + c);
                }
            }

            resultSet = statement.executeQuery();

            // costruisco una mappa (categoria, elenco snippet) coi risultati
            // della ricerca
            data = new TreeMap<String, TreeSet<String>>();
            while (resultSet.next()) {
                String category = resultSet.getString("snippet_category");
                String snippet = resultSet.getString("snippet_name");

                if (data.containsKey(category)) {
                    data.get(category).add(snippet);
                } else {
                    TreeSet<String> set = new TreeSet<String>();
                    set.add(snippet);
                    data.put(category, set);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            data = null;
        }

        return data;
    }

    public boolean databaseMustBeUpdate() {
        boolean update;

        try {
            ResultSet s = connection.createStatement().executeQuery("select id_style from snippets");
            update = s.next();
        } catch (SQLException ex) {
            // se da errore e' perche' il database e' stato aggiornato e
            // quindi non viene piu' trovata la colonna id_style
            update = false;
        }

        return update;
    }

    public boolean updateDatabase() {
        // se il database e' gia' stato aggiornato non faccio altro
        if (!databaseMustBeUpdate()) {
            return true;
        }

        // query da eseguire per aggiornare il database
        String query_add_column_syntax = "alter table snippets add column syntax_name varchar(50)";
        String query_drop_column_style = "alter table snippets drop column id_style";
        String query_drop_table_keywords = "drop table keywords";
        String query_drop_table_styles = "drop table styles";

        try {
            // eseguo le query in una transazione
            connection.setAutoCommit(false);

            connection.createStatement().execute(query_add_column_syntax);
            connection.createStatement().execute(query_drop_column_style);
            connection.createStatement().execute(query_drop_table_keywords);
            connection.createStatement().execute(query_drop_table_styles);

            return true;
        } catch (SQLException ex) {
            System.err.println("error during altering table");
            ex.printStackTrace();
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex1) {
                System.err.println("cannot re-enabled auto commit");
                ex1.printStackTrace();
            }
        }
    }

    /** The instance of the dbms manager. */
    private static DBMS dbms = new DBMS();

    /** The connection used by the application. */
    private Connection connection = null;

    /**
     * Initializes the dbms manager.
     */
    private DBMS() {
        init();
    }

    /**
     * Returns the istance of the dbms manager.
     * 
     * @return the istance of the dbms manager
     */
    public static DBMS getInstance() {
        return dbms;
    }

}
