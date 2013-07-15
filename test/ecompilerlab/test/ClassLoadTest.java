package ecompilerlab.test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/14/13
 * Time: 12:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClassLoadTest
{

  public static void main(String[] args)
  {
    try
    {
      URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new File("H:\\Project\\service\\lib\\joda\\joda-time-2.2.jar").toURI().toURL()});

      final Class<?> aClass = urlClassLoader.loadClass("org.joda.time.ReadableInstant");

      System.out.println(aClass.toString());
    }
    catch (Exception e)
    {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }
  }
}
