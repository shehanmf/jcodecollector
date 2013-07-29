package ecompilerlab.component;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/28/13
 * Time: 8:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class CodeExpandDialog extends JDialog
{

  private final String code;

  public CodeExpandDialog(Frame owner, String code)
  {
    super(owner, false);

    setUndecorated(true);
    JRootPane rootPane = getRootPane();
    rootPane.setWindowDecorationStyle(JRootPane.FRAME);
    rootPane.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
    rootPane.putClientProperty("Quaqua.RootPane.isVertical", Boolean.FALSE);
    rootPane.putClientProperty("Quaqua.RootPane.isPalette", Boolean.TRUE);
    setLocationRelativeTo(owner);

    this.code = code;

    setPreferredSize(new Dimension(1024, 600));

    initComponents();
    pack();
  }

  private void initComponents()
  {
    setLayout(new GridBagLayout());

    GridBagConstraints cons1 = new GridBagConstraints();
    cons1.gridy = 0;
    cons1.gridx = 0;

    cons1.weighty = 1;
    cons1.weightx = 1;

    cons1.fill = GridBagConstraints.BOTH;

    add(new JScrollPane(getHtmlComp(this.code)),cons1);

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

}
