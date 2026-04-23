package com.hhu.views.panelBuilder;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;

import com.hhu.views.Colors;

/*
 * Returns the CodePanel. A Swing Representation of a given Code-String
 */
class CodePanelBuilder {

    static final String TARGET_LINE_PROPERTY = "codepanel.targetLineNumber";

    private static final Font CODE_FONT = new Font("Fira Code", Font.PLAIN, 18);

    private CodePanelBuilder() {
    }

    /*
     * Returns a JPanel, that holds a Syntax-Highlighted form of the given Code with
     * no line highlighted.
     */
    public static JPanel create(String code) {
        return create(code, -1);
    }

    /*
     * Returns a JPanel, that holds a Syntax-Highlighted form of the given Code with the given line highlighted.
     */
    public static JPanel create(String code, int lineNumber) {
        RSyntaxTextArea textArea = createTextArea(code, lineNumber);
        RTextScrollPane scrollPane = new RTextScrollPane(textArea);

        JLabel headline = new JLabel("Source Code", SwingConstants.CENTER);
        ThemeStyler.styleHeadline(headline);

        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBorder(new TitledBorder("Code"));
        ThemeStyler.styleModernCard(panel);

        panel.add(headline, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        ThemeStyler.styleScrollPane(scrollPane);

        return panel;
    }

    /*
     * Returns a Syntax-Highlighted TextArea of a given String
     */
    private static RSyntaxTextArea createTextArea(String code, int lineNumber) {

        RSyntaxTextArea textArea = new RSyntaxTextArea();

        textArea.setText(code);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCodeFoldingEnabled(true);
        textArea.setEditable(false);
        textArea.setAntiAliasingEnabled(true);
        applyDarkSyntaxTheme(textArea);
        textArea.setFont(CODE_FONT);
        textArea.setMargin(new Insets(10, 10, 10, 50));
        textArea.setHighlightCurrentLine(false);
        textArea.setRoundedSelectionEdges(true);
        textArea.putClientProperty(TARGET_LINE_PROPERTY, lineNumber);

        highlightLine(textArea, lineNumber);

        return textArea;
    }

    /*
     * Adds a lineHighlight at a given line
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
    
    /*
     * Changes the Colors to be using DarkMode
     */
    private static void applyDarkSyntaxTheme(RSyntaxTextArea textArea) {
        try {
            //get darkMode theme from Libary
            Theme theme = Theme.load(CodePanelBuilder.class.getResourceAsStream(
                    "/org/fife/ui/rsyntaxtextarea/themes/monokai.xml"));
            theme.apply(textArea);
        } catch (IOException e) {
            //Fallback colors
            textArea.setBackground(Colors.TEXT_BACKGROUND_COLOR);
            textArea.setForeground(Colors.TEXT_FOREGROUND_COLOR);
            textArea.setCaretColor(Colors.CARET_COLOR);
        }
    }
}