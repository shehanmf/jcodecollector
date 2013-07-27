package ecompilerlab.test; /**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/24/13
 * Time: 11:27 PM
 * To change this template use File | Settings | File Templates.
 */

import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shehan
 */
public class SugPanel extends JFrame {


    JXTaskPaneContainer tpc = new JXTaskPaneContainer();

    List<JXTaskPane> panels = new ArrayList<JXTaskPane>();
    public static void main(String[] args) {

        new SugPanel().setVisible(true);
    }

    /**
     * Creates new form ecompilerlab.test.SugPanel
     */
    public SugPanel() {
        initComponents();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }


    private void initComponents() {
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

        jButton1.setText("Search");
        jButton1.setToolTipText("");
        jButton1.setActionCommand("Search");

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final String searchText = jTextField1.getText();
                if (searchText != null && searchText.length() > 0) {
                    SniperResponce sniperResponce = SnipSearch.searchSnippet(searchText);

                    if (sniperResponce != null && sniperResponce.items != null && sniperResponce.items.size() > 0) {
                        List<SnippetItems> items = sniperResponce.items;

                        int itemsLength = (items.size() > 10) ? 10:items.size();

                        for (int i = 0; i < itemsLength; i++) {

                            SnippetItems snippetItems = items.get(i);

//                            try {
////                                Document doc = Jsoup.connect(snippetItems.html_url).get();
////                                Element last = doc.select("td").last();
//                            } catch (Exception e1) {
//                                e1.printStackTrace();
//                            }

                            JXTaskPane newp = new JXTaskPane("Sample (" + (i + 1) + ")");
                            newp.add(new JLabel(snippetItems.name));
                            tpc.add(newp) ;
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
}

