package ecompilerlab.component;

import ecompilerlab.clientstub.CompileRequest;
import ecompilerlab.clientstub.CompileResponse;
import ecompilerlab.clientstub.CompileResponseState;
import ecompilerlab.clientstub.LibraryEntity;
import ecompilerlab.clientstub.PlatformsInfo;
import ecompilerlab.clientstub.ResourceLookUpEntry;
import ecompilerlab.service.WebServiceClient;
import ecompilerlab.service.WebServiceClientImpl;
import ecompilerlab.clientstub.Platforms;

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


  public ResourceLookUpEntry[] doClassLookUp(String className)
  {
    final Platforms codePlatform = this.dataProvider.getCodePlatform();
    final ArrayList<String> currentLibraries = this.dataProvider.getCurrentLibraries();
    return WebServiceClientImpl.getInstance()
      .classLookUp(className, codePlatform, currentLibraries.toArray(new String[]{}));

  }


  public void doCompile()
  {
    final Platforms codePlatform = this.dataProvider.getCodePlatform();
    final String codeToCompile = this.dataProvider.getCodeToCompiler();
    final ArrayList<String> currentLibraries = this.dataProvider.getCurrentLibraries();

//    CompileRequest request = new CompileRequest(codeToCompile, codePlatform, currentLibraries.toArray(new String[]{}),
//      true);

    CompileRequest request = new CompileRequest();
    request.setCode(codeToCompile);
    request.setPlatform(codePlatform);
    request.getLibraryIDs().addAll(currentLibraries);
    request.setCompileOnly(true);

    printCompilationInfo(codePlatform, currentLibraries);

    dataProvider.notifyPerformed(new TextEntry("Compiling.........", true, TextEntry.ENTRY_TYPE.INFO));
    final CompileResponse compileResponse = WebServiceClientImpl.getInstance().doCompile(request);

    if (compileResponse.getState() == CompileResponseState.SUCCESS)
    {
      dataProvider.notifyPerformed(new TextEntry(compileResponse.getResultString(), true, TextEntry.ENTRY_TYPE.INFO));
    }
    else if (compileResponse.getState() == CompileResponseState.COMPILE_ERROR)
    {
      dataProvider.notifyPerformed(new TextEntry("Compile Error", true, TextEntry.ENTRY_TYPE.ERROR));
      dataProvider.notifyPerformed(new TextEntry(compileResponse.getResultString(), true, TextEntry.ENTRY_TYPE.ERROR));
    }

  }


  public void doCompileAndRun()
  {
    final Platforms codePlatform = this.dataProvider.getCodePlatform();
    final String codeToCompile = this.dataProvider.getCodeToCompiler();
    final ArrayList<String> currentLibraries = this.dataProvider.getCurrentLibraries();


//    CompileRequest request = new CompileRequest(codeToCompile, codePlatform, currentLibraries.toArray(new String[]{}),
//      false);
    CompileRequest request = new CompileRequest();
    request.setCode(codeToCompile);
    request.setPlatform(codePlatform);
    request.getLibraryIDs().addAll(currentLibraries);
    request.setCompileOnly(false);

    printCompilationInfo(codePlatform, currentLibraries);

    dataProvider.notifyPerformed(new TextEntry("Compiling.........", true, TextEntry.ENTRY_TYPE.INFO));
    final CompileResponse compileResponse = WebServiceClientImpl.getInstance().doCompile(request);

    if (compileResponse.getState() == CompileResponseState.SUCCESS)
    {

      dataProvider.notifyPerformed(new TextEntry("\n", true, TextEntry.ENTRY_TYPE.INFO));
      dataProvider.notifyPerformed(new TextEntry("Running...", true, TextEntry.ENTRY_TYPE.ERROR));
      dataProvider.notifyPerformed(new TextEntry(compileResponse.getResultString(), true, TextEntry.ENTRY_TYPE.INFO));
    }
    else if (compileResponse.getState() == CompileResponseState.COMPILE_ERROR)
    {
      dataProvider.notifyPerformed(new TextEntry("Compile Error", true, TextEntry.ENTRY_TYPE.ERROR));
      dataProvider.notifyPerformed(new TextEntry(compileResponse.getResultString(), true, TextEntry.ENTRY_TYPE.ERROR));
    }
    else if (compileResponse.getState() == CompileResponseState.RUNTIME_ERROR)
    {
      dataProvider.notifyPerformed(new TextEntry("Runtime Error", true, TextEntry.ENTRY_TYPE.ERROR));
      dataProvider.notifyPerformed(new TextEntry(compileResponse.getResultString(), true, TextEntry.ENTRY_TYPE.ERROR));
    }

  }


  private void printCompilationInfo(final Platforms codePlatform, final ArrayList<String> currentLibraries)
  {
    try
    {

      final PlatformsInfo infoByPlatform = WebServiceClientImpl.getInstance().getInfoByPlatform(codePlatform);
      LibraryEntity[] libs = null;
      if (currentLibraries.size() > 0)
      {
        libs = WebServiceClientImpl.getInstance().getLibrariesByID(currentLibraries.toArray(new String[]{}));
      }

      dataProvider.notifyPerformed(
        new TextEntry("----------------------------------------- Platform Info -----------------------------------",
          true,
          TextEntry.ENTRY_TYPE.INFO));
      dataProvider.notifyPerformed(
        new TextEntry(" Platform :- " + infoByPlatform.getPlatform().toString(), true, TextEntry.ENTRY_TYPE.INFO));
      dataProvider.notifyPerformed(new TextEntry(infoByPlatform.getVersionInfo(), true, TextEntry.ENTRY_TYPE.INFO));
      dataProvider.notifyPerformed(
        new TextEntry("----------------------------------------------------------------------------------------------",
          true, TextEntry.ENTRY_TYPE.INFO));
    }

    catch (Exception e)
    {

      e.printStackTrace();
    }

  }


  public void setDataProvider(CompilerDataProvideListener dataProvider)
  {
    this.dataProvider = dataProvider;
  }
}
