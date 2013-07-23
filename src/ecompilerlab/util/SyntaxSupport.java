package ecompilerlab.util;

import ecompilerlab.clientstub.Platforms;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 6/13/13
 * Time: 9:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class SyntaxSupport
{


  /**
   * @param platforms
   * @return
   */
  public static TreeMap<String, String> toSyntaxMap(Platforms[] platforms)
  {
    TreeMap<String, String> syntaxMap = new TreeMap<String, String>();
    for (Platforms platform : platforms)
    {

      switch (platform)
      {
        case JAVA:
          syntaxMap.put(platform.toString(), SyntaxConstants.SYNTAX_STYLE_JAVA);
          break;
        case C:
          syntaxMap.put(platform.toString(), SyntaxConstants.SYNTAX_STYLE_C);
          break;
        case C_PLUS:
          syntaxMap.put(platform.toString(), SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
          break;
        case C_SHARP:
          syntaxMap.put(platform.toString(), SyntaxConstants.SYNTAX_STYLE_CSHARP);
          break;
        case PYTHON:
          syntaxMap.put(platform.toString(), SyntaxConstants.SYNTAX_STYLE_PYTHON);
          break;
      }
    }

    return syntaxMap;
  }


  /**
   * @param syntax
   * @return
   */
  public static Platforms toPlatform(String syntax)
  {
    syntax = syntax.toUpperCase();
    if (syntax.equals(Platforms.JAVA.toString()))
    {
      return Platforms.JAVA;
    }
    else if (syntax.equals(Platforms.C.toString()))
    {
      return Platforms.C;
    }
    else if (syntax.equals(Platforms.C_PLUS.toString()))
    {
      return Platforms.C_PLUS;
    }
    else if (syntax.equals(Platforms.C_SHARP.toString()))
    {
      return Platforms.C_SHARP;
    }
    else if (syntax.equals(Platforms.PYTHON.toString()))
    {
      return Platforms.PYTHON;
    }

    return null;
  }


}
