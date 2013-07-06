package ecompilerlab.component;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/3/13
 * Time: 2:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class TextEntry
{

  private String text;

  private boolean newLine;

  private ENTRY_TYPE entrType;

  public enum ENTRY_TYPE
  {
    INFO, ERROR, MESSAGE
  }

  public TextEntry(String text, boolean newLine, ENTRY_TYPE entrType)
  {
    this.text = text;
    this.newLine = newLine;
    this.entrType = entrType;
  }

  public String getText()
  {
    return text;
  }

  public void setText(String text)
  {
    this.text = text;
  }

  public ENTRY_TYPE getEntrType()
  {
    return entrType;
  }

  public void setEntrType(ENTRY_TYPE entrType)
  {
    this.entrType = entrType;
  }

  public boolean isNewLine()
  {
    return newLine;
  }

  public void setNewLine(boolean newLine)
  {
    this.newLine = newLine;
  }
}
