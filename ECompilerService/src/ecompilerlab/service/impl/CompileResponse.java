package ecompilerlab.service.impl;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/8/13
 * Time: 12:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class CompileResponse implements Serializable
{
  public static final int SUCCESS = 1;

  public static final int COMPILE_ERROR = 2;

  public static final int RUNTIME_ERROR = 3;

  public static final int OTHER_ERROR = 4;


  private int state;

  private String resultString;


  public CompileResponse(int state, String resultString)
  {
    this.state = state;
    this.resultString = resultString;
  }


  public int getState()
  {
    return state;
  }

  public void setState(int state)
  {
    this.state = state;
  }

  public String getResultString()
  {
    return resultString;
  }

  public void setResultString(String resultString)
  {
    this.resultString = resultString;
  }
}
