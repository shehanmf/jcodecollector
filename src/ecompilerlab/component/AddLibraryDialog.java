package ecompilerlab.component;

import com.explodingpixels.macwidgets.SourceListCategory;
import ecompilerlab.clientstub.LibraryEntity;
import ecompilerlab.clientstub.LibraryType;
import ecompilerlab.component.model.AddLibraryDialogModel;
import jcodecollector.Loader;
import ecompilerlab.clientstub.Platforms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 6/10/13
 * Time: 8:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class AddLibraryDialog extends JDialog
{

  private javax.swing.JLabel platform;

  private SourceList addedList;

  private javax.swing.JScrollPane addedListScroll;

  private javax.swing.JPanel avlLibPanel;

  private SourceList avlList;

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

  private javax.swing.JCheckBox chkAddFromCloud;

  public static int RETURN_STATE_CLOSED = 0;

  public static int RETURN_STATE_SAVED = 1;

  private int returnState;

  private AddLibraryDialogModel model;

  public AddLibraryDialog(boolean isModalDialog, AddLibraryDialogModel model)
  {

    super(Loader.getFrameForDialogs(), isModalDialog);

    this.model = model;
    setUndecorated(true);
    JRootPane rootPane = getRootPane();
    rootPane.setWindowDecorationStyle(JRootPane.FRAME);
    rootPane.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
    rootPane.putClientProperty("Quaqua.RootPane.isVertical", Boolean.FALSE);
    rootPane.putClientProperty("Quaqua.RootPane.isPalette", Boolean.TRUE);
    initComponents();
    setLocationRelativeTo(Loader.getFrameForDialogs());
    returnState = RETURN_STATE_CLOSED;
    initFromModel();
  }

  private void initFromModel()
  {

    resetSourceList(addedList);


    txtPlatform.setEnabled(false);
    txtPlatform.setText(model.getCurruntPatform().toString());
    cmbLibType.setSelectedItem(model.getSelectedLibType());
    updateAvailableList();
    updateAddedListeList();
  }


  private void updateAddedListeList()
  {

    resetSourceList(addedList);
    List<LibraryEntity> addedLibs = model.getSelectedLibraries();
    if (addedLibs != null && addedLibs.size() > 0)
    {
      for (LibraryEntity entity : addedLibs)
      {
        ECompilerLibrarySourceListCategory sourceListCategoryOf =
          getSourceListCategoryOf(addedList, entity.getPlatform());

        if (sourceListCategoryOf == null)
        {
          sourceListCategoryOf = new ECompilerLibrarySourceListCategory
            (entity.getPlatform().toString(), entity.getPlatform());
          addedList.getModel().addCategory(sourceListCategoryOf);

        }
        addedList.getModel().addItemToCategory(new ECompilerLibrarySourceListItem(entity.getName(), entity)
          , sourceListCategoryOf);

      }

    }

  }

  private void updateAvailableList()
  {
    resetSourceList(avlList);
    List<LibraryEntity> availableLibraries = model.getAvailableLibraries();

    if (availableLibraries != null && availableLibraries.size() > 0)
    {
      for (LibraryEntity entity : availableLibraries)
      {
        ECompilerLibrarySourceListCategory sourceListCategoryOf =
          getSourceListCategoryOf(avlList, entity.getPlatform());

        if (sourceListCategoryOf == null)
        {
          sourceListCategoryOf = new ECompilerLibrarySourceListCategory
            (entity.getPlatform().toString(), entity.getPlatform());
          avlList.getModel().addCategory(sourceListCategoryOf);

        }
        avlList.getModel().addItemToCategory(new ECompilerLibrarySourceListItem(entity.getName(), entity)
          , sourceListCategoryOf);

      }

    }
  }

  /**
   * @param list
   * @param categoryPlatform
   * @return
   */
  private ECompilerLibrarySourceListCategory getSourceListCategoryOf(SourceList list, Platforms categoryPlatform)
  {
    if (categoryPlatform == null)
    {
      throw new IllegalArgumentException("\"category\" must not be null");
    }

    List<SourceListCategory> categories = list.getModel().getCategories();
    Iterator<SourceListCategory> iterator = categories.iterator();
    while (iterator.hasNext())
    {
      ECompilerLibrarySourceListCategory sourceListCategory = (ECompilerLibrarySourceListCategory) iterator.next();
      if (sourceListCategory.getPlatform().equals(categoryPlatform))
      {
        return sourceListCategory;
      }
    }

    return null;
  }


  /**
   * @param list
   */
  private void resetSourceList(SourceList list)
  {
    SourceListCategory[] categories = list.getModel().getCategories().toArray(new SourceListCategory[]{});
    for (int i = 0; i < categories.length; i++)
    {
      list.getModel().removeCategory(categories[i]);
    }
  }

  private void initComponents()
  {
    java.awt.GridBagConstraints gridBagConstraints;

    avlLibPanel = new javax.swing.JPanel();

    platform = new javax.swing.JLabel();
    libType = new javax.swing.JLabel();
    txtPlatform = new javax.swing.JTextField();
    cmbLibType = new javax.swing.JComboBox();
    libPanel = new javax.swing.JPanel();
    avlListScroll = new javax.swing.JScrollPane();
    avlList = new SourceList();
    btnAdd = new javax.swing.JButton();
    btnRemove = new javax.swing.JButton();
    addedListScroll = new javax.swing.JScrollPane();
    addedList = new SourceList();
    buttonPanel = new javax.swing.JPanel();
    btnSave = new javax.swing.JButton();
    btnCancel = new javax.swing.JButton();
    chkAddFromCloud = new javax.swing.JCheckBox();

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
    cmbLibType.setModel(new javax.swing.DefaultComboBoxModel(LibraryType.values()));
    cmbLibType.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        libraryTypeChanged();
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    avlLibPanel.add(cmbLibType, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    getContentPane().add(avlLibPanel, gridBagConstraints);

    libPanel.setLayout(new java.awt.GridBagLayout());

    avlListScroll.setViewportView(avlList.getComponent());

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.weighty = 1.0;
    libPanel.add(avlListScroll, gridBagConstraints);

    btnAdd.setText("Add >>");
    btnAdd.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        libraryAdded();
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
    libPanel.add(btnAdd, gridBagConstraints);

    btnRemove.setText("<< Remove");
    btnRemove.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        libraryRemoved();
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
    libPanel.add(btnRemove, gridBagConstraints);

    addedListScroll.setViewportView(addedList.getComponent());

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
    btnSave.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        saved();
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    buttonPanel.add(btnSave, gridBagConstraints);

    btnCancel.setText("Cancel");
    btnCancel.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {

        AddLibraryDialog.this.dispose();
      }
    });
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

    chkAddFromCloud.setText("Add Cloud libraries");
    getContentPane().add(chkAddFromCloud, new java.awt.GridBagConstraints());

    pack();

    setSize(new Dimension(720, 570));
  }

  private void saved()
  {
    this.returnState = RETURN_STATE_SAVED;
    this.dispose();
  }

  private void libraryRemoved()
  {

    ECompilerLibrarySourceListItem selectedItem = (ECompilerLibrarySourceListItem) addedList.getSelectedItem();
    if (selectedItem != null)
    {
      model.libraryRemoved(selectedItem);
      updateAddedListeList();
    }

  }

  private void libraryAdded()
  {

    ECompilerLibrarySourceListItem selectedItem = (ECompilerLibrarySourceListItem) avlList.getSelectedItem();
    if (selectedItem != null)
    {
      model.libraryAdded(selectedItem);
      updateAddedListeList();

    }
  }

  private void libraryTypeChanged()
  {
    LibraryType selectedItem = (LibraryType) cmbLibType.getSelectedItem();
    model.setSelectedLibType(selectedItem);
    model.loadsAvailableLibraries();
    updateAvailableList();
  }

  public AddLibraryDialogModel getModel()
  {
    return model;
  }

  public int getReturnState()
  {
    return returnState;
  }
}
