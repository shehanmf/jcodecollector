package ecompilerlab.component.controller;

import ecompilerlab.component.WebSuggestionPanel;
import ecompilerlab.component.model.WebSuggestionModel;

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

  private static WebSuggestionModel model ;

  private static WebSuggestionPanel view;
  public static WebSuggestionController getInstance()
  {

    if(instance == null)
    {
      instance = new WebSuggestionController();
      model = new WebSuggestionModel();
      view = new WebSuggestionPanel(model);
    }
    return instance;
  }

  private WebSuggestionController()
  {
  }
}
