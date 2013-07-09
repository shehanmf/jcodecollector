package ecompilerlab.service.app.java;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.SecureClassLoader;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/7/13
 * Time: 11:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClassFileManager extends ForwardingJavaFileManager
{
  private JavaClassObject jclassObject;


  public ClassFileManager(StandardJavaFileManager
                            standardManager)
  {
    super(standardManager);
  }


  @Override
  public ClassLoader getClassLoader(Location location)
  {

    try
    {
      return new URLClassLoader(new URL[]{})
      {


        @Override
        protected Class<?> findClass(String name)
          throws ClassNotFoundException
        {
          ClassLoader currentThreadClassLoader
            = Thread.currentThread().getContextClassLoader();

          try
          {
            URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{
              new File("H:\\Project\\ecompilerlab\\ECompilerService\\lib\\joda\\joda-time-2.2.jar").toURI().toURL()},
              currentThreadClassLoader);
            Thread.currentThread().setContextClassLoader(urlClassLoader);
          }
          catch (MalformedURLException e)
          {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
          }

          byte[] b = jclassObject.getBytes();
          return super.defineClass(name, jclassObject
            .getBytes(), 0, b.length);
        }
      };
    }
    catch (Exception e)
    {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }
//    return new SecureClassLoader()
//    {
//
//      @Override
//      protected Class<?> findClass(String name)
//        throws ClassNotFoundException
//      {
//        byte[] b = jclassObject.getBytes();
//        return super.defineClass(name, jclassObject
//          .getBytes(), 0, b.length);
//      }
//    };

    return null;
  }


  /**
   * Gives the compiler an instance of the JavaClassObject
   * so that the compiler can write the byte code into it.
   */
  @Override
  public JavaFileObject getJavaFileForOutput(Location location,
                                             String className, JavaFileObject.Kind kind, FileObject sibling)
    throws IOException
  {
    jclassObject = new JavaClassObject(className, kind);
    return jclassObject;
  }
}