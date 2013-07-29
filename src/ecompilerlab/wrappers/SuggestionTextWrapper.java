package ecompilerlab.wrappers;

import ecompilerlab.clientstub.SuggestionText;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/28/13
 * Time: 11:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class SuggestionTextWrapper
{

  private String displayName;
  private String libraryName;
  private String suggestionString;

  public SuggestionTextWrapper(SuggestionText suggestionText)
  {

    this.displayName = suggestionText.getDisplayName();
    this.libraryName = suggestionText.getLibraryName();
    this.suggestionString = suggestionText.getSuggestionString();
  }


  public String getDisplayName()
  {
    return displayName;
  }

  public void setDisplayName(String displayName)
  {
    this.displayName = displayName;
  }

  public String getLibraryName()
  {
    return libraryName;
  }

  public void setLibraryName(String libraryName)
  {
    this.libraryName = libraryName;
  }

  public String getSuggestionString()
  {
    return suggestionString;
  }

  public void setSuggestionString(String suggestionString)
  {
    this.suggestionString = suggestionString;
  }

  @Override
  public boolean equals(Object obj)
  {
    SuggestionTextWrapper tt = (SuggestionTextWrapper) obj;
    return this.suggestionString.equals(tt.getSuggestionString()) ;
  }

  @Override
  public int hashCode()
  {
    return this.suggestionString.hashCode();
  }
}
