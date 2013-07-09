package ecompilerlab.service.app;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/7/13
 * Time: 4:21 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractECodeRunner
{
  public abstract ExecuteResult executeCode(String className, CompileResult compileResult);
}
