package ecompilerlab.component;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 9/10/13
 * Time: 7:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class RateComponent extends JPanel
{

  private StarRater starRater = new StarRater(6,0);
  private JLabel lblName = new JLabel();

  public RateComponent(String tag)
  {
    setLayout(new GridLayout(1,1));
    lblName.setText(tag);
    this.add(lblName);
    this.add(starRater);

  }
}
