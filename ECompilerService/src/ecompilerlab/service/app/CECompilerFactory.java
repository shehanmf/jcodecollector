package ecompilerlab.service.app;

import ecompilerlab.service.app.c.CECodeFormatter;
import ecompilerlab.service.app.c.CECodeRunner;
import ecompilerlab.service.app.c.CECompiler;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/7/13
 * Time: 4:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class CECompilerFactory extends ECompilerAbstractFactory
{
  @Override
  AbstractECodeFormatter createCodeFormatter()
  {
    return new CECodeFormatter();
  }

  @Override
  AbstractECompiler createCodeCompiler()
  {
    return new CECompiler();
  }

  @Override
  AbstractECodeRunner createCodeRunner()
  {
    return new CECodeRunner();
  }
}
