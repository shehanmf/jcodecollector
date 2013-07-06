package ecompilerlab.service.app;

import ecompilerlab.service.impl.CompileRequest;
import ecompilerlab.service.impl.CompileResult;

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

  public CompileResult doCompile(CompileRequest request)
  {
    switch (request.getPlatform())
    {
      case JAVA:

      case C:

      case C_PLUS:

      case C_SHARP:

      case PYTHON:

    }
    return new CompileResult(CompileResult.RESULT_SUCCESS, "Compile success", "Running", "no errors");
  }

}
