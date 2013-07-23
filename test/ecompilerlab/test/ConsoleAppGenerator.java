package ecompilerlab.test;

import java.util.regex.Matcher;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author bharatha.silva
 * @since  7/6/13
 */
public class ConsoleAppGenerator
{
  public static final char NEW_LINE_CHAR = '\n';
  public static final char CLOSE_BRACKET = '}';
  public static final char OPEN_BRACKET = '{';
  private static Pattern classPrt = Pattern.compile(".*\\s*class\\s+([a-zA-Z_$][a-zA-Z\\d_$]*)\\s*.*?");
  private String source;
  private String suggestedClassName;

  public ConsoleAppGenerator(final String source)
  {
    this(source, "TestConsoleApp");
  }

  public ConsoleAppGenerator(final String source, final String suggestedClassName)
  {
    this.source = source;
    this.suggestedClassName = suggestedClassName;
  }

  public String generate() throws InvalidSourceException
  {
    if (isCompleteClass(source))
    {
      return source;
    }

    checkIncompleteBrackets();

    final StringBuilder sb = new StringBuilder();
    sb.append("public class ").append(this.suggestedClassName);
    sb.append(NEW_LINE_CHAR);
    sb.append(OPEN_BRACKET).append(NEW_LINE_CHAR);

    //inside class
    sb.append("public static void main(final String[] args)").append(NEW_LINE_CHAR);
    sb.append(OPEN_BRACKET).append(NEW_LINE_CHAR);

    //inside main
    final String objectName = this.suggestedClassName.toLowerCase();
    sb.append(this.suggestedClassName).append(" ").append(objectName).append(" = ").append("new ")
      .append(this.suggestedClassName).append("();");
    sb.append(NEW_LINE_CHAR);

    final MethodTokenizer methodTokenizer = new MethodTokenizer(this.source);
    final List<String> methods = methodTokenizer.processMethods();

    if (methods.size() >= 0)
    {
      final String methodSource = methods.get(0);
      final String methodName = MethodTokenizer.getMethodName(methodSource);

      sb.append(objectName).append(".").append(methodName).append("();");
      sb.append(NEW_LINE_CHAR);
    }

    //
    sb.append(CLOSE_BRACKET).append(NEW_LINE_CHAR);

    for (final String method : methods)
    {
      sb.append(NEW_LINE_CHAR);
      sb.append(NEW_LINE_CHAR);
      sb.append(method);
    }

    sb.append(NEW_LINE_CHAR);

    //inside class
    sb.append(CLOSE_BRACKET);

    return sb.toString();
  }

  private void checkIncompleteBrackets() throws InvalidSourceException
  {
    final int length = this.source.length();
    int c = 0;

    for (int i = 0; i < length; i++)
    {
      final char cha = this.source.charAt(i);

      if (cha == OPEN_BRACKET)
      {
        c++;
      }

      if (cha == CLOSE_BRACKET)
      {
        c--;
      }
    }

    if (c != 0)
    {
      throw new InvalidSourceException("Incomplete source : mismatch of open\\close brackets");
    }
  }

  private boolean isCompleteClass(final String source)
  {
    final Matcher matcher = classPrt.matcher(source);

    return matcher.find();
  }
}
