package ecompilerlab.component.model;

import ecompilerlab.wrappers.SampleCodeCacheEntry;
import ecompilerlab.wrappers.SuggestionTextWrapper;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/27/13
 * Time: 8:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebSuggestionModel
{

  private Hashtable<SuggestionTextWrapper, List<SampleCodeCacheEntry>> searchedEntry;


  public WebSuggestionModel()
  {
    this.searchedEntry = new Hashtable<SuggestionTextWrapper, List<SampleCodeCacheEntry>>();
  }

  public void setSearchedEntry(Hashtable<SuggestionTextWrapper, List<SampleCodeCacheEntry>> searchedEntry)
  {
    this.searchedEntry = searchedEntry;
  }

//  public void addSearchEntry(SuggestionTextWrapper suggestionText, List<SampleCodeCacheEntry> entries)
//  {
//    this.searchedEntry.put(suggestionText, entries);
//  }

  public Hashtable<SuggestionTextWrapper, List<SampleCodeCacheEntry>> getSearchedEntry()
  {
    return searchedEntry;
  }

  public void clean()
  {
    searchedEntry.clear();
  }

  public void addEntry(SuggestionTextWrapper webSearchString, SampleCodeCacheEntry sampleCodeCacheEntry)
  {
    List<SampleCodeCacheEntry> sampleCodeCacheEntries = searchedEntry.get(webSearchString);

    if (sampleCodeCacheEntries == null)
    {
      sampleCodeCacheEntries = new ArrayList<SampleCodeCacheEntry>();
      searchedEntry.put(webSearchString, sampleCodeCacheEntries);
    }

    sampleCodeCacheEntries.add(sampleCodeCacheEntry);
  }
}
