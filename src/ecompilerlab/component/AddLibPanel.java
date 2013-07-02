package ecompilerlab.component;

import com.explodingpixels.macwidgets.SourceListCategory;
import ecompilerlab.component.model.AddLibraryDialogModel;
import ecompilerlab.service.impl.LibraryEntity;
import ecompilerlab.util.ModelSupport;
import jcodecollector.State;
import jcodecollector.common.bean.Snippet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 6/2/13
 * Time: 6:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddLibPanel extends JPanel {

    private SourceList libList = new SourceList();

    private JButton btnAddLib = new JButton("Add libraries");

    private AddLibraryDialogModel currentModel;

    private AddLibraryDialogModel currentPresistModel;

    private Snippet currentSnippet;


    public AddLibPanel() {

        JPanel tmpPanel = new JPanel();
        tmpPanel.setLayout(new BorderLayout());
        JComponent component = libList.getComponent();
        component.setSize(new Dimension(200,200));
        component.setPreferredSize(new Dimension(200,200));
        component.setMaximumSize(new Dimension(200,200));
        tmpPanel.add(component,BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(tmpPanel,BorderLayout.SOUTH);
        add(btnAddLib,BorderLayout.NORTH);

        btnAddLib.setEnabled(false);
        btnAddLib.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddLibraryDialog dialog = new AddLibraryDialog(true,currentModel);
                dialog.setVisible(true);

                if(dialog.getReturnState() == AddLibraryDialog.RETURN_STATE_SAVED)
                {
                    currentModel = dialog.getModel();
                    State.getInstance().updateWindowStatus(true);
                    try {
                        currentPresistModel = (AddLibraryDialogModel) currentModel.clone();
                    } catch (CloneNotSupportedException e1) {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
                else
                {
                    try {
                        currentModel = (AddLibraryDialogModel) currentPresistModel.clone();
                    } catch (CloneNotSupportedException e1) {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }

                reloadList();

            }
        });
    }

    public void setSnippet(Snippet snippet) {
        this.currentSnippet = snippet;
        this.currentModel = ModelSupport.getInstance().newAddLibraryDialogModel(snippet);

        try {
            this.currentPresistModel = (AddLibraryDialogModel) currentModel.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        btnAddLib.setEnabled(true);
        reloadList();
    }


    public Snippet getSnippet() {

        List<LibraryEntity> selectedLibraries = this.currentModel.getSelectedLibraries();

        ArrayList<String> libIDs = new ArrayList<String>();
        for (LibraryEntity entity : selectedLibraries)
        {
            libIDs.add(Integer.toString(entity.getId()));
        }
        this.currentSnippet.setLibIDs(libIDs);
        return this.currentSnippet;
    }

    private void reloadList()
    {
        resetSourceList();
        SourceListCategory category = new SourceListCategory("Current Libraries");
        libList.getModel().addCategory(category);
        final List<LibraryEntity> selectedLibraries = currentModel.getSelectedLibraries();

        for (LibraryEntity entity : selectedLibraries)
        {
            libList.getModel().addItemToCategory(new ECompilerLibrarySourceListItem(entity.getName(),entity),category);

        }

    }


    private void resetSourceList() {
        SourceListCategory[] categories = libList.getModel().getCategories().toArray(new SourceListCategory[] {});
        for (int i = 0; i < categories.length; i++) {
            libList.getModel().removeCategory(categories[i]);
        }
    }


    public void createNewSnippet() {
        this.currentSnippet = new Snippet();
        currentModel = ModelSupport.getInstance().newAddLibraryDialogModel(new Snippet());
        try {
            currentPresistModel = (AddLibraryDialogModel) currentModel.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        reloadList();
        btnAddLib.setEnabled(false);
    }
}
