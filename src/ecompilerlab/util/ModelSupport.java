package ecompilerlab.util;

import ecompilerlab.component.model.AddLibraryDialogModel;
import ecompilerlab.service.WebServiceClient;
import ecompilerlab.service.WebServiceClientImpl;
import ecompilerlab.service.impl.LibraryEntity;
import ecompilerlab.service.impl.LibraryType;
import jcodecollector.common.bean.Snippet;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/2/13
 * Time: 12:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class ModelSupport {


    private static ModelSupport instance;

    private WebServiceClient serviceClient = WebServiceClientImpl.getInstance();

    private ModelSupport(){};

    public static ModelSupport getInstance() {
        if(instance == null)
        {
            instance = new ModelSupport();
        }
        return instance;
    }

    public AddLibraryDialogModel newAddLibraryDialogModel(Snippet snippet)
    {
        AddLibraryDialogModel model = new AddLibraryDialogModel();

        model.setAddCloud(false);
        model.setCurruntPatform(SyntaxSupport.toPlatform(snippet.getSyntax()));
        model.setSelectedLibType(LibraryType.Platform);
        LibraryEntity[] allAvailableLibraries = serviceClient.getAllAvailableLibraries();

        model.setAllLibraries(allAvailableLibraries);
        model.loadsAvailableLibraries();
        model.setSelectedLibraryIds(snippet.getLibIDs());
        return model;
    }

}
