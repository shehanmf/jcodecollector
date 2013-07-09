package ecompilerlab.service.app;

import ecompilerlab.service.impl.LibraryEntity;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/7/13
 * Time: 4:20 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractECompiler
{
  public abstract CompileResult compile(String className, String code, LibraryEntity[] classPathLibs);
}
