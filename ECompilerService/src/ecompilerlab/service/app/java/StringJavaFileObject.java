package ecompilerlab.service.app.java;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/7/13
 * Time: 11:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringJavaFileObject extends SimpleJavaFileObject
{

  private String content;

  protected StringJavaFileObject(String className,
                                 String content)
  {
    super(URI.create("string:///" + className.replace('.', '/')
      + Kind.SOURCE.extension), Kind.SOURCE);
    this.content = content;
  }


  @Override
  public CharSequence getCharContent(
    boolean ignoreEncodingErrors)
  {
    return content;
  }
}
