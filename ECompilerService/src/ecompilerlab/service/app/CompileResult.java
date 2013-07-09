package ecompilerlab.service.app;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/2/13
 * Time: 8:27 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class CompileResult{

  public final static int RESULT_SUCCESS = 1;

  public final static int RESULT_COMPILE_ERROR = 2;

  private int resultCode;


  private String formattedCompileError;

  public CompileResult(int resultCode, String formattedCompileError)
  {
    this.resultCode = resultCode;

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


  public String getFormattedCompileError()
  {
    return formattedCompileError;
  }

  public void setFormattedCompileError(String formattedCompileError)
  {
    this.formattedCompileError = formattedCompileError;
  }

}
