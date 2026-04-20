package com.hhu.views.panelBuilder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
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

final class CodePanelBuilder {

    static final String TARGET_LINE_PROPERTY = "codepanel.targetLineNumber";

    private static final Font CODE_FONT = new Font("Fira Code", Font.PLAIN, 18);

    private static final Color LINE_HIGHLIGHT_FALLBACK = new Color(74, 7, 39);

    private CodePanelBuilder() {
    }

    public static JPanel create(String code) {
        return create(code, -1);
    }

    public static JPanel create(String code, int lineNumber) {
        RSyntaxTextArea textArea = createTextArea(code, lineNumber);
        RTextScrollPane scrollPane = new RTextScrollPane(textArea);

        JLabel headline = new JLabel("Source Code", SwingConstants.CENTER);
        ThemeStyler.styleAccentLabel(headline);

        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBorder(new TitledBorder("Code"));
        ThemeStyler.styleModernCard(panel);

        panel.add(headline, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        ThemeStyler.styleScrollPane(scrollPane);

        return panel;
    }

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

        highlightLineIfNeeded(textArea, lineNumber);
        scrollToLineIfNeeded(textArea, lineNumber);

        return textArea;
    }

    private static void scrollToLineIfNeeded(RSyntaxTextArea textArea, int lineNumber) {
        if (lineNumber < 0) {
            return;
        }

        SwingUtilities.invokeLater(() -> {
            try {
                int lineCount = textArea.getLineCount();
                int safeLine = Math.max(0, Math.min(lineNumber, lineCount - 1));
                int lineStartOffset = textArea.getLineStartOffset(safeLine);

                textArea.setCaretPosition(lineStartOffset);

                Rectangle lineBounds = textArea.modelToView(lineStartOffset);
                if (lineBounds != null) {
                    textArea.scrollRectToVisible(lineBounds);
                }
            } catch (BadLocationException ignored) {
                // line is out of range, nothing to scroll
            }
        });
    }

    private static void highlightLineIfNeeded(RSyntaxTextArea textArea, int lineNumber) {
        if (lineNumber < 0) {
            return;
        }

        try {
            textArea.addLineHighlight(lineNumber, LINE_HIGHLIGHT_FALLBACK);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private static void applyDarkSyntaxTheme(RSyntaxTextArea textArea) {
        try {
            Theme theme = Theme.load(CodePanelBuilder.class.getResourceAsStream(
                    "/org/fife/ui/rsyntaxtextarea/themes/monokai.xml"));
            theme.apply(textArea);
        } catch (IOException | NullPointerException ignored) {
            textArea.setBackground(ThemeStyler.uiColor("TextArea.background", new Color(42, 42, 42)));
            textArea.setForeground(ThemeStyler.uiColor("TextArea.foreground", new Color(219, 220, 222)));
            textArea.setCaretColor(ThemeStyler.uiColor("TextArea.caretForeground", new Color(255, 255, 255)));
        }
    }
}