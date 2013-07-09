package ecompilerlab.service.app;

import ecompilerlab.service.impl.CompileRequest;
import ecompilerlab.service.impl.CompileResponse;
import ecompilerlab.service.impl.Platforms;

/**
 * Created with IntelliJ IDEA.
 * User: shehan.fernando
 * Date: 7/6/13
 * Time: 9:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompileFacade
{

  private static CompileFacade instance = new CompileFacade();

  public static CompileFacade getInstance()
  {
    return instance;
  }

  public CompileResponse doCompile(CompileRequest request)
  {

    final ECompilerAbstractFactory eCompiler = getECompiler(request.getPlatform());
    final AbstractECodeFormatter codeFormatter = eCompiler.createCodeFormatter();

    try
    {
      final String formattedCode = codeFormatter
        .generate(request.getCode(), ApplicationSettings.getInstance().getTestClassName());

      final String className = codeFormatter.getClassName(formattedCode);

      final AbstractECompiler codeCompiler = eCompiler.createCodeCompiler();

      final CompileResult compileResult = codeCompiler
        .compile(className, formattedCode, LibraryFacade.getInstance().getLibrariesByID(request.getLibraryIDs()));

      if (compileResult.getResultCode() == CompileResult.RESULT_SUCCESS)
      {
        if (request.isCompileOnly())
        {
          return new CompileResponse(CompileResponse.SUCCESS, "Compilation Success");
        }
        else
        {
          final AbstractECodeRunner codeRunner = eCompiler.createCodeRunner();
          final ExecuteResult executeResult = codeRunner.executeCode(className, compileResult);

          if (executeResult.getResultCode() == ExecuteResult.RESULT_SUCCESS)
          {
            return new CompileResponse(CompileResponse.SUCCESS, executeResult.getFormattedResult());
          }
          else
          {
            return new CompileResponse(CompileResponse.RUNTIME_ERROR, executeResult.getFormattedRuntimeError());
          }
        }
      }
      else
      {
        return new CompileResponse(CompileResponse.COMPILE_ERROR, compileResult.getFormattedCompileError());
      }


    }
    catch (InvalidSourceException e)
    {
      return null;
    }
  }


  public static ECompilerAbstractFactory getECompiler(Platforms platform)
  {
    switch (platform)
    {
      case JAVA:
        return new JavaECompilerFactory();

      case C:
        return new CECompilerFactory();
    }
    return null;


  }


}
