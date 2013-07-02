package ecompilerlab.service;

import ecompilerlab.service.impl.LibraryEntity;
import ecompilerlab.service.impl.Platforms;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 6/13/13
 * Time: 8:55 PM
 * To change this template use File | Settings | File Templates.
 */
public interface WebServiceClient {

    public Platforms[] getSupportedPlatforms();

    public LibraryEntity[] getAllAvailableLibraries();

}
