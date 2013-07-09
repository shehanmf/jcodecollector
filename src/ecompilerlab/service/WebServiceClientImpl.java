package ecompilerlab.service;

import ecompilerlab.service.impl.CompileRequest;
import ecompilerlab.service.app.CompileResult;
import ecompilerlab.service.impl.CompileResponse;
import ecompilerlab.service.impl.ECompilerServiceImpl;
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
public class WebServiceClientImpl implements WebServiceClient
{

  private ECompilerService port;

  private static WebServiceClient instance;

  public static WebServiceClient getInstance()
  {
    if (instance == null)
    {
      instance = new WebServiceClientImpl();
    }
    return instance;
  }

  public WebServiceClientImpl()
  {
    this.port = new ECompilerServiceImpl();
  }

  @Override
  public Platforms[] getSupportedPlatforms()
  {
    return port.getSupportedPlatforms();
  }

  public LibraryEntity[] getAllAvailableLibraries()
  {
    return port.getAllAvailableLibraries();
  }

  public CompileResponse doCompile(CompileRequest request)
  {
    return port.doCompile(request);
  }

  public PlatformsInfo getInfoByPlatform(Platforms platforms)
  {
    return port.getInfoByPlatform(platforms);
  }

  @Override
  public LibraryEntity[] getLibrariesByID(String[] ids)
  {
    return port.getLibrariesByID(ids);
  }
}
