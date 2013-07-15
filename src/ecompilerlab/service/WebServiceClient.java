package ecompilerlab.service;


import ecompilerlab.clientstub.CompileRequest;
import ecompilerlab.clientstub.CompileResponse;
import ecompilerlab.clientstub.LibraryEntity;
import ecompilerlab.clientstub.PlatformsInfo;
import ecompilerlab.clientstub.Platforms;
import ecompilerlab.clientstub.ResourceLookUpEntry;
import ecompilerlab.component.CompilerDataProvideListener;

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

  public LibraryEntity[] getLibrariesByID(String[] ids);

  public void setDataProvider(CompilerDataProvideListener dataProvider);

  public void doConnect();

  public ResourceLookUpEntry[] classLookUp(String className,Platforms platforms,String[] libIds);

}
