package ecompilerlab.test;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 7/14/13
 * Time: 9:44 PM
 * To change this template use File | Settings | File Templates.
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;

import javax.swing.*;
import javax.swing.text.*;

public class VerticalHighlight {
  JFrame frame;
  JTextArea ta;

  public static void main(String args[]) {
    new VerticalHighlight();
  }

  public VerticalHighlight() {
    frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ta=new JTextArea("test11111\ntest22222\ntest33333\ntest44444\ntest55555\n");
    ta.setCaret(new MyCaret());
    JScrollPane scroll=new JScrollPane(ta);
    frame.getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add(scroll,BorderLayout.CENTER);

    JButton copy=new JButton("Copy");
    ActionListener lst=new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          Highlighter.Highlight[] selections= ta.getHighlighter().getHighlights();
          String text="";
          int cnt=selections.length;
          for (int i=0; i<cnt; i++) {
            int start=selections[i].getStartOffset();
            int end=selections[i].getEndOffset();
            String selectedText=ta.getDocument().getText(start,end-start);
            text+=selectedText+'\n';
            System.err.println(selectedText);
          }
          StringSelection ss=new StringSelection(text);
          Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss,ss);
        }

        catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    };
    copy.addActionListener(lst);
    frame.getContentPane().add(copy,BorderLayout.SOUTH);
    frame.setSize(300,200);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}

class MyCaret extends DefaultCaret {
  Point lastPoint=new Point(0,0);
  public void mouseMoved(MouseEvent e) {
    super.mouseMoved(e);
    lastPoint=new Point(e.getX(),e.getY());
  }
  public void mouseClicked(MouseEvent e) {
    super.mouseClicked(e);
    getComponent().getHighlighter().removeAllHighlights();
  }

  protected void moveCaret(MouseEvent e) {
    Point pt = new Point(e.getX(), e.getY());
    Position.Bias[] biasRet = new Position.Bias[1];
    int pos = getComponent().getUI().viewToModel(getComponent(), pt, biasRet);
    if(biasRet[0] == null)
      biasRet[0] = Position.Bias.Forward;
    if (pos >= 0) {
      setDot(pos);
      Point start=new Point(Math.min(lastPoint.x,pt.x),Math.min(lastPoint.y,pt.y));
      Point end=new Point(Math.max(lastPoint.x,pt.x),Math.max(lastPoint.y,pt.y));
      customHighlight(start,end);
    }
  }

  protected void customHighlight(Point start, Point end) {
    getComponent().getHighlighter().removeAllHighlights();
    int y=start.y;
    int firstX=start.x;
    int lastX=end.x;

    int pos1 = getComponent().getUI().viewToModel(getComponent(), new Point(firstX,y));
    int pos2 = getComponent().getUI().viewToModel(getComponent(), new Point(lastX,y));
    try {
      getComponent().getHighlighter().addHighlight(pos1,pos2,
        ((DefaultHighlighter)getComponent().getHighlighter()).DefaultPainter);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    y++;
    while (y<end.y) {
      int pos1new = getComponent().getUI().viewToModel(getComponent(), new Point(firstX,y));
      int pos2new = getComponent().getUI().viewToModel(getComponent(), new Point(lastX,y));
      if (pos1!=pos1new)  {
        pos1=pos1new;
        pos2=pos2new;
        try {
          getComponent().getHighlighter().addHighlight(pos1,pos2,
            ((DefaultHighlighter)getComponent().getHighlighter()).DefaultPainter);
        }
        catch (Exception ex) {
          ex.printStackTrace();
        }
      }
      y++;
    }
  }
}