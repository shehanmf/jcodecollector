package ecompilerlab.test;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shehan.fernando
 * Date: 7/6/13
 * Time: 8:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class DynaCompTest
{
  public static void main(String[] args) throws Exception {
    // Full name of the class that will be compiled.
    // If class should be in some package,
    // fullName should contain it too
    // (ex. "testpackage.DynaClass")
    String fullName = "TestClassCompile";

//    // Here we specify the source code of the class to be compiled
//    StringBuilder src = new StringBuilder();
//    src.append("public class DynaClass {\n");
//    src.append("    public String toString() {\n");
//    src.append("        return \"Hello, I am \" + ");
//    src.append("this.getClass().getSimpleName();\n");
//    src.append("    }\n");
//    src.append("}\n");



    String src = "public class TestClassCompile\n" +
      "{\n" +
      "\t\n" +
      "  public static void main(String[] args)\n" +
      "  {\n" +
      "    new TestClassCompile() .testCount();\n" +
      "  }\n" +
      "\n" +
      "  public void testCount()\n" +
      "  {\n" +
      "    int nums[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};\n" +
      "    int sum = 0;\n" +
      "\n" +
      "\n" +
      "    for (int x : nums)\n" +
      "    {\n" +
      "      System.out.println(\"Value is: \" + x);\n" +
      "      sum += x;\n" +
      "    }\n" +
      "\n" +
      "    System.out.println(\"Summation: \" + sum);\n" +
      "  }\n" +
      "}";

    System.out.println(src);

    // We get an instance of JavaCompiler. Then
    // we create a file manager
    // (our custom implementation of it)
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    JavaFileManager fileManager = new
      ClassFileManager(compiler
      .getStandardFileManager(null, null, null));

    // Dynamic compiling requires specifying
    // a list of "files" to compile. In our case
    // this is a list containing one "file" which is in our case
    // our own implementation (see details below)
    List<JavaFileObject> jfiles = new ArrayList<JavaFileObject>();
    jfiles.add(new CharSequenceJavaFileObject(fullName, src));

    // We specify a task to the compiler. Compiler should use our file
    // manager and our list of "files".
    // Then we run the compilation with call()
    compiler.getTask(null, fileManager, null, null,
      null, jfiles).call();

    // Creating an instance of our compiled class and
    // running its toString() method
//    Object instance = fileManager.getClassLoader(null)
//      .loadClass(fullName).newInstance();
//    System.out.println(instance);

    Class<?> cls = fileManager.getClassLoader(null).loadClass(fullName);
    Method meth = cls.getMethod("main", String[].class);
    String[] params = null; // init params accordingly
    meth.invoke(null, (Object) params);

  }
}
