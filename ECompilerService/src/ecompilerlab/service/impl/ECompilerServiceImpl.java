package ecompilerlab.service.impl;

import ecompilerlab.service.ECompilerService;
import ecompilerlab.service.app.CompileFacade;
import ecompilerlab.service.app.CompileResult;
import ecompilerlab.service.app.LibraryFacade;
import ecompilerlab.service.app.PlatformFacade;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 6/11/13
 * Time: 7:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class ECompilerServiceImpl implements ECompilerService
{

  @Override
  public Platforms[] getSupportedPlatforms()
  {
    return Platforms.values();
  }

  @Override
  public LibraryEntity[] getAllAvailableLibraries()
  {

    return getSampleData();
  }

  @Override
  public CompileResponse doCompile(CompileRequest request)
  {
    return CompileFacade.getInstance().doCompile(request);
  }


  @Override
  public PlatformsInfo getInfoByPlatform(Platforms platforms)
  {
    return PlatformFacade.getInstance().getInfoByPlatform(platforms);
  }

  @Override
  public LibraryEntity[] getLibrariesByID(String[] ids)
  {
    return LibraryFacade.getInstance().getLibrariesByID(ids);
  }

  private LibraryEntity[] getSampleData()
  {
    return LibraryFacade.getInstance().getAllLibraries();

  }

}
