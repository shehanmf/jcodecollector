package ecompilerlab.test;

import java.util.List;


/**
 * @author bharatha.silva
 * @since  7/6/13
 */
public class MethodTokenizerTest
{
  private MethodTokenizer methodTokenizer;


  public void testGetMethodName() throws Exception
  {
   // assertEquals(MethodTokenizer.getMethodName("public void testCount()" + "{\n"), "testCount");
  }


  public void testProcessMethods1() throws Exception
  {
    final String method =
      "int nums[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };  \n" +
      "int sum = 0;  \n" +
      " \n" +
      "// use for-each style for to display and sum the values \n" +
      "for(int x : nums) {  \n" +
      "                System.out.println(\"Value is: \" + x); \n" +
      "                sum += x;  \n" +
      "}  \n" +
      "\n" +
      "System.out.println(\"Summation: \" + sum);\n";
    methodTokenizer = new MethodTokenizer(method);

    final List<String> methods = methodTokenizer.processMethods();

   // assertEquals(methods.get(0), "public void test1()\n{\n" + method + "}");
  }


  public void testProcessMethods2() throws Exception
  {
    final String method =
      "public void testCount()\n" +
      "{\n" +
      "                int nums[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };  \n" +
      "                int sum = 0;  \n" +
      " \n" +
      "                \n" +
      "                for(int x : nums) {  \n" +
      "                                System.out.println(\"Value is: \" + x); \n" +
      "                                sum += x;  \n" +
      "                }  \n" +
      "\n" +
      "                System.out.println(\"Summation: \" + sum);          \n" +
      "}";
    methodTokenizer = new MethodTokenizer(method);

    final List<String> methods = methodTokenizer.processMethods();

    //assertEquals(methods.get(0), method);
  }


  public void testProcessMethods3() throws Exception
  {
    final String method =
      "public void testCount()\n" +
      "{\n" +
      "                int nums[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };  \n" +
      "                int sum = 0;  \n" +
      " \n" +
      "                \n" +
      "                for(int x : nums) {  \n" +
      "                                System.out.println(\"Value is: \" + x); \n" +
      "                                sum += x;  \n" +
      "                }\n" +
      "                printSum(sum)  \n" +
      "\n" +
      "                \n" +
      "}\n" +
      "\n" +
      "private void printSum(int sum)\n" +
      "{\n" +
      "                System.out.println(\"Summation: \" + sum);          \n" +
      "}";
    methodTokenizer = new MethodTokenizer(method);

    final List<String> methods = methodTokenizer.processMethods();

//    assertEquals(methods.get(0),
//                 "public void testCount()\n" +
//                 "{\n" +
//                 "                int nums[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };  \n" +
//                 "                int sum = 0;  \n" +
//                 " \n" +
//                 "                \n" +
//                 "                for(int x : nums) {  \n" +
//                 "                                System.out.println(\"Value is: \" + x); \n" +
//                 "                                sum += x;  \n" +
//                 "                }\n" +
//                 "                printSum(sum)  \n" +
//                 "\n" +
//                 "                \n" +
//                 "}");
//    assertEquals(methods.get(1),
//                 "private void printSum(int sum)\n" +
//                 "{\n" +
//                 "                System.out.println(\"Summation: \" + sum);          \n" +
//                 "}");
  }
}
