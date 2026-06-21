package com.hhu.ui.panels.codepanel;

import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayDeque;
import java.util.Deque;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

/**
 * Finds the source-code editor in Swing components and scrolls it to the
 * highlighted line.
 */
public class CodePanelScroller {

    private static final int SCROLL_RETRIES = 4;
    private static final String TARGET_LINE_PROPERTY = "codepanel.targetLineNumber";

    /*
     * Changes the scroll Positon of the codePanel to be the highlited line.
     *
     * Needs to happen after frame.setVisible, since the panelHeight is needed for
     * the calculation of the position. Therefore, this method cannot be inside
     * CodePanelBuilder (The Class that builds the SyntaxTextArea)
     */
    public void scrollToHighlightedLine(JPanel codePanel) {
        RSyntaxTextArea textArea = findSyntaxTextArea(codePanel);
        if (textArea != null) {
            scrollToTargetLine(textArea, SCROLL_RETRIES);
        }
    }

    public String readCode(Component root) {
        RSyntaxTextArea codeArea = findSyntaxTextArea(root);
        if (codeArea == null) {
            return "";
        }
        return codeArea.getText();
    }

    public RSyntaxTextArea findSyntaxTextArea(Component root) {
        Deque<Component> stack = new ArrayDeque<>();
        stack.push(root);

        // Stack Tiefensuche durch alle Panel und deren Kinder
        while (!stack.isEmpty()) {
            Component current = stack.pop();
            if (current instanceof RSyntaxTextArea textArea) {
                return textArea;
            }
            if (current instanceof Container container) {
                for (Component child : container.getComponents()) {
                    stack.push(child);
                }
            }
        }
        return null;
    }

    private void scrollToTargetLine(RSyntaxTextArea textArea, int retries) {
        SwingUtilities.invokeLater(() -> tryScrollToTargetLine(textArea, retries));
    }

    private void tryScrollToTargetLine(RSyntaxTextArea textArea, int retries) {
        // check if the UI is ready to be measured
        // if visibleRect.height = 0 , then the textArea is not visible yet
        Rectangle visibleRect = textArea.getVisibleRect();
        if (!textArea.isShowing() || visibleRect.height <= 0) {
            retryWhenPossible(textArea, retries);
            return;
        }

        try {
            Rectangle targetView = calculateTargetView(textArea, visibleRect);
            if (targetView != null) {
                textArea.scrollRectToVisible(targetView);
            }
        } catch (BadLocationException ignored) {
            // Outside document range = nothing to scroll
        }
    }

    private void retryWhenPossible(RSyntaxTextArea textArea, int retries) {
        if (retries > 0) {
            // Swing is lazy; it only knows the size of the text Area, when the component is
            // shown. The retry fixes accessing the height before it's known.
            scrollToTargetLine(textArea, retries - 1);
        }
    }

    private Rectangle calculateTargetView(RSyntaxTextArea textArea, Rectangle visibleRect) throws BadLocationException {
        // get line to scroll to
        Integer target = (Integer) textArea.getClientProperty(TARGET_LINE_PROPERTY);
        if (target == null) {
            return null;
        }

        // make sure line isnt negative or higher than number of existing lines
        int safeLine = clampToDocumentLine(textArea, target);

        // turn LineStart into Geometric Coordinates (Pixels on Screen)
        Rectangle2D lineBounds = textArea.modelToView2D(textArea.getLineStartOffset(safeLine));
        if (lineBounds == null) {
            return null;
        }

        int scrollY = calculateCenteredScrollY(textArea, visibleRect, lineBounds);

        // scroll to this rectangle, that is placed above the line to highlight
        return new Rectangle(0, scrollY, (int) visibleRect.getWidth(), (int) visibleRect.getHeight());
    }

    private int clampToDocumentLine(RSyntaxTextArea textArea, int line) {
        int maxLineIndex = textArea.getLineCount() - 1;
        return Math.max(0, Math.min(line, maxLineIndex));
    }

    private int calculateCenteredScrollY(RSyntaxTextArea textArea, Rectangle visibleRect, Rectangle2D lineBounds) {
        // Calculate the center position
        // Math: Start at line top -> Move up by half a screen -> Move down by half a
        // line
        double centeredY = lineBounds.getY() - (visibleRect.getHeight() / 2) + (lineBounds.getHeight() / 2);

        // Confirm it's valid scroll position so we don't go out of bounds
        int maxPossibleScroll = textArea.getHeight() - (int) visibleRect.getHeight();
        return Math.max(0, Math.min((int) centeredY, maxPossibleScroll));
    }
}
