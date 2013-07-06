package ecompilerlab.service;

import ecompilerlab.service.impl.CompileRequest;
import ecompilerlab.service.impl.CompileResult;
import ecompilerlab.service.impl.LibraryEntity;
import ecompilerlab.service.impl.Platforms;
import ecompilerlab.service.impl.PlatformsInfo;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 6/13/13
 * Time: 8:13 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ECompilerService
{

  public Platforms[] getSupportedPlatforms();

  public LibraryEntity[] getAllAvailableLibraries();

  public CompileResult doCompile(CompileRequest request);

  public PlatformsInfo getInfoByPlatform(Platforms platforms);

  LibraryEntity[] getLibrariesByID(String[] ids);
}

