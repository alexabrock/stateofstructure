package com.hhu.views.panelBuilder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

final class CodePanelBuilder {

    private CodePanelBuilder() {
    }

    static JPanel create(String code) {
        RSyntaxTextArea textArea = createTextArea(code);
        RTextScrollPane scrollPane = new RTextScrollPane(textArea);

        JLabel headline = new JLabel("Source Code", SwingConstants.CENTER);
        headline.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBorder(new TitledBorder("Code"));
        panel.add(headline, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    static RSyntaxTextArea createTextArea(String code) {
        RSyntaxTextArea textArea = new RSyntaxTextArea();
        textArea.setText(code);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCodeFoldingEnabled(true);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 17));
        textArea.setEditable(false);
        textArea.setAntiAliasingEnabled(true);
        textArea.setCurrentLineHighlightColor(new Color(230, 230, 255));

        /* try {
            textArea.addLineHighlight(6, Color.YELLOW);
        } catch (BadLocationException e) {
            e.printStackTrace();
        } */

        return textArea;
    }
}