package ecompilerlab.wrappers;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/27/13
 * Time: 8:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class SampleCodeCacheEntry
{

  private String url;

  private String htmlCode;

  private String searchedWord;


  public String getUrl()
  {
    return url;
  }

  public void setUrl(String url)
  {
    this.url = url;
  }

  public String getHtmlCode()
  {
    return htmlCode;
  }

  public void setHtmlCode(String htmlCode)
  {
    this.htmlCode = htmlCode;
  }

  public String getSearchedWord()
  {
    return searchedWord;
  }

  public void setSearchedWord(String searchedWord)
  {
    this.searchedWord = searchedWord;
  }
}
