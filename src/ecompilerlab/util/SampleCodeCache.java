package ecompilerlab.util;

import ecompilerlab.wrappers.SampleCodeCacheEntry;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/27/13
 * Time: 8:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class SampleCodeCache
{
  private final static SampleCodeCache instance = new SampleCodeCache();

  private Hashtable<String, ArrayList<SampleCodeCacheEntry>> cache = new Hashtable<String, ArrayList<SampleCodeCacheEntry>>();


  public static SampleCodeCache getInstance()
  {
    return instance;
  }

  private SampleCodeCache()
  {
  }


  public void put(SampleCodeCacheEntry entry)
  {

    ArrayList<SampleCodeCacheEntry> sampleCodeCacheEntries = get(entry.getSearchedWord());

    if (sampleCodeCacheEntries == null)
    {
      sampleCodeCacheEntries = new ArrayList<SampleCodeCacheEntry>();
      cache.put(entry.getSearchedWord(), sampleCodeCacheEntries);
    }

    sampleCodeCacheEntries.add(entry);

  }

  public ArrayList<SampleCodeCacheEntry> get(String searchedWord)
  {
    return cache.get(searchedWord);
  }


}
