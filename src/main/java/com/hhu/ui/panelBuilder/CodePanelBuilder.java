package com.hhu.ui.panelBuilder;

import java.awt.BorderLayout;
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

import com.hhu.ui.Colors;
import com.hhu.util.ThemeStyler;
import com.hhu.util.compiler.Compiler;

/**
 * Returns the CodePanel. A Swing Representation of a given Code-String
 */
class CodePanelBuilder {

    static final String TARGET_LINE_PROPERTY = "codepanel.targetLineNumber";

    private static final Font CODE_FONT = createFont();

    private CodePanelBuilder() {
    }

    /**
     * Returns a JPanel, that holds a Syntax-Highlighted form of the given Code with
     * the given line highlighted.
     */
    public static JPanel create(String code, int lineNumber) {
        String newCode = removeRecordCalls(code);

        RSyntaxTextArea textArea = createTextArea(newCode, lineNumber);
        RTextScrollPane scrollPane = new RTextScrollPane(textArea);

        JLabel headline = new JLabel("Source Code", SwingConstants.CENTER);
        ThemeStyler.styleHeadline(headline);

        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBorder(new TitledBorder("Code"));
        ThemeStyler.styleCodeCard(panel);

        panel.add(headline, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        ThemeStyler.styleScrollPane(scrollPane);

        return panel;
    }

    private static String removeRecordCalls(String code) {
        return code.replace(Compiler.getRecordCallReplacement(), "");
    }

    /**
     * Returns a Syntax-Highlighted TextArea of a given String
     */
    private static RSyntaxTextArea createTextArea(String code, int lineNumber) {

        RSyntaxTextArea textArea = new RSyntaxTextArea();

        textArea.setText(code);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCodeFoldingEnabled(true);
        textArea.setEditable(true);
        textArea.setAntiAliasingEnabled(true);
        textArea.setFont(CODE_FONT);
        textArea.setMargin(new Insets(10, 10, 10, 50));
        textArea.setHighlightCurrentLine(false);
        textArea.setRoundedSelectionEdges(true);
        textArea.putClientProperty(TARGET_LINE_PROPERTY, lineNumber);

        highlightLine(textArea, lineNumber);

        return textArea;
    }

    /**
     * Adds a line Highlight at a given line
     */
    private static void highlightLine(RSyntaxTextArea textArea, int lineNumber) {
        if (lineNumber < 0) {
            return;
        }

        try {
            textArea.addLineHighlight(lineNumber, Colors.LINE_HIGHLIGHT);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private static Font createFont() {
        Font fira = new Font("Fira Code", Font.PLAIN, 18);
        // check if FiraCode exists, otherwise take MONOSPACED
        return fira.getFamily().equalsIgnoreCase("Fira Code")
                ? fira
                : new Font(Font.MONOSPACED, Font.PLAIN, 18);
    }
}