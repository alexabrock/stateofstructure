package com.hhu.ui.application;

import java.awt.Component;
import java.awt.GraphicsEnvironment;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import com.hhu.util.DrawCalls;
import com.hhu.util.DrawStep;
import com.hhu.util.ThemeStyler;
import com.hhu.util.compiler.WorkspaceCleaner;

public class Application {
    private static DrawCallHandler currentState;

    private final DrawCallHandler applicationState;
    private final CodePanelScroller codePanelScroller;
    private final DrawStepPanel drawStepView;
    private final ApplicationFrame applicationFrame;
    private final WorkspaceCleaner workspaceCleaner;

    private Application(DrawCalls drawCalls) {
        applicationState = new DrawCallHandler(drawCalls);
        codePanelScroller = new CodePanelScroller();
        drawStepView = new DrawStepPanel(codePanelScroller);
        applicationFrame = new ApplicationFrame();
        workspaceCleaner = new WorkspaceCleaner();
        currentState = applicationState;
    }

    public static <E> void start(DrawCalls givenDrawCalls) {
        // Swing needs this
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Headless environment detected ");
            return;
        }

        ThemeStyler.applyTheme();

        SwingUtilities.invokeLater(() -> new Application(givenDrawCalls).show());
    }

    static void updateButtons(JButton prev, JButton next) {
        if (currentState == null) {
            prev.setEnabled(false);
            next.setEnabled(false);
            return;
        }
        NavigationPanel.updateButtons(prev, next, currentState);
    }

    /*
     * returns the very outer shell of the Application.
     */

    static JFrame createFrame(JPanel centerPanel, JPanel buttonPanel) {
        return new ApplicationFrame().create(centerPanel, buttonPanel);
    }

    /*
     * returns a Panel, that holds the buttons. Initializes those buttons to replace
     * the Panels, that are nested inside the centerPanel, with the next or previous
     * DrawStep
     */
    static JPanel createButtonPanel(JPanel centerPanel) {
        return new NavigationPanel(currentState, newDrawStepView(), new CodePanelScroller()).create(centerPanel);
    }

    static JPanel createCenterPanel(DrawStep step) {
        return newDrawStepView().createCenterPanel(step);
    }

    static JPanel createVisualizationPanel(JPanel memoryPanel, JPanel datastructurePanel) {
        return newDrawStepView().createVisualizationPanel(memoryPanel, datastructurePanel);
    }

    /*
     * removes the current Panels in residesIn and replaces them with the panels
     * stored inside the DrawStep.
     * Also updates the scrollPosition of the CodePanel to match the new highlited
     * line.
     */
    static void replacePanels(JPanel residesIn, DrawStep step) {
        newDrawStepView().showStep(residesIn, step);
    }

    static RSyntaxTextArea findSyntaxTextArea(Component root) {
        return new CodePanelScroller().findSyntaxTextArea(root);
    }

    /*
     * Changes the scroll Positon of the codePanel to be the highlited line.
     * 
     * Needs to happen after frame.setVisible, since the panelHeight is needed for
     * the calculation of the position. Therefore, this method cannot be inside
     * CodePanelBuilder.
     */
    static void setScrollPosition(JPanel codePanel) {
        new CodePanelScroller().scrollToHighlightedLine(codePanel);
    }

    private static DrawStepPanel newDrawStepView() {
        return new DrawStepPanel(new CodePanelScroller());
    }

    private void show() {
        /*
         * need to initialize these, so the button logik is cleaner (button can just
         * replace the existing code panels and doesnt need to check if they exist)
         */
        DrawStep firstStep = applicationState.nextStep();
        JPanel centerPanel = drawStepView.createCenterPanel(firstStep);
        JPanel buttonPanel = createNavigationPanel(centerPanel);
        JFrame frame = applicationFrame.create(centerPanel, buttonPanel);

        frame.setVisible(true);
        workspaceCleaner.registerShutdownCleanup();
    }

    private JPanel createNavigationPanel(JPanel centerPanel) {
        NavigationPanel navigationPanel = new NavigationPanel(
                applicationState,
                drawStepView,
                codePanelScroller);
        return navigationPanel.create(centerPanel);
    }
}
