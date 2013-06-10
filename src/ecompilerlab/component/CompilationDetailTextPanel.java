package ecompilerlab.component;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: shehan.fernando
 * Date: 6/1/13
 * Time: 6:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompilationDetailTextPanel extends JPanel
{


    public CompilationDetailTextPanel() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        btnCompile = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtCompoileInfo = new javax.swing.JTextArea();

        setLayout(new java.awt.GridBagLayout());

        btnCompile.setText("Compile");
        btnCompile.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        add(btnCompile, gridBagConstraints);

        txtCompoileInfo.setColumns(20);
        txtCompoileInfo.setRows(5);
        jScrollPane2.setViewportView(txtCompoileInfo);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jScrollPane2, gridBagConstraints);
    }// </editor-fold>
    // Variables declaration - do not modify
    private javax.swing.JButton btnCompile;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtCompoileInfo;
}
