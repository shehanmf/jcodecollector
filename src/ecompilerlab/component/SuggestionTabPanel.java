package ecompilerlab.component;

import ecompilerlab.component.controller.WebSuggestionController;
import jcodecollector.common.bean.Snippet;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/27/13
 * Time: 8:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class SuggestionTabPanel extends JTabbedPane
{


  public SuggestionTabPanel()
  {
    addTab("Web Suggestions", WebSuggestionController.getInstance().getUI());
    addTab("Smart Suggestions",new JPanel());
  }

  public void setSnippet(Snippet snippet)
  {
    WebSuggestionController.getInstance().snippetChanged(snippet);
  }
}
