package com.hhu.ui.application;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.hhu.ui.panelBuilder.TutorialPanel;
import com.hhu.util.ThemeStyler;

/**
 * Returns a Panel, that holds the buttons. Initializes those buttons to replace
 * the Panels, that are nested inside the centerPanel, with the next or previous
 * DrawStep
 */
class NavigationPanel {

    private final DrawCallHandler drawCallHandler;
    private final DrawStepPanel drawStepPanel;
    private final CodePanelScroller codePanelScroller;

    private JButton prevButton;
    private JButton nextButton;
    private JButton compileButton;
    private JButton helpButton;

    NavigationPanel(
            DrawCallHandler drawCallHandler,
            DrawStepPanel drawStepPanel,
            CodePanelScroller codePanelScroller) {
        this.drawCallHandler = drawCallHandler;
        this.drawStepPanel = drawStepPanel;
        this.codePanelScroller = codePanelScroller;
    }

    JPanel create(JPanel centerPanel) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 24, 8));
        ThemeStyler.styleToolbar(buttonPanel);

        helpButton = createButton("Help");
        compileButton = createButton("Compile");
        prevButton = createButton("Previous");
        nextButton = createButton("Next");

        // Initial state setzen
        refreshNavigationButtons();
        initializeButtonActions(centerPanel);

        buttonPanel.add(helpButton);
        buttonPanel.add(compileButton);
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);

        return buttonPanel;
    }

    void refreshNavigationButtons() {
        updateButtons(prevButton, nextButton, drawCallHandler);
    }

    static void updateButtons(JButton prev, JButton next, DrawCallHandler drawCallHandler) {
        prev.setEnabled(drawCallHandler.hasPreviousStep());
        next.setEnabled(drawCallHandler.hasNextStep());
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        ThemeStyler.styleNavigationButton(button);
        return button;
    }

    // centerPanel is needed, since the compiler, which is linked to the compile
    // Button,
    // replaces the text from the codePanel in the centerPanel
    private void initializeButtonActions(JPanel centerPanel) {
        prevButton.addActionListener(e -> showPreviousStep(centerPanel));
        nextButton.addActionListener(e -> showNextStep(centerPanel));
        compileButton.addActionListener(e -> createCompilationController().compile(centerPanel, compileButton));
        helpButton.addActionListener(e -> {
            TutorialPanel tutorial = new TutorialPanel(centerPanel);
            tutorial.setVisible(true);
        });
    }

    private CompilationController createCompilationController() {
        return new CompilationController(
                drawCallHandler,
                drawStepPanel,
                codePanelScroller,
                this::refreshNavigationButtons);
    }

    private void showPreviousStep(JPanel centerPanel) {
        drawStepPanel.showStep(centerPanel, drawCallHandler.previousStep());
        refreshNavigationButtons();
    }

    private void showNextStep(JPanel centerPanel) {
        drawStepPanel.showStep(centerPanel, drawCallHandler.nextStep());
        refreshNavigationButtons();
    }
}
