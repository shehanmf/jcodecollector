package ecompilerlab.util;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/28/13
 * Time: 7:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class IconLib
{

  public final static ImageIcon favoriteIco = new ImageIcon("resources/FAVOURITE.png");

  public final static ImageIcon browserView = new ImageIcon("resources/FULL_VIEW.png");

  public final static ImageIcon extView = new ImageIcon("resources/OPEN_VIEW.png");

  private static IconLib instance = new IconLib();

  public static IconLib getInstance()
  {
    return instance;
  }
}
