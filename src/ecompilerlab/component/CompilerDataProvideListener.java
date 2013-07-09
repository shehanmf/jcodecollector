package ecompilerlab.component;

import ecompilerlab.service.impl.LibraryEntity;
import ecompilerlab.service.impl.Platforms;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: shehan.fernando
 * Date: 7/6/13
 * Time: 1:23 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CompilerDataProvideListener
{

  public String getCodeToCompiler();

  public ArrayList<String> getCurrentLibraries();

  public Platforms getCodePlatform();

  public void notifyPerformed(TextEntry textEntry);
}
