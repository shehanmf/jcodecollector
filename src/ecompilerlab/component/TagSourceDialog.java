package ecompilerlab.component;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 9/8/13
 * Time: 11:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class TagSourceDialog extends JDialog
{
  private JButton btnSave;
  private JButton btnCancel;
  private JLabel lblName;
  private JLabel lblTags;
  private JPanel jPanel1;
  private JPanel jPanel2;
  private JTextField txtName;
  private JTextField txtTags;


  public TagSourceDialog(JFrame owner)
  {
    super(owner, true);
    initComponents();
  }


  private void initComponents()
  {
    java.awt.GridBagConstraints gridBagConstraints;

    lblName = new JLabel();
    lblTags = new JLabel();
    txtName = new JTextField();
    txtTags = new JTextField();
    jPanel1 = new JPanel();
    btnSave = new JButton();
    btnCancel = new JButton();
    jPanel2 = new JPanel();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    getContentPane().setLayout(new java.awt.GridBagLayout());

    lblName.setText("Name");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    getContentPane().add(lblName, gridBagConstraints);

    lblTags.setText("Tags");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    getContentPane().add(lblTags, gridBagConstraints);

    txtName.setColumns(30);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    getContentPane().add(txtName, gridBagConstraints);

    txtTags.setColumns(30);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    getContentPane().add(txtTags, gridBagConstraints);

    jPanel1.setLayout(new java.awt.GridBagLayout());

    btnSave.setText("Save");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    jPanel1.add(btnSave, gridBagConstraints);

    btnCancel.setText("Cancel");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    jPanel1.add(btnCancel, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.weightx = 1.0;
    getContentPane().add(jPanel1, gridBagConstraints);

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 100, Short.MAX_VALUE)
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 100, Short.MAX_VALUE)
    );

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.gridwidth = 2;
    getContentPane().add(jPanel2, gridBagConstraints);

    pack();
  }


}
