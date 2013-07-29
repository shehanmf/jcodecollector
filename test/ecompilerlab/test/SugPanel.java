package ecompilerlab.test; /**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/24/13
 * Time: 11:27 PM
 * To change this template use File | Settings | File Templates.
 */

import javafx.embed.swing.JFXPanel;
import jcodecollector.document.LimitedSyntaxDocument;
import jcodecollector.util.ApplicationConstants;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import prettify.PrettifyParser;
import prettify.theme.ThemeDefault;
import syntaxhighlight.SyntaxHighlighter;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

/**
 * @author Shehan
 */
public class SugPanel extends JFrame
{


  JXTaskPaneContainer tpc = new JXTaskPaneContainer();

  List<JXTaskPane> panels = new ArrayList<JXTaskPane>();

  public static void main(String[] args)
  {

    new SugPanel().setVisible(true);
  }

  /**
   * Creates new form ecompilerlab.test.SugPanel
   */
  public SugPanel()
  {
    initComponents();

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }


  private RSyntaxTextArea getSyntaxComp(String htmlSnippet)
  {
    RSyntaxTextArea textArea = new RSyntaxTextArea();

    textArea
      .setDocument(new LimitedSyntaxDocument(SyntaxConstants.SYNTAX_STYLE_JAVA, ApplicationConstants.CODE_LENGTH));
//    String html = "<html>" + htmlSnippet + "</html>";

    textArea.setRows(5);
    textArea.setText(htmlSnippet);
    return textArea;
  }


  private SyntaxHighlighter getComp3(String html)
  {
    final PrettifyParser parser = new PrettifyParser();

    // initialize the highlighter and use Default theme
    SyntaxHighlighter highlighter = new SyntaxHighlighter(parser, new ThemeDefault());
    // set the line number count from 10 instead of 1
    highlighter.setFirstLine(10);
    // set to highlight line 13, 27, 28, 38, 42, 43 and 53
    highlighter.setHighlightedLineList(Arrays.asList(13, 27, 28, 38, 42, 43, 53));
    highlighter.setContent(html);

    return highlighter;
  }


  private JScrollPane getHtmlComp(String htmlSnippet)
  {
    JEditorPane jEditorPane = new JEditorPane();
    jEditorPane.setEditable(false);

//    jEditorPane.setSize(new Dimension(200,20));
//    jEditorPane.setPreferredSize(new Dimension(200,20));
//    jEditorPane.setMinimumSize(new Dimension(200,20));

    HTMLEditorKit kit = new HTMLEditorKit();
    jEditorPane.setEditorKit(kit);
//    jEditorPane.setDocument(new LimitedSyntaxDocument(SyntaxConstants.SYNTAX_STYLE_JAVA, ApplicationConstants.CODE_LENGTH));


    // add some styles to the html
//    StyleSheet styleSheet = kit.getStyleSheet();
//    styleSheet.addRule("body {color:#000; font-family:times; margin: 4px; }");
//    styleSheet.addRule("h1 {color: blue;}");
//    styleSheet.addRule("h2 {color: #ff0000;}");
//    styleSheet.addRule("pre {font : 10px monaco; color : black; background-color : #fafafa; }");


//    String html = "<html>" + htmlSnippet + "</html>";

//    final javax.swing.text.Document defaultDocument = kit.createDefaultDocument();
//    jEditorPane.setDocument(defaultDocument);
    jEditorPane.setText(htmlSnippet);

    return new JScrollPane(jEditorPane);
  }

  private void initComponents()
  {
    java.awt.GridBagConstraints gridBagConstraints;

    jTextField1 = new javax.swing.JTextField();
    jButton1 = new javax.swing.JButton();
    suggpan = new javax.swing.JPanel();

    setLayout(new java.awt.GridBagLayout());

    jTextField1.setColumns(20);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    add(jTextField1, gridBagConstraints);

    jTextField1.setText("org.apache.commons.math3.stat.Frequency");

    jButton1.setText("Search");
    jButton1.setToolTipText("");
    jButton1.setActionCommand("Search");

    jButton1.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {


        final String searchText = jTextField1.getText();
        if (searchText != null && searchText.length() > 0)
        {
          SniperResponce sniperResponce = SnipSearch.searchSnippet(searchText);

          if (sniperResponce != null && sniperResponce.items != null && sniperResponce.items.size() > 0)
          {
            List<SnippetItems> items = sniperResponce.items;

            int itemsLength = (items.size() > 10) ? 10 : items.size();

            for (int i = 0; i < itemsLength; i++)
            {

              SnippetItems snippetItems = items.get(i);

              JXTaskPane newp = new JXTaskPane("Sample (" + (i + 1) + ")");



              try
              {
                Document doc = Jsoup.connect(snippetItems.html_url).get();
                final Element last = doc.select("td").last();
                newp.add(getHtmlComp(last.html()));

              }
              catch (Exception e1)
              {
                e1.printStackTrace();
              }

              tpc.add(newp);
              newp.setCollapsed(true);
              tpc.revalidate();
              tpc.repaint();


            }
          }
        }

      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    add(jButton1, gridBagConstraints);

    suggpan.setLayout(new BorderLayout());
    suggpan.add(new JScrollPane(tpc));

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 2.0;
    add(suggpan, gridBagConstraints);
    pack();
  }// </editor-fold>

  // Variables declaration - do not modify
  private javax.swing.JButton jButton1;

  private javax.swing.JTextField jTextField1;

  private javax.swing.JPanel suggpan;
  // End of variables declaration


  public static String cleanNoMarkup(String input) {
    final Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);
    String output = Jsoup.clean(input, "", Whitelist.basic(), outputSettings);
    return output;

  }
}

