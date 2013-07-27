package ecompilerlab.test;

/**
 * Created with IntelliJ IDEA.
 * User: shehan.fernando
 * Date: 7/26/13
 * Time: 11:10 PM
 * To change this template use File | Settings | File Templates.
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.html.HTMLDocument;

import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;


/**
 * A demo for the {@code JXTaskPane}.
 *
 * @author Karl George Schaefer
 * @author l2fprod (original JXTaskPaneDemoPanel)
 */

@SuppressWarnings("serial")
public class TaskPaneDemo extends JPanel {
    private JXTaskPane systemGroup;
    private JXTaskPane officeGroup;
    private JXTaskPane seeAlsoGroup;
    private JXTaskPane detailsGroup;

    /**
     * main method allows us to run as a standalone demo.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new TaskPaneDemo());
                frame.setPreferredSize(new Dimension(800, 600));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public TaskPaneDemo() {
        super(new BorderLayout());

        createTaskPaneDemo();

//        bind();
    }

    private void createTaskPaneDemo() {
        JXTaskPaneContainer tpc = new JXTaskPaneContainer();

        // "System" GROUP
        systemGroup = new JXTaskPane();
        systemGroup.setName("systemGroup");
        tpc.add(systemGroup);

        // "Office" GROUP
        officeGroup = new JXTaskPane();
        officeGroup.setName("officeGroup");
        tpc.add(officeGroup);

        // "SEE ALSO" GROUP and ACTIONS
        seeAlsoGroup = new JXTaskPane();
        seeAlsoGroup.setName("seeAlsoGroup");
        tpc.add(seeAlsoGroup);

        // "Details" GROUP
        detailsGroup = new JXTaskPane();
        detailsGroup.setName("detailsGroup");

        //TODO better injection for editor area
        JEditorPane area = new JEditorPane("text/html", "<html>");
        area.setName("detailsArea");

        area.setFont(UIManager.getFont("Label.font"));

        Font defaultFont = UIManager.getFont("Button.font");

        String stylesheet = "body { margin-top: 0; margin-bottom: 0; margin-left: 0; margin-right: 0; font-family: "
                + defaultFont.getName()
                + "; font-size: "
                + defaultFont.getSize()
                + "pt;  }"
                + "a, p, li { margin-top: 0; margin-bottom: 0; margin-left: 0; margin-right: 0; font-family: "
                + defaultFont.getName()
                + "; font-size: "
                + defaultFont.getSize()
                + "pt;  }";
        if (area.getDocument() instanceof HTMLDocument) {
            HTMLDocument doc = (HTMLDocument)area.getDocument();
            try {
                doc.getStyleSheet().loadRules(new java.io.StringReader(stylesheet),
                        null);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        detailsGroup.add(area);

        tpc.add(detailsGroup);

        add(new JScrollPane(tpc));
    }

//    private void bind() {
//        ApplicationActionMap map = Application.getInstance().getContext().getActionMap(this);
//
//        systemGroup.add(map.get("email"));
//        systemGroup.add(map.get("delete"));
//
//        officeGroup.add(map.get("write"));
//
//        seeAlsoGroup.add(map.get("exploreInternet"));
//        seeAlsoGroup.add(map.get("help"));
//    }


}