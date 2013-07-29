package ecompilerlab.component;

import ecompilerlab.component.model.WebSuggestionModel;
import ecompilerlab.wrappers.SampleCodeCacheEntry;
import ecompilerlab.wrappers.SuggestionTextWrapper;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/27/13
 * Time: 8:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebSuggestionPanel extends JPanel
{

  private JPanel showSuggestionPanel;

  private JXTaskPaneContainer suggestionContainer;

  private WebSuggestionModel model;


  public WebSuggestionPanel(WebSuggestionModel model)
  {
    this.model = model;

    initUI();
  }

  private void initUI()
  {

    this.setLayout(new GridBagLayout());

    this.showSuggestionPanel = new JPanel();
    this.showSuggestionPanel.setLayout(new FlowLayout());
    showSuggestionPanel.setSize(new Dimension(200, 60));
    showSuggestionPanel.setMinimumSize(new Dimension(200, 60));
    showSuggestionPanel.setPreferredSize(new Dimension(200, 60));

    this.suggestionContainer = new JXTaskPaneContainer();

    GridBagConstraints cons1 = new GridBagConstraints();
    cons1.gridx = 0;
    cons1.gridy = 0;
    cons1.fill = GridBagConstraints.BOTH;
    cons1.weightx = 1;
    this.add(this.showSuggestionPanel, cons1);


    GridBagConstraints cons2 = new GridBagConstraints();
    cons2.gridx = 0;
    cons2.gridy = 1;
    cons2.fill = GridBagConstraints.BOTH;
    cons2.weightx = 1;
    cons2.weighty = 1;

    this.add(new JScrollPane(this.suggestionContainer), cons2);


  }


  public void modelChanged()
  {

    removeAllSuggested();
    final Hashtable<SuggestionTextWrapper, List<SampleCodeCacheEntry>> searchedEntry = model.getSearchedEntry();
    for (SuggestionTextWrapper suggestionTextWrapper : searchedEntry.keySet())
    {
      final List<SampleCodeCacheEntry> sampleCodeCacheEntries = searchedEntry.get(suggestionTextWrapper);

      final SearchCriteriaCheckBox comp = new SearchCriteriaCheckBox(suggestionTextWrapper);


//      comp.addItemListener();

      showSuggestionPanel.add(comp);

      for (SampleCodeCacheEntry entry : sampleCodeCacheEntries)
      {
        SampleCodeViewer viewer = new SampleCodeViewer(suggestionTextWrapper, entry);
        suggestionContainer.add(viewer);
        suggestionContainer.revalidate();

        final SampleCodeViewer.CodeViewVisibleListener viewVisibleListener = viewer.getViewVisibleListener();
        comp.addListener(viewVisibleListener);

      }
    }
  }


  private void removeAllSuggested()
  {
    showSuggestionPanel.removeAll();
    revalidate();
    this.suggestionContainer.removeAll();
    this.suggestionContainer.revalidate();
  }

  public void addEntry(SuggestionTextWrapper webSearchString, SampleCodeCacheEntry sampleCodeCacheEntry)
  {
    model.addEntry(webSearchString,sampleCodeCacheEntry);
    SampleCodeViewer viewer = new SampleCodeViewer(webSearchString, sampleCodeCacheEntry);
    suggestionContainer.add(viewer);
    suggestionContainer.revalidate();
  }

  private void updateSearchCriteria()
  {

//    showSuggestionPanel.getco
//    model.
  }

}
