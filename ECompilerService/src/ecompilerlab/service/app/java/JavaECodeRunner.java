package ecompilerlab.service.app.java;

import ecompilerlab.service.app.AbstractECodeRunner;
import ecompilerlab.service.app.CompileResult;
import ecompilerlab.service.app.ExecuteResult;

import javax.tools.JavaFileManager;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLClassLoader;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/7/13
 * Time: 4:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class JavaECodeRunner extends AbstractECodeRunner
{
  @Override
  public ExecuteResult executeCode(String className, CompileResult compileResult)
  {
    JavaCompileResult result = (JavaCompileResult) compileResult;

    final JavaFileManager fileManager = result.getJavaFileManager();


    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    PrintStream old = System.out;


    try
    {
      System.setOut(ps);


      Class<?> cls = fileManager.getClassLoader(null).loadClass(className);
      Method meth = cls.getMethod("main", String[].class);
      String[] params = null; // init params accordingly
      meth.invoke(null, (Object) params);

      System.out.flush();
      System.setOut(old);

      return new JavaExecuteResult(JavaExecuteResult.RESULT_SUCCESS, null,baos.toString());
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return new JavaExecuteResult(JavaExecuteResult.RESULT_RUNTIME_ERROR, e.getMessage(),null);
    }
    catch (Error e)
    {
      e.printStackTrace();
      return new JavaExecuteResult(JavaExecuteResult.RESULT_RUNTIME_ERROR, e.getMessage(),null);
    }
  }
}
