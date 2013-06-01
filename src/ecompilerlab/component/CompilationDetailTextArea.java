package ecompilerlab.component;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: shehan.fernando
 * Date: 6/1/13
 * Time: 6:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompilationDetailTextArea extends JPanel
{

  private JScrollPane infoScroll;
  private JTextArea compileTextArea;


  public CompilationDetailTextArea()
  {
    infoScroll = new JScrollPane();
    setLayout(new GridBagLayout());

    compileTextArea = new JTextArea();
//    getViewport().add(compileTextArea);
  }
}
