package smf.app.gen;

/**
 * @author bharatha.silva
 * @since  7/6/13
 */
public class TestConsoleApp
{
  public static void main(final String[] args)
  {
    TestConsoleApp testconsoleapp = new TestConsoleApp();
    testconsoleapp.testCount();
  }


  public void testCount()
  {
    int nums[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    int sum = 0;


    for(int x : nums) {
      System.out.println("Value is: " + x);
      sum += x;
    }
    printSum(sum);


  }

  private void printSum(int sum)
  {
    System.out.println("Summation: " + sum);
  }
}