package ecompilerlab.service.app.java;

import ecompilerlab.service.app.ExecuteResult;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/8/13
 * Time: 1:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class JavaExecuteResult extends ExecuteResult
{
  public JavaExecuteResult(int resultCode, String formattedRuntimeError, String formattedResult)
  {
    super(resultCode, formattedRuntimeError, formattedResult);
  }
}
