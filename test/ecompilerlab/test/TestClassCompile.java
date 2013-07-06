package ecompilerlab.test;

/**
 * Created with IntelliJ IDEA.
 * User: shehan.fernando
 * Date: 7/6/13
 * Time: 6:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestClassCompile
{


  public static void main(String[] args)
  {
    new TestClassCompile() .testCount();
  }

  public void testCount()
  {
    int nums[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int sum = 0;


    for (int x : nums)
    {
      System.out.println("Value is: " + x);
      sum += x;
    }

    System.out.println("Summation: " + sum);
  }
}
