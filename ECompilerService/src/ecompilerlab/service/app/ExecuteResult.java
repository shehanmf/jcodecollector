package ecompilerlab.service.app;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/8/13
 * Time: 12:58 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class ExecuteResult
{
  public final static int RESULT_SUCCESS = 1;

  public final static int RESULT_RUNTIME_ERROR = 2;

  private int resultCode;

  private String formattedRuntimeError;

  private String formattedResult;

  public ExecuteResult(int resultCode, String formattedRuntimeError,String formattedResult)
  {
    this.resultCode = resultCode;

    this.formattedRuntimeError = formattedRuntimeError;

    this.formattedResult = formattedResult;
  }

  public int getResultCode()
  {
    return resultCode;
  }

  public void setResultCode(int resultCode)
  {
    this.resultCode = resultCode;
  }


  public String getFormattedRuntimeError()
  {
    return formattedRuntimeError;
  }

  public void setFormattedRuntimeError(String formattedRuntimeError)
  {
    this.formattedRuntimeError = formattedRuntimeError;
  }

  public String getFormattedResult()
  {
    return formattedResult;
  }

  public void setFormattedResult(String formattedResult)
  {
    this.formattedResult = formattedResult;
  }
}
