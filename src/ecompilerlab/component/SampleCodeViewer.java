package ecompilerlab.component;

import ecompilerlab.util.IconLib;
import ecompilerlab.wrappers.SampleCodeCacheEntry;
import ecompilerlab.wrappers.SuggestionTextWrapper;
import jcodecollector.Loader;
import org.jdesktop.swingx.JXTaskPane;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/28/13
 * Time: 5:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class SampleCodeViewer extends JXTaskPane
{

  private final SampleCodeCacheEntry sourceCode;

  private final SuggestionTextWrapper wrapper;

  private JButton btnFav;

  private JButton btnExtView;

  private JButton btnBrowser;

  private CodeViewVisibleListener viewVisibleListener;

  public SampleCodeViewer(SuggestionTextWrapper suggestionTextWrapper, final SampleCodeCacheEntry sourceCode)
  {
    super("Sample for (" + suggestionTextWrapper.getDisplayName() + ")");

    this.setCollapsed(true);
    this.wrapper = suggestionTextWrapper;
    this.sourceCode = sourceCode;

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));


    btnFav = new JButton(IconLib.favoriteIco);
    btnFav.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        addToFavorite();
      }
    });
    btnFav.setSize(new Dimension(30, 30));
    btnFav.setPreferredSize(new Dimension(30, 30));
    btnFav.setMaximumSize(new Dimension(30, 30));
    buttonPanel.add(btnFav);


    btnExtView = new JButton(IconLib.extView);
    btnExtView.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        openExternalView(sourceCode.getHtmlCode());
      }
    });
    btnExtView.setSize(new Dimension(30, 30));
    btnExtView.setPreferredSize(new Dimension(30, 30));
    btnExtView.setMaximumSize(new Dimension(30, 30));
    buttonPanel.add(btnExtView);

    btnBrowser = new JButton(IconLib.browserView);
    btnBrowser.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        openInBrowserWindow(sourceCode.getUrl());
      }
    });
    btnBrowser.setSize(new Dimension(30, 30));
    btnBrowser.setPreferredSize(new Dimension(30, 30));
    btnBrowser.setMaximumSize(new Dimension(30, 30));
    buttonPanel.add(btnBrowser);


    add(buttonPanel);
    add(getHtmlComp(sourceCode.getHtmlCode()));
  }


  private JEditorPane getHtmlComp(String htmlSnippet)
  {
    JEditorPane jEditorPane = new JEditorPane();
    jEditorPane.setEditable(false);

    HTMLEditorKit kit = new HTMLEditorKit();
    jEditorPane.setEditorKit(kit);

    jEditorPane.setText(htmlSnippet);

    return jEditorPane;
  }


  private void addToFavorite()
  {
    new TagSourceDialog(Loader.getFrameForDialogs()).setVisible(true);
  }

  private void openExternalView(String htmlCode)
  {

    new CodeExpandDialog(Loader.getFrameForDialogs(), htmlCode).setVisible(true);
  }


  private void openInBrowserWindow(String url)
  {
    new ECompilerCodeBrowser(Loader.getFrameForDialogs(), url).setVisible(true);
  }


  interface CodeViewVisibleListener
  {

    public void setVisible(boolean visible);

  }


  public CodeViewVisibleListener getViewVisibleListener()
  {
    if (viewVisibleListener == null)
    {
      viewVisibleListener = new CodeViewVisibleListener()
      {
        @Override
        public void setVisible(boolean visible)
        {
          SampleCodeViewer.this.setVisible(visible);
        }
      };
    }
    return viewVisibleListener;
  }
}
