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

    setLayout(new GridBagLayout());

    compileTextArea = new JTextArea();
    infoScroll = new JScrollPane(compileTextArea);
    this.add(infoScroll,new GridBagConstraints(0,0,0,0,1.0,1.0,GridBagConstraints.CENTER,
            GridBagConstraints.BOTH,new Insets(0,0,0,0),0,0));

  }
}
