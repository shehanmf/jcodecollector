package ecompilerlab.component;

import com.explodingpixels.macwidgets.SourceListCategory;
import com.explodingpixels.macwidgets.SourceListClickListener;
import com.explodingpixels.macwidgets.SourceListItem;
import ecompilerlab.clientstub.ResourceLookUpEntry;
import jcodecollector.Loader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/14/13
 * Time: 7:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResourceLookUpDialog extends JDialog
{

  private JButton btnCancel;

  private SourceList resourceList;

  private javax.swing.JPanel jPanel1;

  private javax.swing.JScrollPane jScrollPane1;

  private javax.swing.JPanel listPanel;

  private ResourceLookUpEntry[] resourceLookUpEntry;

  private ECompilerResourceSourceListItem selectedResource;


  public ResourceLookUpDialog(ResourceLookUpEntry[] resourceLookUpEntries)
  {
    super(Loader.getFrameForDialogs(), true);
    this.resourceLookUpEntry = resourceLookUpEntries;
    setUndecorated(true);
    JRootPane rootPane = getRootPane();
    rootPane.setWindowDecorationStyle(JRootPane.FRAME);
    rootPane.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
    rootPane.putClientProperty("Quaqua.RootPane.isVertical", Boolean.FALSE);
    rootPane.putClientProperty("Quaqua.RootPane.isPalette", Boolean.TRUE);
    initComponents();
    setLocationRelativeTo(Loader.getFrameForDialogs());
    initFromData();

  }


  private void initFromData()
  {
    for (ResourceLookUpEntry lookUpEntry : resourceLookUpEntry)
    {
      ECompilerLibrarySourceListCategory sourceListCategoryOf = getSourceListCategoryOf(resourceList,
        lookUpEntry.getLibraryName());

      if (sourceListCategoryOf == null)
      {
        sourceListCategoryOf = new ECompilerLibrarySourceListCategory(lookUpEntry.getLibraryName(),
          lookUpEntry.getLibraryName());
        resourceList.getModel().addCategory(sourceListCategoryOf);
      }

      resourceList.getModel()
        .addItemToCategory(new ECompilerResourceSourceListItem(lookUpEntry.getFullClassName(), lookUpEntry),
          sourceListCategoryOf);
    }
  }


  private ECompilerLibrarySourceListCategory getSourceListCategoryOf(SourceList list, String libName)
  {
    if (libName == null)
    {
      throw new IllegalArgumentException("\"category\" must not be null");
    }

    List<SourceListCategory> categories = list.getModel().getCategories();

    Iterator<SourceListCategory> iterator = categories.iterator();
    while (iterator.hasNext())
    {
      ECompilerLibrarySourceListCategory sourceListCategory = (ECompilerLibrarySourceListCategory) iterator.next();
      if (sourceListCategory.getUniqueName().equals(libName))
      {
        return sourceListCategory;
      }
    }

    return null;
  }


  private void initComponents()
  {
    java.awt.GridBagConstraints gridBagConstraints;

    setTitle("Found Resources...");
    listPanel = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    resourceList = new SourceList();
    jPanel1 = new javax.swing.JPanel();
    btnCancel = new JButton();
    btnCancel.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        ResourceLookUpDialog.this.dispose();
      }
    });


    resourceList.addSourceListClickListener(new SourceListClickListener()
    {
      @Override
      public void sourceListItemClicked(SourceListItem sourceListItem, Button button, int i)
      {
        if (i == 2)//click count
        {
          selectedResource = (ECompilerResourceSourceListItem) sourceListItem;
          ResourceLookUpDialog.this.dispose();
        }
        else
        {
          selectedResource = null;
        }
      }

      @Override
      public void sourceListCategoryClicked(SourceListCategory sourceListCategory, Button button,
                                            int i)
      {
        selectedResource = null;
      }
    });
    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    getContentPane().setLayout(new java.awt.GridBagLayout());

    listPanel.setLayout(new java.awt.GridBagLayout());


    jScrollPane1.setViewportView(resourceList.getComponent());

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    listPanel.add(jScrollPane1, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    getContentPane().add(listPanel, gridBagConstraints);

    jPanel1.setLayout(new java.awt.GridBagLayout());

    btnCancel.setText("Close");
    jPanel1.add(btnCancel, new java.awt.GridBagConstraints());

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    getContentPane().add(jPanel1, gridBagConstraints);

    pack();
    setSize(new Dimension(500, 250));
  }

  public ECompilerResourceSourceListItem getSelectedResource()
  {
    return selectedResource;
  }
}
