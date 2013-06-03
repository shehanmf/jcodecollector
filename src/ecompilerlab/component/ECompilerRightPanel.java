package ecompilerlab.component;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 6/2/13
 * Time: 6:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class ECompilerRightPanel extends JPanel
{
    private JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

    private AddLibPanel addLibPanel = new AddLibPanel();

    public ECompilerRightPanel()
    {

        split.setBorder(null);
        split.setDividerSize(1);
        split.setContinuousLayout(true);
        split.setDividerLocation(220);
        split.setTopComponent(addLibPanel);
        split.setBottomComponent(new JLabel("Test crawler"));

        setLayout(new BorderLayout());
        add(split,BorderLayout.CENTER);

    }
}
