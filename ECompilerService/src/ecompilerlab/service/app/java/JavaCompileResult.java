package ecompilerlab.service.app.java;

import ecompilerlab.service.app.CompileResult;

import javax.tools.JavaFileManager;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/7/13
 * Time: 11:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class JavaCompileResult extends CompileResult
{
  private JavaFileManager javaFileManager;

  public JavaCompileResult(int resultCode, String formattedCompileError,JavaFileManager javaFileManager)
  {
    super(resultCode, formattedCompileError);
    this.javaFileManager = javaFileManager;
  }

  public JavaFileManager getJavaFileManager()
  {
    return javaFileManager;
  }
}
