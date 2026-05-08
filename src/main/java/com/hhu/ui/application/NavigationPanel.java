package com.hhu.ui.application;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.hhu.ui.panelBuilder.ThemeStyler;

/*
 * returns a Panel, that holds the buttons. Initializes those buttons to replace
 * the Panels, that are nested inside the centerPanel, with the next or previous
 * DrawStep
 */
class NavigationPanel {

    private final ApplicationState applicationState;
    private final DrawStepPanel drawStepView;
    private final CodePanelScroller codePanelScroller;

    private JButton prevButton;
    private JButton nextButton;

    NavigationPanel(
            ApplicationState applicationState,
            DrawStepPanel drawStepView,
            CodePanelScroller codePanelScroller) {
        this.applicationState = applicationState;
        this.drawStepView = drawStepView;
        this.codePanelScroller = codePanelScroller;
    }

    JPanel create(JPanel centerPanel) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 24, 8));
        ThemeStyler.styleToolbar(buttonPanel);

        JButton compileButton = createButton("Compile");
        prevButton = createButton("Previous");
        nextButton = createButton("Next");

        // Initial state setzen
        refreshNavigationButtons();
        registerActions(centerPanel, compileButton);

        buttonPanel.add(compileButton);
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);

        return buttonPanel;
    }

    void refreshNavigationButtons() {
        updateButtons(prevButton, nextButton, applicationState);
    }

    static void updateButtons(JButton prev, JButton next, ApplicationState applicationState) {
        prev.setEnabled(applicationState.hasPreviousStep());
        next.setEnabled(applicationState.hasNextStep());
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        ThemeStyler.styleNavigationButton(button);
        return button;
    }

    private void registerActions(JPanel centerPanel, JButton compileButton) {
        prevButton.addActionListener(e -> showPreviousStep(centerPanel));
        nextButton.addActionListener(e -> showNextStep(centerPanel));
        compileButton.addActionListener(e -> createCompilationController().compile(centerPanel, compileButton));
    }

    private CompilationController createCompilationController() {
        return new CompilationController(
                applicationState,
                drawStepView,
                codePanelScroller,
                this::refreshNavigationButtons);
    }

    private void showPreviousStep(JPanel centerPanel) {
        drawStepView.showStep(centerPanel, applicationState.previousStep());
        refreshNavigationButtons();
    }

    private void showNextStep(JPanel centerPanel) {
        drawStepView.showStep(centerPanel, applicationState.nextStep());
        refreshNavigationButtons();
    }
}
