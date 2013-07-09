package ecompilerlab.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/7/13
 * Time: 6:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegExpExample {

  public static void main(String args[]) {
//    String unadornedClassRE = "^\\s*class (\\w+)";
    String unadornedClassRE = "class (\\w+)";



    Pattern classPattern = Pattern.compile(unadornedClassRE);

    Matcher classMatcher;

//    String line = " class HellolClass";
    String line = "import java.test.*;\n" +
      "public class Helloclass{\n" +
      "\n" +
      "\tpublic void testCount()\n" +
      "\t{\n" +
      "     \tint nums[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }; \n" +
      "     \tint sum = 0; \n" +
      "\n" +
      "     \tfor(int x : nums) { \n" +
      "\t\t\tSystem.out.println(\"Value is: \" + x);\n" +
      "\t    \t\tsum += x; \n" +
      "     \t}\n" +
      "     \tprintSum(sum);\n" +
      "                \n" +
      "\t}\n" +
      " \n" +
      "\tprivate void printSum(int sum)\n" +
      "\t{\n" +
      "\t\tSystem.out.println(\"Summation: \" + sum);         \n" +
      "\t}\n" +
      "}";

    classMatcher = classPattern.matcher(line);


    if (classMatcher.find())
    {
      System.out.printf("Class name is :-" +  classMatcher.group(1));
    }

//    if (classMatcher.find()) {
//      System.out.println("The class [" + classMatcher.group(1) + "] is not public");
//    }
//
//    while (doubleMatcher.find()) {
//      System.out.println("The word \"" + doubleMatcher.group(1) + "\" occurs twice at position "
//        + doubleMatcher.start());
//    }

  }
}