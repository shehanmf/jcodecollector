package ecompilerlab.component.controller;

import ecompilerlab.component.WebSuggestionPanel;
import ecompilerlab.component.model.WebSuggestionModel;
import ecompilerlab.service.WebServiceClientImpl;
import ecompilerlab.util.SyntaxSupport;
import ecompilerlab.wrappers.SampleCodeCacheEntry;
import ecompilerlab.wrappers.SuggestionTextWrapper;
import jcodecollector.common.bean.Snippet;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/27/13
 * Time: 8:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebSuggestionController
{


  private static WebSuggestionController instance;

  private static WebSuggestionModel model;

  private static WebSuggestionPanel view;

  private static boolean codeChanged;

  private static boolean snippetChanged;

  private static Snippet currentSnippet;

  private static Timer timer;

  public static WebSuggestionController getInstance()
  {

    if (instance == null)
    {
      instance = new WebSuggestionController();
      model = new WebSuggestionModel();
      model.setCurrentSnippet(currentSnippet);
      view = new WebSuggestionPanel(model);

      timer = new Timer();
      timer.scheduleAtFixedRate(createWebSearchTask(), 5 * 1000, 5 * 1000);
    }
    return instance;
  }

  private WebSuggestionController()
  {
  }


  public WebSuggestionPanel getUI()
  {
    return view;
  }

  public void snippetChanged(Snippet snippet)
  {
    model.clean();
    this.codeChanged = true;
    this.currentSnippet = snippet;
    this.snippetChanged = true;
    view.modelChanged();
    model.setCurrentSnippet(this.currentSnippet);
  }


  private static TimerTask createWebSearchTask()
  {

    return new TimerTask()
    {
      @Override
      public void run()
      {

        try
        {
          if (codeChanged)
          {
            SwingWorker<Hashtable<SuggestionTextWrapper, List<SampleCodeCacheEntry>>, Integer> woker = new
              SwingWorker<Hashtable<SuggestionTextWrapper, List<SampleCodeCacheEntry>>, Integer>()
              {
                SuggestionTextWrapper[] validSuggestedStrings;

                @Override
                protected Hashtable<SuggestionTextWrapper, List<SampleCodeCacheEntry>> doInBackground() throws Exception
                {
                  System.out.println("Start Search....");
                  String[] availableStrings = clean(currentSnippet.getCode());

                  validSuggestedStrings = WebServiceClientImpl.getInstance()
                    .getValidSuggestedStrings(
                      SyntaxSupport.toPlatform(currentSnippet.getSyntax()), availableStrings,
                      currentSnippet.getLibIDs().toArray(new String[]{}));

                  if (validSuggestedStrings != null && validSuggestedStrings.length > 0)
                  {

                    Hashtable<SuggestionTextWrapper, List<SampleCodeCacheEntry>> searchedEntry =
                      SearchController.getInstance().doSearchCache(validSuggestedStrings);

                    return searchedEntry;
                  }

                  return null;
                }

                @Override
                protected void done()
                {

                  try
                  {

                    final Hashtable<SuggestionTextWrapper, List<SampleCodeCacheEntry>> suggestions = get();
                    if (suggestions != null && !suggestions.isEmpty())
                    {
                      model.setSearchedEntry(suggestions);
                      view.modelChanged();
                    }
                    codeChanged = false;

                    startWebSearch(validSuggestedStrings);


                  }

                  catch (Exception e)
                  {
                    e.printStackTrace();
                  }

                }
              };

            woker.execute();
          }
          else
          {
            System.out.println("Search Stoped...");
          }
        }
        catch (Exception ex)
        {
          ex.printStackTrace();
        }


      }
    };
  }

  private static void startWebSearch(final SuggestionTextWrapper[] validSuggestedStrings)
  {

    SwingWorker<Hashtable<SuggestionTextWrapper, List<SampleCodeCacheEntry>>, Integer> woker = new
      SwingWorker<Hashtable<SuggestionTextWrapper, List<SampleCodeCacheEntry>>, Integer>()
      {

        @Override
        protected Hashtable<SuggestionTextWrapper, List<SampleCodeCacheEntry>> doInBackground() throws Exception
        {
          final int snippetID = currentSnippet.getID();
          if (validSuggestedStrings != null && validSuggestedStrings.length > 0)
          {
            SearchController.getInstance().doSearchWebAndNotify(validSuggestedStrings, new SearchNotifyListener()
            {
              @Override
              public void notifySearch(SuggestionTextWrapper webSearchString, SampleCodeCacheEntry sampleCodeCacheEntry)
              {
                if (snippetID == currentSnippet.getID())
                {
                  view.addEntry(webSearchString, sampleCodeCacheEntry);
//                  model.addEntry(webSearchString,sampleCodeCacheEntry);
//                  view.modelChanged();
                  System.out.println("Adding web entry ....");
                }

              }
            });
          }

          return null;
        }

        @Override
        protected void done()
        {

        }
      };

    woker.execute();

  }


  public static String[] clean(final String passage)
  {

    String firstArray[] = passage.split("\\W");
    List<String> list = new ArrayList<String>();

    String pattern = "(?U)\\b\\p{Lu}\\p{L}*\\b";

    for (String s : firstArray)
    {
      if (s != null && s.length() > 0)
      {

        if (s.matches(pattern))
        {
          list.add(s);
        }
      }
    }

    removeDuplicates(list);
    return list.toArray(new String[list.size()]);

  }

  public static List<String> removeDuplicates(List<String> list)
  {

    Set setItems = new LinkedHashSet(list);
    list.clear();
    list.addAll(setItems);

    return list;
  }

}
