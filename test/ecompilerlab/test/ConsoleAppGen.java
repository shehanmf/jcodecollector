package ecompilerlab.test;

/**
 * @author bharatha.silva
 * @since  7/6/13
 */
public class ConsoleAppGen
{
  public static void main(final String[] args) throws InvalidSourceException
  {
    final ConsoleAppGenerator consoleAppGenerator =
      new ConsoleAppGenerator("public void testCount()\n" +
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
                                      "}\n");
    System.out.println(consoleAppGenerator.generate());
  }
}
