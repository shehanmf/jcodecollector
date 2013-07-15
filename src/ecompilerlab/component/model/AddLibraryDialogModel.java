package ecompilerlab.component.model;

import ecompilerlab.clientstub.LibraryEntity;
import ecompilerlab.clientstub.LibraryType;
import ecompilerlab.clientstub.Platforms;
import ecompilerlab.component.ECompilerLibrarySourceListItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 6/30/13
 * Time: 11:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class AddLibraryDialogModel implements Cloneable {

    private Platforms curruntPatform;

    private LibraryType selectedLibType;

    private LibraryEntity[] allLibraries;

    private List<LibraryEntity> availableLibraries = new ArrayList<LibraryEntity>();

    private List<LibraryEntity> selectedLibraries = new ArrayList<LibraryEntity>();

    private boolean addCloud;


    public Platforms getCurruntPatform() {
        return curruntPatform;
    }

    public void setCurruntPatform(Platforms curruntPatform) {
        this.curruntPatform = curruntPatform;
    }

    public LibraryType getSelectedLibType() {
        return selectedLibType;
    }

    public void setSelectedLibType(LibraryType selectedLibType) {
        this.selectedLibType = selectedLibType;
    }

    public LibraryEntity[] getAllLibraries() {
        return allLibraries;
    }

    public void setAllLibraries(LibraryEntity[] allLibraries) {
        this.allLibraries = allLibraries;
    }

    public boolean isAddCloud() {
        return addCloud;
    }

    public void setAddCloud(boolean addCloud) {
        this.addCloud = addCloud;
    }

    public List<LibraryEntity> getAvailableLibraries() {
        return availableLibraries;
    }

    public void setAvailableLibraries(List<LibraryEntity> availableLibraries) {
        this.availableLibraries = availableLibraries;
    }

    public List<LibraryEntity> getSelectedLibraries() {
        return selectedLibraries;
    }

    public void setSelectedLibraries(List<LibraryEntity> selectedLibraries) {
        this.selectedLibraries = selectedLibraries;
    }


    /**
     *
     */
    public void loadsAvailableLibraries() {

        availableLibraries.clear();
        if(selectedLibType.equals(LibraryType.PLATFORM))
        {
            for(LibraryEntity entity :allLibraries)
            {
                if(entity.getPlatform().equals(curruntPatform))
                {
                    availableLibraries.add(entity);
                }
            }
        }
        else if(selectedLibType.equals(LibraryType.NATIVE))
        {
            for(LibraryEntity entity :allLibraries)
            {
                if(!entity.getPlatform().equals(curruntPatform))
                {
                    availableLibraries.add(entity);
                }
            }
//            availableLibraries.addAll(Arrays.asList(allLibraries));
        }

    }

    /**
     *
     * @param libIDs
     */
    public void setSelectedLibraryIds(ArrayList<String> libIDs) {
        selectedLibraries.clear();
        if(libIDs != null && libIDs.size() >0)
        {
            for (String libId : libIDs)
            {
                for(LibraryEntity entity :allLibraries)
                {
                    if(entity.getId() == Integer.parseInt(libId))
                    {
                        selectedLibraries.add(entity);
                        break;
                    }
                }
            }
        }
    }

    public void libraryRemoved(ECompilerLibrarySourceListItem selectedItem) {

        boolean isInList = false;
        for(LibraryEntity entity : selectedLibraries)
        {
            if(entity.getId() == selectedItem.getLibrary().getId())
            {
                isInList = true;
                break;
            }
        }
        if(isInList)
        {
            selectedLibraries.remove(selectedItem.getLibrary());
        }

    }

    public void libraryAdded(ECompilerLibrarySourceListItem selectedItem) {

        boolean alreadyAdded = false;
        for(LibraryEntity entity : selectedLibraries)
        {
            if(entity.getId() == selectedItem.getLibrary().getId())
            {
                alreadyAdded = true;
                break;
            }
        }
        if(!alreadyAdded)
        {
            selectedLibraries.add(selectedItem.getLibrary());
        }
        else
        {
            return;
        }
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        AddLibraryDialogModel cloneModel = new AddLibraryDialogModel();

        cloneModel.curruntPatform = this.curruntPatform;
        cloneModel.selectedLibType = this.selectedLibType;
        cloneModel.allLibraries = Arrays.copyOf(this.allLibraries,this.allLibraries.length);
        cloneModel.availableLibraries = new ArrayList<LibraryEntity>(this.availableLibraries);
        cloneModel.selectedLibraries = new ArrayList<LibraryEntity>(this.selectedLibraries);
        cloneModel.addCloud = this.addCloud;
        return cloneModel;
    }
}
