package ecompilerlab.component;

import jcodecollector.common.bean.Snippet;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: shehan.fernando
 * Date: 6/1/13
 * Time: 6:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompilationDetailTextPanel extends JPanel implements SnippetChangeSupport
{


    private CompilerListener compilerListner;

    public CompilationDetailTextPanel() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        btnCompile = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtCompoileInfo = new JTextPane();
        txtCompoileInfo.setEditable(false);
        setLayout(new java.awt.GridBagLayout());

        btnCompile.setEnabled(false);
        btnCompile.setText("<html><b>Compile</b><br> & <br><b>Run</b></html>");
        btnCompile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compileSnippet();
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        add(btnCompile, gridBagConstraints);

//        txtCompoileInfo.setColumns(20);
//        txtCompoileInfo.setRows(5);
        jScrollPane2.setViewportView(txtCompoileInfo);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jScrollPane2, gridBagConstraints);
    }// </editor-fold>

    private void compileSnippet() {
        this.compilerListner.fireCompileAction();
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnCompile;
    private javax.swing.JScrollPane jScrollPane2;
    private JTextPane txtCompoileInfo;

    @Override
    public void setSnippet(Snippet snippet) {
        btnCompile.setEnabled(true);

        clear();
        append(new TextEntry("Snippet set.... (" + snippet.getName() + ")", TextEntry.ENTRY_TYPE.ERROR));

    }

    @Override
    public Snippet getSnippet() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void createNewSnippet() {
        btnCompile.setEnabled(false);
    }

    public void addCompilerListner(CompilerListener compilerListner) {

        this.compilerListner = compilerListner;
    }


    private void clear()
    {
        txtCompoileInfo.setText("");
    }

    private void append(TextEntry textEntry)
    {
        Color color;
        switch (textEntry.getEntrType())
        {
            case ERROR:
                color = Color.RED;
                break;
            case MESSAGE:
                color = Color.GREEN;




                break;
            case INFO:
            default:
                color = Color.BLACK;
        }
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = txtCompoileInfo.getDocument().getLength();
        txtCompoileInfo.setEditable(true);
        txtCompoileInfo.setCaretPosition(len);
        txtCompoileInfo.setCharacterAttributes(aset, false);
        txtCompoileInfo.replaceSelection(textEntry.getText());
        txtCompoileInfo.setEditable(false);
    }



}
