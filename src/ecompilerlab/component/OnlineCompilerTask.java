package ecompilerlab.component;

import ecompilerlab.service.WebServiceClient;
import ecompilerlab.service.WebServiceClientImpl;
import ecompilerlab.service.impl.CompileRequest;
import ecompilerlab.service.app.CompileResult;
import ecompilerlab.service.impl.CompileResponse;
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
    final String codeToCompile = this.dataProvider.getCodeToCompiler();
    final ArrayList<String> currentLibraries = this.dataProvider.getCurrentLibraries();

    CompileRequest request = new CompileRequest(codeToCompile, codePlatform, currentLibraries.toArray(new String[]{}),
      true);

    printCompilationInfo(codePlatform, currentLibraries);

    dataProvider.notifyPerformed(new TextEntry("Compiling.........", true,TextEntry.ENTRY_TYPE.INFO));
    final CompileResponse compileResponse = serviceClient.doCompile(request);

    if(compileResponse.getState() == CompileResponse.SUCCESS)
    {
      dataProvider.notifyPerformed(new TextEntry(compileResponse.getResultString(), true,TextEntry.ENTRY_TYPE.INFO));
    }
    else if(compileResponse.getState() == CompileResponse.COMPILE_ERROR)
    {
      dataProvider.notifyPerformed(new TextEntry("Compile Error", true,TextEntry.ENTRY_TYPE.ERROR));
      dataProvider.notifyPerformed(new TextEntry(compileResponse.getResultString(), true,TextEntry.ENTRY_TYPE.ERROR));
    }

  }


  public void doCompileAndRun()
  {
    final Platforms codePlatform = this.dataProvider.getCodePlatform();
    final String codeToCompile = this.dataProvider.getCodeToCompiler();
    final ArrayList<String> currentLibraries = this.dataProvider.getCurrentLibraries();


    CompileRequest request = new CompileRequest(codeToCompile, codePlatform, currentLibraries.toArray(new String[]{}),
      false);

    printCompilationInfo(codePlatform, currentLibraries);

    dataProvider.notifyPerformed(new TextEntry("Compiling.........", true,TextEntry.ENTRY_TYPE.INFO));
    final CompileResponse compileResponse = serviceClient.doCompile(request);

    if(compileResponse.getState() == CompileResponse.SUCCESS)
    {

      dataProvider.notifyPerformed(new TextEntry("\n", true,TextEntry.ENTRY_TYPE.INFO));
      dataProvider.notifyPerformed(new TextEntry("Running...", true,TextEntry.ENTRY_TYPE.ERROR));
      dataProvider.notifyPerformed(new TextEntry(compileResponse.getResultString(), true,TextEntry.ENTRY_TYPE.INFO));
    }
    else if(compileResponse.getState() == CompileResponse.COMPILE_ERROR)
    {
      dataProvider.notifyPerformed(new TextEntry("Compile Error", true,TextEntry.ENTRY_TYPE.ERROR));
      dataProvider.notifyPerformed(new TextEntry(compileResponse.getResultString(), true,TextEntry.ENTRY_TYPE.ERROR));
    }
    else if(compileResponse.getState() == CompileResponse.RUNTIME_ERROR)
    {
      dataProvider.notifyPerformed(new TextEntry("Runtime Error", true,TextEntry.ENTRY_TYPE.ERROR));
      dataProvider.notifyPerformed(new TextEntry(compileResponse.getResultString(), true,TextEntry.ENTRY_TYPE.ERROR));
    }

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
  }


  public void setDataProvider(CompilerDataProvideListener dataProvider)
  {
    this.dataProvider = dataProvider;
  }
}
