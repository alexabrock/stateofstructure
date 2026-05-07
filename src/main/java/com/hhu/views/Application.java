package com.hhu.views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import com.hhu.util.DrawCalls;
import com.hhu.util.DrawStep;
import com.hhu.util.compiler.Compiler;
import com.hhu.views.panelBuilder.ThemeStyler;
import com.hhu.util.compiler.CompilationException;

public class Application {
    private static DrawCalls drawCalls;

    public static <E> void start(DrawCalls givenDrawCalls) {
        // Swing needs this
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Headless environment detected ");
            return;
        }

        ThemeStyler.applyDarkTheme();

        drawCalls = givenDrawCalls;

        SwingUtilities.invokeLater(() -> {
            /*
             * need to initialize these, so the button logik is cleaner (button can just
             * replace the existing code panels and doesnt need to check if they exist)
             */

            DrawStep firstStep = drawCalls.nextStep();

            JPanel centerPanel = createCenterPanel(firstStep);

            JPanel buttonPanel = createButtonPanel(centerPanel);

            JFrame frame = createFrame(centerPanel, buttonPanel);

            frame.setVisible(true);
        });
    }

    /*
     * returns the very outer shell of the Application.
     */

    static JFrame createFrame(JPanel centerPanel, JPanel buttonPanel) {
        JFrame frame = new JFrame("State to Structure");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(centerPanel, BorderLayout.CENTER);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(2500, 1200);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    /*
     * returns a Panel, that holds the buttons. Initializes those buttons to replace
     * the Panels, that are nested inside the centerPanel, with the next or previous
     * DrawStep
     */
    static JPanel createButtonPanel(JPanel centerPanel) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 24, 8));
        ThemeStyler.styleToolbar(buttonPanel);

        JButton prevButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");
        JButton compileButton = new JButton("Compile");
        ThemeStyler.styleNavigationButton(prevButton);
        ThemeStyler.styleNavigationButton(nextButton);
        ThemeStyler.styleNavigationButton(compileButton);

        // Initial state setzen
        prevButton.setEnabled(drawCalls.hasPrevStep());
        nextButton.setEnabled(drawCalls.hasNextStep());

        prevButton.addActionListener(e -> {
            DrawStep step = drawCalls.prevStep();
            replacePanels(centerPanel, step);

            updateButtons(prevButton, nextButton);
        });

        nextButton.addActionListener(e -> {
            DrawStep step = drawCalls.nextStep();
            replacePanels(centerPanel, step);

            updateButtons(prevButton, nextButton);
        });

        compileButton.addActionListener(e -> handleCompilation(centerPanel, compileButton));
        
        buttonPanel.add(compileButton);
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        
        return buttonPanel;
    }

    /*
     * Compiles the Text inside the codePanel and updated the Appliation.drawStep
     * It uses the SwingWorker, since the calculation of the Graphs takes a while
     * and without SwingWorker, the calculation can't happen in the background and
     * the Application freezes until the compilation is done.
     */
    private static void handleCompilation(JPanel centerPanel, JButton compileButton) {
        RSyntaxTextArea codeArea = findSyntaxTextArea(centerPanel);
        String code = codeArea.getText();

        compileButton.setEnabled(false);
        compileButton.setText("Compiling...");

        SwingWorker<DrawCalls, Void> worker = new SwingWorker<>() {

            @Override
            protected DrawCalls doInBackground() {
                // forget the old Datastructure vizualisation, if the Code is recompiled
                return Compiler.compile(code);
            }

            @Override
            protected void done() {
                try {
                    // get() returns result of doInBackground
                    drawCalls = get();

                    replacePanels(centerPanel, drawCalls.nextStep());

                } catch (ExecutionException e) {
                    /*
                     * Incase the doInBackground went wrong, a ExecutionException is thrown.
                     * The ExecutionException is a wrapper around, in this case, a
                     * CompilationException.
                     * The wrapped EXception is retrieved by e.getCause
                     */
                    Throwable cause = e.getCause();

                    JOptionPane.showMessageDialog(
                            centerPanel,
                            cause.getMessage(),
                            "Compilation Error",
                            JOptionPane.ERROR_MESSAGE);
                } catch (InterruptedException e) {
                    /* InterruptedException is thrown if the done() Thread is interrupted */
                    Thread.currentThread().interrupt();

                    JOptionPane.showMessageDialog(
                            centerPanel,
                            "Compilation interrupted",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    // finally, damit egal was passiert der Button wieder enabled wird
                } finally {
                    compileButton.setEnabled(true);
                    compileButton.setText("Compile");
                }
            }
        };

        worker.execute();
    }

    static void updateButtons(JButton prev, JButton next) {
        prev.setEnabled(drawCalls.hasPrevStep());
        next.setEnabled(drawCalls.hasNextStep());
    }

    /*
     * removes the current Panels in residesIn and replaces them with the panels
     * stored inside the DrawStep.
     * Also updates the scrollPosition of the CodePanel to match the new highlited
     * line.
     */
    static void replacePanels(JPanel residesIn, DrawStep step) {
        BorderLayout layout = (BorderLayout) residesIn.getLayout();
        // replace visualization area (memory + datastructure)
        Component oldVisualizationArea = layout.getLayoutComponent(BorderLayout.CENTER);
        residesIn.remove(oldVisualizationArea);
        residesIn.add(createVisualizationPanel(step.memory(), step.datastructure()), BorderLayout.CENTER);

        // replace method name
        Component oldMethodName = layout.getLayoutComponent(BorderLayout.NORTH);
        residesIn.remove(oldMethodName);
        residesIn.add(step.name(), BorderLayout.NORTH);

        // replace code panel
        Component oldCode = layout.getLayoutComponent(BorderLayout.WEST);
        residesIn.remove(oldCode);
        JPanel newCodePanel = step.codePanel();
        setScrollPosition(newCodePanel);
        residesIn.add(newCodePanel, BorderLayout.WEST);

        residesIn.revalidate();
        residesIn.repaint();
    }

    /*
     * returns a Panel that holds the center part of the Application and a headline
     * specifically, it holds the codePanel, the memoryPanel, the datastructurePanel
     * and the datastructurePanel, which is the headline.
     */
    static JPanel createCenterPanel(DrawStep step) {

        JPanel codePanel = step.codePanel();
        JPanel memoryPanel = step.memory();
        JPanel datastructurePanel = step.datastructure();
        JLabel datastructureName = step.name();

        JPanel centerPanel = new JPanel(new BorderLayout(8, 8));
        ThemeStyler.styleCenterPanel(centerPanel);

        centerPanel.add(codePanel, BorderLayout.WEST);
        setScrollPosition(codePanel);

        // CENTER stretches to fill remaining space
        centerPanel.add(createVisualizationPanel(memoryPanel, datastructurePanel), BorderLayout.CENTER);

        centerPanel.add(datastructureName, BorderLayout.NORTH);

        return centerPanel;
    }

    /*
     * Nests the memory and datastructure panel together, so the two scale together,
     * but seperate from the codePanel.
     * The memory panel should always be twice as large as the datastructure panel.
     */
    static JPanel createVisualizationPanel(JPanel memoryPanel, JPanel datastructurePanel) {
        JPanel visualizationPanel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0; // spalte
        c.gridy = 0; // zeile
        c.weightx = 2; // größe in spalte
        c.weighty = 1; // größe in zeile
        c.fill = GridBagConstraints.BOTH; // strech verhalten

        // space between the two panels
        c.insets = new Insets(0, 0, 0, 8);

        visualizationPanel.add(memoryPanel, c);

        c.gridx = 1;
        c.weightx = 1;

        visualizationPanel.add(datastructurePanel, c);

        return visualizationPanel;
    }

    /*
     * Changes the scroll Positon of the codePanel to be the highlited line.
     * 
     * Needs to happen after frame.setVisible, since the panelHeight is needed for
     * the calculation of the position. Therefore, this method cannot be inside
     * CodePanelBuilder.
     */
    static void setScrollPosition(JPanel codePanel) {
        RSyntaxTextArea textArea = findSyntaxTextArea(codePanel);
        if (textArea != null) {
            scrollToTargetLine(textArea, 4);
        }
    }

    private static void scrollToTargetLine(RSyntaxTextArea textArea, int retries) {
        SwingUtilities.invokeLater(() -> {
            // check if the UI is ready to be measured
            // if visibleRect.height = 0 , then the textArea is not visible yet
            Rectangle visibleRect = textArea.getVisibleRect();
            if (!textArea.isShowing() || visibleRect.height <= 0) {
                if (retries > 0) {
                    // Swing is lazy; it only knows the size of the text Area, when the component is
                    // shown. The retry fixes accessing the height before it's known.
                    scrollToTargetLine(textArea, retries - 1);
                }
                return;
            }

            try {
                // get line to scroll to
                Integer target = (Integer) textArea.getClientProperty("codepanel.targetLineNumber");
                if (target == null)
                    return;

                // make sure line isnt negative or higher than number of existing lines
                int maxLineIndex = textArea.getLineCount() - 1;
                int safeLine = Math.max(0, Math.min(target, maxLineIndex));

                // turn LineStart into Geometric Coordinates (Pixels on Screen)
                int lineOffset = textArea.getLineStartOffset(safeLine);
                Rectangle2D lineBounds = textArea.modelToView2D(lineOffset);
                if (lineBounds == null)
                    return;

                // Calculate the center position
                double lineTopY = lineBounds.getY();
                double lineHeight = lineBounds.getHeight();
                double viewHeight = visibleRect.getHeight();

                // Math: Start at line top -> Move up by half a screen -> Move down by half a
                // line
                int centeredY = (int) (lineTopY - (viewHeight / 2) + (lineHeight / 2));

                // Confirm it's valid scroll position so we don't go out of bounds
                int maxPossibleScroll = textArea.getHeight() - (int) viewHeight;
                int finalScrollY = Math.max(0, Math.min(centeredY, maxPossibleScroll));

                // scroll to this rectangle, that is placed above the line to highlight
                Rectangle targetView = new Rectangle(0, finalScrollY, (int) visibleRect.getWidth(), (int) viewHeight);
                textArea.scrollRectToVisible(targetView);

            } catch (BadLocationException ignored) {
                // Outside document range -> nothing to scroll
            }
        });
    }

    static RSyntaxTextArea findSyntaxTextArea(Component root) {
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
}
