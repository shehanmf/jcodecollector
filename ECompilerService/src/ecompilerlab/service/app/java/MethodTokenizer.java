package ecompilerlab.service.app.java;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/7/13
 * Time: 5:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class MethodTokenizer
{
  public static Pattern methodPrt =
    Pattern.compile("\\s*((public|private|protected|static|final|native|synchronized|abstract|threadsafe|transient)+\\s)+[\\$_\\w\\<\\>\\[\\]]*\\s+([\\$_\\w]+)\\([^\\)]*\\)?\\s*\\{?[^\\}]*\\}?");
  private String source;

  public MethodTokenizer(final String source)
  {
    this.source = source.trim();
  }

  public List<String> processMethods()
  {
    final List<String> methods = new ArrayList<String>();
    final int length = this.source.length();
    int methodCount = 0;
    int openBrackets = 0;
    int closeBrackets = 0;
    final boolean methodStarted = false;
    final List<Integer> bracketIndices = new ArrayList<Integer>();

    for (int i = 0; i < length; i++)
    {
      final char c = this.source.charAt(i);

      if (c == '{')
      {
        bracketIndices.add(i);
      }

      if (c == '}')
      {
        bracketIndices.add(-i);
      }
    }

    int currentMethodStart = 0;
    int currentMethodEnd = -1;

    for (int i = 0; i < bracketIndices.size(); i++)
    {
      final int bracketIndex = bracketIndices.get(i);

      if ((bracketIndex > 0) && (openBrackets == closeBrackets))
      {
        final String substring = this.source.substring(currentMethodEnd + 1, bracketIndex);

        if (methodPrt.matcher(substring).matches())
        {
          if (methodCount > 0)
          {
            methods.add(this.source.substring(currentMethodStart, currentMethodEnd + 1).trim());
            currentMethodStart = currentMethodEnd + 1;
          }

          methodCount++;
        }
      }

      if (bracketIndex < 0)
      {
        currentMethodEnd = -1 * bracketIndex;
      }

      if (bracketIndex > 0)
      {
        openBrackets++;
      }

      if (bracketIndex < 0)
      {
        closeBrackets++;
      }
    }

    if (methodCount > 0)
    {
      methods.add(this.source.substring(currentMethodStart, currentMethodEnd + 1).trim());
    }
    else
    {
      final String method = "public void test" + (methodCount + 1) + "()\n{\n" + this.source + "\n}";
      methods.add(method);
    }

    return methods;
  }

  public static String getMethodName(final String source)
  {
    final int i = source.indexOf('{');
    final String methodSignature = source.substring(0, i + 1);
    final Matcher matcher = methodPrt.matcher(methodSignature);

    if (matcher.matches())
    {
      return matcher.group(3);
    }

    return "";
  }
}
