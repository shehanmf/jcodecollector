package ecompilerlab.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private Frame parent ;

    public AddLibPanel(final Frame parent) {

        this.parent = parent;

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

        btnAddLib.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddLibraryDialog dialog = new AddLibraryDialog(parent,true);
                dialog.setVisible(true);

            }
        });
    }
}
