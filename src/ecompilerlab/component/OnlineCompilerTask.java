package ecompilerlab.component;

import ecompilerlab.service.WebServiceClient;
import ecompilerlab.service.WebServiceClientImpl;
import ecompilerlab.service.impl.CompileRequest;
import ecompilerlab.service.impl.LibraryEntity;
import ecompilerlab.service.impl.Platforms;
import ecompilerlab.service.impl.PlatformsInfo;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/2/13
 * Time: 8:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class OnlineCompilerTask
{
  private WebServiceClient serviceClient = WebServiceClientImpl.getInstance();

  private CompilerDataProvideListener dataProvider;

  private static OnlineCompilerTask instance;

  public static OnlineCompilerTask getInstance()
  {
    if (instance == null)
    {
      instance = new OnlineCompilerTask();
    }
    return instance;
  }


  public void doCompile()
  {
    final Platforms codePlatform = this.dataProvider.getCodePlatform();
    final String codeToCompiler = this.dataProvider.getCodeToCompiler();
    final ArrayList<String> currentLibraries = this.dataProvider.getCurrentLibraries();


    CompileRequest request = new CompileRequest();
    request.setCode(codeToCompiler);
    request.setPlatform(codePlatform);
    request.setLibraryIDs(currentLibraries.toArray(new String[]{}));

    printCompilationInfo(codePlatform, currentLibraries);
  }


  public void doCompileAndRun()
  {
    final Platforms codePlatform = this.dataProvider.getCodePlatform();
    final String codeToCompiler = this.dataProvider.getCodeToCompiler();
    final ArrayList<String> currentLibraries = this.dataProvider.getCurrentLibraries();


    CompileRequest request = new CompileRequest();
    request.setCode(codeToCompiler);
    request.setPlatform(codePlatform);
    request.setLibraryIDs(currentLibraries.toArray(new String[]{}));

    printCompilationInfo(codePlatform, currentLibraries);


  }


  private void printCompilationInfo(final Platforms codePlatform, final ArrayList<String> currentLibraries)
  {
    final PlatformsInfo infoByPlatform = serviceClient.getInfoByPlatform(codePlatform);
    LibraryEntity[] libs = null;
    if (currentLibraries.size() > 0)
    {
      libs = serviceClient.getLibrariesByID(currentLibraries.toArray(new String[]{}));
    }

    dataProvider.notifyPerformed(
      new TextEntry("----------------------------------------- Platform Info -----------------------------------", true,
        TextEntry.ENTRY_TYPE.INFO));
    dataProvider.notifyPerformed(
      new TextEntry(" Platform :- " + infoByPlatform.getPlatform().toString(), true, TextEntry.ENTRY_TYPE.INFO));
    dataProvider.notifyPerformed(new TextEntry(infoByPlatform.getVersionInfo(), true, TextEntry.ENTRY_TYPE.INFO));
    dataProvider.notifyPerformed(
      new TextEntry("----------------------------------------------------------------------------------------------",
        true, TextEntry.ENTRY_TYPE.INFO));
//    dataProvider.notifyPerformed(new TextEntry("Compiling", TextEntry.ENTRY_TYPE.INFO));
//    dataProvider.notifyPerformed(new TextEntry("Platform :- " + codePlatform.toString(), TextEntry.ENTRY_TYPE.MESSAGE));
//    dataProvider.notifyPerformed(
//      new TextEntry("ClassPath libraries :- " + currentLibraries.size(), TextEntry.ENTRY_TYPE.MESSAGE));
  }


  public void setDataProvider(CompilerDataProvideListener dataProvider)
  {
    this.dataProvider = dataProvider;
  }
}
