package com.hhu.views.panelBuilder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

final class CodePanelBuilder {

    private static final Font HEADLINE_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 20);

    private static final Font CODE_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 17);

    private static final Color CURRENT_LINE_COLOR = new Color(230, 230, 255);
    
    private static final Color LINE_HIGHLIGHT_COLOR = new Color(255, 249, 196);

    private CodePanelBuilder() {}

    public static JPanel create(String code) {
        return create(code, -1);
    }

    public static JPanel create(String code, int lineNumber) {
        RSyntaxTextArea textArea = createTextArea(code, lineNumber);
        RTextScrollPane scrollPane = new RTextScrollPane(textArea);

        JLabel headline = new JLabel("Source Code", SwingConstants.CENTER);
        headline.setFont(HEADLINE_FONT);

        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBorder(new TitledBorder("Code"));

        panel.add(headline, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private static RSyntaxTextArea createTextArea(String code, int lineNumber) {

        RSyntaxTextArea textArea = new RSyntaxTextArea();

        textArea.setText(code);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCodeFoldingEnabled(true);
        textArea.setFont(CODE_FONT);
        textArea.setEditable(false);
        textArea.setAntiAliasingEnabled(true);
        textArea.setCurrentLineHighlightColor(CURRENT_LINE_COLOR);
        textArea.setMargin(new Insets(10, 10, 10, 50));

        highlightLineIfNeeded(textArea, lineNumber);

        return textArea;
    }

    private static void highlightLineIfNeeded( RSyntaxTextArea textArea, int lineNumber) {
        if (lineNumber < 0) {
            return;
        }

        try {
            textArea.addLineHighlight(lineNumber, LINE_HIGHLIGHT_COLOR);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}