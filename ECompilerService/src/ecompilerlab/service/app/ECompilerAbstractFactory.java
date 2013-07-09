package ecompilerlab.service.app;

/**
 * Created with IntelliJ IDEA.
 * User: shehan.fernando
 * Date: 7/6/13
 * Time: 9:32 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class ECompilerAbstractFactory
{
  abstract AbstractECodeFormatter createCodeFormatter();

  abstract AbstractECompiler createCodeCompiler();

  abstract AbstractECodeRunner createCodeRunner();
}
