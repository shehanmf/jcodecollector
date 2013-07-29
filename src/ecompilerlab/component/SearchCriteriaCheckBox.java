package ecompilerlab.component;

import ecompilerlab.wrappers.SuggestionTextWrapper;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/28/13
 * Time: 5:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchCriteriaCheckBox extends JCheckBox implements ItemListener
{

  private SuggestionTextWrapper suggestionTextWrapper;

  private List<SampleCodeViewer.CodeViewVisibleListener> listeners = new ArrayList<SampleCodeViewer.CodeViewVisibleListener>();

  public SearchCriteriaCheckBox(SuggestionTextWrapper wrapper)
  {
    super(wrapper.getDisplayName());
    this.suggestionTextWrapper = wrapper;
    this.setSelected(true);
    this.addItemListener(this);
  }


  public void addListener(SampleCodeViewer.CodeViewVisibleListener listener)
  {
    listeners.add(listener);
  }

  @Override
  public void itemStateChanged(ItemEvent e)
  {

    if(isSelected())
    {
      for (SampleCodeViewer.CodeViewVisibleListener listener : listeners)
      {
        listener.setVisible(true);
      }
    }
    else
    {
      for (SampleCodeViewer.CodeViewVisibleListener listener : listeners)
      {
        listener.setVisible(false);
      }
    }

  }
}
