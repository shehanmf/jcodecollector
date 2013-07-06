package ecompilerlab.service.impl;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/2/13
 * Time: 8:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompileResult implements Serializable {

  public static int RESULT_SUCCESS;

  public static int RESULT_COMPILE_ERROR;

  public static int RESULT_RUNTIME_ERROR;

  public static int RESULT_UNKNOWN_ERROR;


  private int resultCode;

  private String formattedCompilationResult;

  private String formattedRunResult;

  private String formattedCompileError;

  public CompileResult(int resultCode, String formattedCompilationResult, String formattedRunResult,
                       String formattedCompileError)
  {
    this.resultCode = resultCode;
    this.formattedCompilationResult = formattedCompilationResult;
    this.formattedRunResult = formattedRunResult;
    this.formattedCompileError = formattedCompileError;
  }

  public int getResultCode()
  {
    return resultCode;
  }

  public void setResultCode(int resultCode)
  {
    this.resultCode = resultCode;
  }

  public String getFormattedRunResult()
  {
    return formattedRunResult;
  }

  public void setFormattedRunResult(String formattedRunResult)
  {
    this.formattedRunResult = formattedRunResult;
  }

  public String getFormattedCompileError()
  {
    return formattedCompileError;
  }

  public void setFormattedCompileError(String formattedCompileError)
  {
    this.formattedCompileError = formattedCompileError;
  }

  public String getFormattedCompilationResult()
  {
    return formattedCompilationResult;
  }

  public void setFormattedCompilationResult(String formattedCompilationResult)
  {
    this.formattedCompilationResult = formattedCompilationResult;
  }
}
