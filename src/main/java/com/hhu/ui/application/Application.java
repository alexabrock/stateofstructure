package com.hhu.ui.application;

import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.hhu.ui.panels.ApplicationFrame;
import com.hhu.ui.panels.NavigationPanel;
import com.hhu.ui.panels.codePanel.CodePanelScroller;
import com.hhu.ui.panels.drawStepPanel.DrawStepPanel;
import com.hhu.util.DrawCalls;
import com.hhu.util.DrawStep;
import com.hhu.util.ThemeStyler;
import com.hhu.util.compiler.WorkspaceCleaner;

public class Application {

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
    }

    public static void start(DrawCalls givenDrawCalls) {
        // Swing needs this
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Headless environment detected ");
            return;
        }

        ThemeStyler.applyTheme();

        SwingUtilities.invokeLater(() -> new Application(givenDrawCalls).show());
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
