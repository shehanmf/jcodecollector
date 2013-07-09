package ecompilerlab.service.app.java;

import ecompilerlab.service.app.AbstractECompiler;
import ecompilerlab.service.app.ApplicationSettings;
import ecompilerlab.service.app.CompileResult;
import ecompilerlab.service.impl.LibraryEntity;
import ecompilerlab.service.impl.Platforms;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/7/13
 * Time: 4:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class JavaECompiler extends AbstractECompiler
{
  @Override
  public CompileResult compile(String className, String code, LibraryEntity[] classPathLibs)
  {

    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

    final StandardJavaFileManager standardFileManager = compiler.getStandardFileManager(null, null, null);
    JavaFileManager fileManager = new ClassFileManager(standardFileManager);

    List<JavaFileObject> jfiles = new ArrayList<JavaFileObject>();
    jfiles.add(new StringJavaFileObject(className, code));

    DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();


    ArrayList<LibraryEntity> javaPlatformJars = new ArrayList<LibraryEntity>();
    if (classPathLibs != null && classPathLibs.length > 0)
    {
      for (LibraryEntity entity : classPathLibs)
      {
        if (entity.getPlatform().equals(Platforms.JAVA))
        {
          javaPlatformJars.add(entity);
        }
      }
    }


    ArrayList<URL> classPathFiles = new ArrayList<URL>();
    if (javaPlatformJars.size() > 0)
    {
      final String libraryBasePath = ApplicationSettings.getInstance().getLibraryBasePath();

      for (LibraryEntity entity : javaPlatformJars)
      {
        final String[] libNames = entity.getLibNames();
        for (String libName : libNames)
        {
          try
          {
            classPathFiles.add(new File(libraryBasePath + libName).toURI().toURL());
          }
          catch (MalformedURLException e)
          {
            e.printStackTrace();
          }
        }
      }
    }

    List<String> options = new ArrayList<String>();
    options.add("-classpath");
    StringBuilder sb = new StringBuilder();
    URLClassLoader urlClassLoader = (URLClassLoader) Thread.currentThread().getContextClassLoader();
    for (URL url : urlClassLoader.getURLs()){
      sb.append(url.getFile().replace("%20", " ")).append(File.pathSeparator);
    }
    for (URL url : classPathFiles){
      sb.append(url.getFile().replace("%20", " ")).append(File.pathSeparator);
    }

    options.add(sb.toString());

    final Boolean call = compiler.getTask(null, fileManager, diagnostics, options, null, jfiles).call();
    if (call)
    {
      return new JavaCompileResult(JavaCompileResult.RESULT_SUCCESS, null, fileManager);
    }
    else
    {
      StringBuffer bf = new StringBuffer();
      for (Diagnostic diagnostic : diagnostics.getDiagnostics())
      {
        bf.append(diagnostic.toString() + "\n");
      }
      return new JavaCompileResult(JavaCompileResult.RESULT_COMPILE_ERROR, bf.toString(), null);
    }
  }
}
