package ecompilerlab.component.controller;

import ecompilerlab.wrappers.SampleCodeCacheEntry;
import ecompilerlab.wrappers.SuggestionTextWrapper;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/28/13
 * Time: 4:55 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SearchNotifyListener
{

  public void notifySearch(SuggestionTextWrapper webSearchString, SampleCodeCacheEntry sampleCodeCacheEntry);
}
