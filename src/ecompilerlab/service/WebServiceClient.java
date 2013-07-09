package ecompilerlab.service;

import ecompilerlab.service.impl.CompileRequest;
import ecompilerlab.service.app.CompileResult;
import ecompilerlab.service.impl.CompileResponse;
import ecompilerlab.service.impl.LibraryEntity;
import ecompilerlab.service.impl.Platforms;
import ecompilerlab.service.impl.PlatformsInfo;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 6/13/13
 * Time: 8:55 PM
 * To change this template use File | Settings | File Templates.
 */
public interface WebServiceClient
{

  public Platforms[] getSupportedPlatforms();

  public LibraryEntity[] getAllAvailableLibraries();

  public CompileResponse doCompile(CompileRequest request);

  public PlatformsInfo getInfoByPlatform(Platforms platforms);

  LibraryEntity[] getLibrariesByID(String[] ids);
}
