package ecompilerlab.service.app;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/7/13
 * Time: 5:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class InvalidSourceException  extends Exception
{
  public InvalidSourceException(final String message)
  {
    super(message);
  }
}
