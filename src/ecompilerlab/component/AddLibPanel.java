package ecompilerlab.component;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Shehan
 * Date: 6/2/13
 * Time: 6:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class AddLibPanel extends JPanel {

    private SourceList libList = new SourceList();

    private JButton btnAddLib = new JButton("Add libraries");

    public AddLibPanel()
    {
        JPanel tmpPanel = new JPanel();
        tmpPanel.setLayout(new BorderLayout());
        JComponent component = libList.getComponent();
        component.setSize(new Dimension(200,200));
        component.setPreferredSize(new Dimension(200,200));
        component.setMaximumSize(new Dimension(200,200));
        tmpPanel.add(component,BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(tmpPanel,BorderLayout.SOUTH);
        add(btnAddLib,BorderLayout.NORTH);
//        add(libList.getComponent(), new GridBagConstraints(0,0,0,0,1.0,1.0,GridBagConstraints.CENTER,
//                GridBagConstraints.BOTH,new Insets(0,0,0,0),0,0));
//        add(btnAddLib, new GridBagConstraints(0,1,0,0,0.0,0.0,GridBagConstraints.CENTER,
//                GridBagConstraints.BOTH,new Insets(0,0,0,0),0,0));
    }
}
