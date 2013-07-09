package ecompilerlab.component;

import jcodecollector.common.bean.Snippet;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created with IntelliJ IDEA.
 * User: shehan.fernando
 * Date: 6/1/13
 * Time: 6:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompilationDetailTextPanel extends JPanel implements SnippetChangeSupport
{

  // Variables declaration - do not modify
  private javax.swing.JButton btnCompile;

  private javax.swing.JScrollPane jScrollPane2;

  private JTextPane txtCompoileInfo;

  public CompilationDetailTextPanel()
  {
    initComponents();
  }


  @SuppressWarnings("unchecked")
  private void initComponents()
  {
    java.awt.GridBagConstraints gridBagConstraints;

    btnCompile = new javax.swing.JButton();
    jScrollPane2 = new javax.swing.JScrollPane();
    txtCompoileInfo = new JTextPane();
    txtCompoileInfo.setEditable(false);


    JPopupMenu menu = new JPopupMenu();

    JMenuItem menuItemClear = new JMenuItem( "Clear");
    menuItemClear.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        clear();
      }
    });
    menu.add(menuItemClear);
    txtCompoileInfo.setComponentPopupMenu(menu);
//    add(menu);

    addMouseListener(
      new PopupTriggerMouseListener(
        menu,
        this
      )
    );


    setLayout(new java.awt.GridBagLayout());

    btnCompile.setEnabled(false);
    btnCompile.setText("<html><b>Compile</b><br> & <br><b>Run</b></html>");
    btnCompile.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        compileAndRunSnippet();
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
    gridBagConstraints.weighty = 1.0;
    add(btnCompile, gridBagConstraints);

//        txtCompoileInfo.setColumns(20);
//        txtCompoileInfo.setRows(5);
    jScrollPane2.setViewportView(txtCompoileInfo);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    add(jScrollPane2, gridBagConstraints);
  }// </editor-fold>

  private void compileAndRunSnippet()
  {
    OnlineCompilerTask.getInstance().doCompileAndRun();
  }


  @Override
  public void setSnippet(Snippet snippet)
  {
    btnCompile.setEnabled(true);

    clear();
    append(new TextEntry("Snippet set.... (" + snippet.getName() + ")", true ,TextEntry.ENTRY_TYPE.ERROR));

  }

  @Override
  public Snippet getSnippet()
  {
    return null;
  }

  @Override
  public void createNewSnippet()
  {
    btnCompile.setEnabled(false);
  }

  private void clear()
  {
    txtCompoileInfo.setText("");
  }

  public void append(TextEntry textEntry)
  {
    Color color;
    switch (textEntry.getEntrType())
    {
      case ERROR:
        color = Color.RED;
        break;
      case MESSAGE:
        color = Color.BLACK;
        break;
      case INFO:
      default:
        color = Color.BLACK;
    }
    StyleContext sc = StyleContext.getDefaultStyleContext();
    AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);

    aset = sc.addAttribute(aset, StyleConstants.FontFamily, "verdana");
    aset = sc.addAttribute(aset, StyleConstants.FontSize, 12);
    aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

    int len = txtCompoileInfo.getDocument().getLength();
    txtCompoileInfo.setEditable(true);
    txtCompoileInfo.setCaretPosition(len);
    txtCompoileInfo.setCharacterAttributes(aset, false);
    txtCompoileInfo.replaceSelection((textEntry.isNewLine() ? "\n" : "") + textEntry.getText());
//    txtCompoileInfo.set
    txtCompoileInfo.setEditable(false);
  }


  public static class PopupTriggerMouseListener extends MouseAdapter
  {
    private JPopupMenu popup;

    private JComponent component;

    public PopupTriggerMouseListener(JPopupMenu popup, JComponent component)
    {
      this.popup = popup;
      this.component = component;
    }

    //some systems trigger popup on mouse press, others on mouse release, we want to cater for both
    private void showMenuIfPopupTrigger(MouseEvent e)
    {
      if (e.isPopupTrigger())
      {
        popup.show(component, e.getX() + 3, e.getY() + 3);
      }
    }

    //according to the javadocs on isPopupTrigger, checking for popup trigger on mousePressed and mouseReleased
    //should be all  that is required
    //public void mouseClicked(MouseEvent e)
    //{
    //    showMenuIfPopupTrigger(e);
    //}

    public void mousePressed(MouseEvent e)
    {
      showMenuIfPopupTrigger(e);
    }

    public void mouseReleased(MouseEvent e)
    {
      showMenuIfPopupTrigger(e);
    }
  }

}
