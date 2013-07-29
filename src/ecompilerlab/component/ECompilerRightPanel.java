package ecompilerlab.component;

import jcodecollector.common.bean.Snippet;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 6/2/13
 * Time: 6:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class ECompilerRightPanel extends JPanel implements SnippetChangeSupport
{
  private JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

  private AddLibPanel addLibPanel;

  private SuggestionTabPanel suggestionPanel;


  public ECompilerRightPanel()
  {
    this.addLibPanel = new AddLibPanel();
    this.suggestionPanel = new SuggestionTabPanel();
    split.setBorder(null);
    split.setDividerSize(1);
    split.setContinuousLayout(true);
    split.setDividerLocation(230);
    split.setTopComponent(addLibPanel);
    split.setBottomComponent(suggestionPanel);


    ArrayList<String> facrefs = new ArrayList<String>();


    setLayout(new BorderLayout());
    add(split, BorderLayout.CENTER);

  }

  public void setSnippet(Snippet snippet)
  {
    addLibPanel.setSnippet(snippet);
    suggestionPanel.setSnippet(snippet);
  }

  public Snippet getSnippet()
  {
    return addLibPanel.getSnippet();
  }

  public void createNewSnippet()
  {
    addLibPanel.createNewSnippet();
  }
}
