package ecompilerlab.component;

import ecompilerlab.component.model.SourceTagDialogModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
  private RateComponent[] rateComponents;

  private SourceTagDialogModel model;



  public TagSourceDialog(JFrame owner,SourceTagDialogModel model)
  {
    super(owner, true);
    this.model = model;
    setUndecorated(true);
    JRootPane rootPane = getRootPane();
    rootPane.setWindowDecorationStyle(JRootPane.FRAME);
    rootPane.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
    rootPane.putClientProperty("Quaqua.RootPane.isVertical", Boolean.FALSE);
    rootPane.putClientProperty("Quaqua.RootPane.isPalette", Boolean.TRUE);
    setLocationRelativeTo(owner);
    setTitle("Tag Snippets");
    initComponents();
    initFromModel();
  }

  private void initFromModel()
  {

    this.txtName.setText(this.model.getName());
    this.txtTags.setText(this.model.getTagsAsString());
  }


  private void initComponents()
  {
    java.awt.GridBagConstraints gridBagConstraints;

    lblName = new JLabel();
    lblTags = new JLabel();
    txtName = new JTextField();
    txtTags = new JTextField();
    txtTags.setEnabled(false);
    jPanel1 = new JPanel();
    btnSave = new JButton();
    btnCancel = new JButton();
    jPanel2 = new JPanel();
    jPanel2.setLayout(new GridBagLayout());

    if(model != null && model.getTags() != null && model.getTags().size() > 0)
    {
      rateComponents = new RateComponent[model.getTags().size()];

      for (int i = 0; i < model.getTags().size(); i++)
      {
        final String tag = model.getTags().get(i);
        rateComponents[i] = new RateComponent(tag);

        GridBagConstraints coins = new GridBagConstraints();
        coins.gridx = 0;
        coins.gridy = i;
        jPanel2.add(rateComponents[i],coins) ;

      }

    }
//    jPanel2 = new StarRater();
//    jPanel2.addStarListener(new StarRater.StarListener()
//    {
//      @Override
//      public void handleSelection(int selection)
//      {
//        model.setRatings(selection);
//      }
//    });

    btnCancel.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {

        TagSourceDialog.this.dispose();
      }
    });

    btnSave.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        System.out.println("ratingd :- " + model.getRatings());
      }
    });

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


    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.gridwidth = 2;
    getContentPane().add(jPanel2, gridBagConstraints);

    pack();
  }


}
