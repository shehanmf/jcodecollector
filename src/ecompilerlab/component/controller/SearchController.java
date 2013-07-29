package ecompilerlab.component.controller;

import com.google.gson.Gson;
import ecompilerlab.util.SampleCodeCache;
import ecompilerlab.wrappers.SampleCodeCacheEntry;
import ecompilerlab.wrappers.SuggestionTextWrapper;
import ecompilerlab.wrappers.WebSearchItem;
import ecompilerlab.wrappers.WebSearchResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/28/13
 * Time: 11:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class SearchController
{


  private static SearchController instance = new SearchController();

  public static SearchController getInstance()
  {
    return instance;
  }


  /**
   * @param searchWrappers
   * @return
   */
  public Hashtable<SuggestionTextWrapper, List<SampleCodeCacheEntry>> doSearchCache(
    SuggestionTextWrapper[] searchWrappers)
  {

    Hashtable<SuggestionTextWrapper, List<SampleCodeCacheEntry>> searchedEntry = new Hashtable<SuggestionTextWrapper, List<SampleCodeCacheEntry>>();
    for (SuggestionTextWrapper validSuggestedString : searchWrappers)
    {
      final ArrayList<SampleCodeCacheEntry> codeCacheEntries = SampleCodeCache.getInstance()
        .get(validSuggestedString.getSuggestionString());

      if (codeCacheEntries != null && !codeCacheEntries.isEmpty())
      {
        searchedEntry.put(validSuggestedString, codeCacheEntries);
      }
    }

    return searchedEntry;
  }


  public void doSearchWebAndNotify(SuggestionTextWrapper[] searchWrappers, SearchNotifyListener notifyListener)
  {

    Set<SuggestionTextWrapper> webSearchStrings = new HashSet<SuggestionTextWrapper>();

    for (SuggestionTextWrapper validSuggestedString : searchWrappers)
    {
      final ArrayList<SampleCodeCacheEntry> codeCacheEntries = SampleCodeCache.getInstance()
        .get(validSuggestedString.getSuggestionString());

      if (!(codeCacheEntries != null && !codeCacheEntries.isEmpty()))
      {
        webSearchStrings.add(validSuggestedString);
      }
    }

    if (!webSearchStrings.isEmpty())
    {

      searchAndNotify(webSearchStrings, notifyListener);
    }
  }

  private void searchAndNotify(Set<SuggestionTextWrapper> webSearchStrings, SearchNotifyListener notifyListener)
  {

    for (SuggestionTextWrapper webSearchString : webSearchStrings)
    {
      final WebSearchResponse webSearchResponse = searchSnippet(webSearchString.getSuggestionString());

      if (webSearchResponse != null && webSearchResponse.items != null && webSearchResponse.items.size() > 0)
      {

        List<WebSearchItem> items = webSearchResponse.items;
        int itemsLength = (items.size() > 20) ? 20 : items.size();

        for (int i = 0; i < itemsLength; i++)
        {

          final WebSearchItem webSearchItem = items.get(i);
          SampleCodeCacheEntry sampleCodeCacheEntry = new SampleCodeCacheEntry();
          sampleCodeCacheEntry.setUrl(webSearchItem.html_url);
          sampleCodeCacheEntry.setSearchedWord(webSearchString.getSuggestionString());

          try
          {
            Document doc = Jsoup.connect(webSearchItem.html_url).get();
            final Element last = doc.select("td").last();
            if (last != null)
            {
              sampleCodeCacheEntry.setHtmlCode(last.html());
              SampleCodeCache.getInstance().put(sampleCodeCacheEntry);
              notifyListener.notifySearch(webSearchString,sampleCodeCacheEntry);
            }
          }
          catch (Exception e)
          {
            e.printStackTrace();
          }
        }
      }
    }
  }


//  /**
//   * @param searchWrappers
//   * @return
//   */
//  public Hashtable<SuggestionTextWrapper, List<SampleCodeCacheEntry>> doSearchWeb2(
//    SuggestionTextWrapper[] searchWrappers)
//  {
//
//    Set<SuggestionTextWrapper> webSearchStrings = new HashSet<SuggestionTextWrapper>();
//
//    Hashtable<SuggestionTextWrapper, List<SampleCodeCacheEntry>> searchedEntry =
//      new Hashtable<SuggestionTextWrapper, List<SampleCodeCacheEntry>>();
//
//
//    for (SuggestionTextWrapper validSuggestedString : searchWrappers)
//    {
//      final ArrayList<SampleCodeCacheEntry> codeCacheEntries = SampleCodeCache.getInstance()
//        .get(validSuggestedString.getSuggestionString());
//
//      if (codeCacheEntries != null && !codeCacheEntries.isEmpty())
//      {
//        searchedEntry.put(validSuggestedString, codeCacheEntries);
//      }
//      else
//      {
//        webSearchStrings.add(validSuggestedString);
//      }
//    }
//
//    if (!webSearchStrings.isEmpty())
//    {
//
//      fillWebSearchEntries(webSearchStrings, searchedEntry);
//    }
//    return searchedEntry;
//
//  }
//
//
//  /**
//   * @param webSearchStrings
//   * @param searchedEntry
//   */
//  private void fillWebSearchEntries(Set<SuggestionTextWrapper> webSearchStrings,
//                                    Hashtable<SuggestionTextWrapper, List<SampleCodeCacheEntry>> searchedEntry)
//  {
//
//    for (SuggestionTextWrapper webSearchString : webSearchStrings)
//    {
//      final WebSearchResponse webSearchResponse = searchSnippet(webSearchString.getSuggestionString());
//
//      if (webSearchResponse != null && webSearchResponse.items != null && webSearchResponse.items.size() > 0)
//      {
//        List<WebSearchItem> items = webSearchResponse.items;
//
//        int itemsLength = (items.size() > 20) ? 20 : items.size();
//
//
//        List<SampleCodeCacheEntry> entries = new ArrayList<SampleCodeCacheEntry>();
//
//        for (int i = 0; i < itemsLength; i++)
//        {
//
//          final WebSearchItem webSearchItem = items.get(i);
//
//          SampleCodeCacheEntry sampleCodeCacheEntry = new SampleCodeCacheEntry();
//          sampleCodeCacheEntry.setUrl(webSearchItem.html_url);
//          sampleCodeCacheEntry.setSearchedWord(webSearchString.getSuggestionString());
//
//
//          try
//          {
//            Document doc = Jsoup.connect(webSearchItem.html_url).get();
//            final Element last = doc.select("td").last();
//            if (last != null)
//            {
//              sampleCodeCacheEntry.setHtmlCode(last.html());
//              SampleCodeCache.getInstance().put(sampleCodeCacheEntry);
//              entries.add(sampleCodeCacheEntry);
//            }
//
//          }
//          catch (Exception e)
//          {
//            e.printStackTrace();
//            continue;
//          }
//
//        }
//
//        searchedEntry.put(webSearchString, entries);
//
//      }
//    }
//
//
//  }


  public WebSearchResponse searchSnippet(String searchString)
  {

    final String url = String
      .format("https://api.github.com/search/code?q=%s+in:file+extension:Java", searchString);
    HttpClient client = new DefaultHttpClient();
    HttpGet request = new HttpGet(url);
    request.setHeader("Accept", "application/vnd.github.preview");

    try
    {
      HttpResponse response = client.execute(request);

      StringBuilder builder = new StringBuilder();
      if (response.getStatusLine().getStatusCode() == 200)
      {
        HttpEntity entity = response.getEntity();

        // All the work is done for you here :)
        String jsonContent = EntityUtils.toString(entity);

        // Create a Reader from String
        Reader stringReader = new StringReader(jsonContent);

        Gson gson = new Gson();
        return gson.fromJson(stringReader, WebSearchResponse.class);
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    return null;
  }

}
