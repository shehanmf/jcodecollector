package ecompilerlab.service;

import ecompilerlab.service.impl.ECompilerServiceImpl;
import ecompilerlab.service.impl.LibraryEntity;
import ecompilerlab.service.impl.Platforms;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 6/13/13
 * Time: 8:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebServiceClientImpl implements WebServiceClient{

    private ECompilerService port;

    private static WebServiceClient instance;
    public static WebServiceClient getInstance() {
        if(instance == null)
        {
            instance = new WebServiceClientImpl();
        }
        return instance;
    }

    public WebServiceClientImpl() {
        this.port = new ECompilerServiceImpl();
    }

    @Override
    public Platforms[] getSupportedPlatforms() {
        return port.getSupportedPlatforms();
    }

    public LibraryEntity[] getAllAvailableLibraries()
    {
        return port.getAllAvailableLibraries();
    }

}
