package ecompilerlab.component;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 6/10/13
 * Time: 8:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class AddLibraryDialog extends JDialog {

    private javax.swing.JLabel platform;
    private javax.swing.JList addedList;
    private javax.swing.JScrollPane addedListScroll;
    private javax.swing.JPanel avlLibPanel;
    private javax.swing.JList avlList;
    private javax.swing.JScrollPane avlListScroll;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSave;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JComboBox cmbLibType;
    private javax.swing.JPanel libPanel;
    private javax.swing.JLabel libType;
    private javax.swing.JTextField txtPlatform;

    public AddLibraryDialog(Frame owner, boolean modal) {

        super(owner, modal);
        setUndecorated(true);
        JRootPane rootPane = getRootPane();
        rootPane.setWindowDecorationStyle(JRootPane.FRAME);
        rootPane.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
        rootPane.putClientProperty("Quaqua.RootPane.isVertical", Boolean.FALSE);
        rootPane.putClientProperty("Quaqua.RootPane.isPalette", Boolean.TRUE);
        initComponents();
    }


    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        avlLibPanel = new javax.swing.JPanel();

        platform = new javax.swing.JLabel();
        libType = new javax.swing.JLabel();
        txtPlatform = new javax.swing.JTextField();
        cmbLibType = new javax.swing.JComboBox();
        libPanel = new javax.swing.JPanel();
        avlListScroll = new javax.swing.JScrollPane();
        avlList = new javax.swing.JList();
        btnAdd = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        addedListScroll = new javax.swing.JScrollPane();
        addedList = new javax.swing.JList();
        buttonPanel = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add New Library");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        avlLibPanel.setLayout(new java.awt.GridBagLayout());

        platform.setText("Platform");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        avlLibPanel.add(platform, gridBagConstraints);

        libType.setText("Library Type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        avlLibPanel.add(libType, gridBagConstraints);

        txtPlatform.setColumns(15);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        avlLibPanel.add(txtPlatform, gridBagConstraints);

        cmbLibType.setMinimumSize(new java.awt.Dimension(250, 20));
        cmbLibType.setPreferredSize(new java.awt.Dimension(250, 20));
        cmbLibType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Platform", "Native" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        avlLibPanel.add(cmbLibType, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        getContentPane().add(avlLibPanel, gridBagConstraints);

        libPanel.setLayout(new java.awt.GridBagLayout());

        avlListScroll.setViewportView(avlList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        libPanel.add(avlListScroll, gridBagConstraints);

        btnAdd.setText("Add >>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        libPanel.add(btnAdd, gridBagConstraints);

        btnRemove.setText("Remove");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        libPanel.add(btnRemove, gridBagConstraints);

        addedListScroll.setViewportView(addedList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        libPanel.add(addedListScroll, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(libPanel, gridBagConstraints);

        buttonPanel.setLayout(new java.awt.GridBagLayout());

        btnSave.setText("Save");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        buttonPanel.add(btnSave, gridBagConstraints);

        btnCancel.setText("Cancel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        buttonPanel.add(btnCancel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(buttonPanel, gridBagConstraints);

        pack();
    }
}
