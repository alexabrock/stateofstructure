package com.hhu.ui.application;

import static org.junit.Assert.assertEquals;

import javax.swing.JPanel;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.junit.Test;

import com.hhu.ui.panels.codepanel.CodePanelScroller;

public class CodePanelScrollerTest {
    @Test
    public void readCodeWorksWhenRootIsRSyntaxTextArea() {
        RSyntaxTextArea root = new RSyntaxTextArea();
        root.setText("Hello");
        CodePanelScroller codePanelScroller = new CodePanelScroller();

        String result = codePanelScroller.readCode(root);

        assertEquals("Hello", result);
    }
    
    @Test
    public void readCodeWorksWhenRootIsInComponent() {
        RSyntaxTextArea textArea = new RSyntaxTextArea();
        JPanel root = new JPanel();
        root.add(textArea);
        textArea.setText("Hello");
        CodePanelScroller codePanelScroller = new CodePanelScroller();

        String result = codePanelScroller.readCode(root);

        assertEquals("Hello", result);
    }

    @Test
    public void findsSyntaxTextArea() {
        RSyntaxTextArea textArea = new RSyntaxTextArea();
        JPanel root = new JPanel();
        root.add(textArea);
        CodePanelScroller codePanelScroller = new CodePanelScroller();

        RSyntaxTextArea result = codePanelScroller.findSyntaxTextArea(root);

        assertEquals(textArea, result);
    }

    
}
