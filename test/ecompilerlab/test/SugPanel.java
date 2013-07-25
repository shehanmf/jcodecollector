package ecompilerlab.test; /**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/24/13
 * Time: 11:27 PM
 * To change this template use File | Settings | File Templates.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Shehan
 */
public class SugPanel extends JFrame
{


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
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
          SnipSearch.searchSnippet(searchText);
        }

      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    add(jButton1, gridBagConstraints);

    javax.swing.GroupLayout suggpanLayout = new javax.swing.GroupLayout(suggpan);
    suggpan.setLayout(suggpanLayout);
    suggpanLayout.setHorizontalGroup(
      suggpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 439, Short.MAX_VALUE)
    );
    suggpanLayout.setVerticalGroup(
      suggpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 391, Short.MAX_VALUE)
    );

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

