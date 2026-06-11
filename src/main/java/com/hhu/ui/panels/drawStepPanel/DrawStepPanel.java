package com.hhu.ui.panels.drawStepPanel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.hhu.ui.panels.codePanel.CodePanelScroller;
import com.hhu.util.DrawStep;
import com.hhu.util.ThemeStyler;

/**
 * Builds and updates the center area that renders the current DrawStep.
 */
public class DrawStepPanel {

    private final CodePanelScroller codePanelScroller;

    public DrawStepPanel(CodePanelScroller codePanelScroller) {
        this.codePanelScroller = codePanelScroller;
    }

    /**
     * returns a Panel that holds the center part of the Application and a headline
     * specifically, it holds the codePanel, the memoryPanel, the datastructurePanel
     * and the datastructureName, which is the headline.
     */
    public JPanel createCenterPanel(DrawStep step) {
        JPanel codePanel = step.codePanel();
        JPanel memoryPanel = step.memory();
        JPanel datastructurePanel = step.datastructure();
        JLabel datastructureName = step.name();

        JPanel centerPanel = new JPanel(new BorderLayout(8, 8));
        ThemeStyler.styleCenterPanel(centerPanel);

        centerPanel.add(codePanel, BorderLayout.WEST);
        codePanelScroller.scrollToHighlightedLine(codePanel);

        // CENTER stretches to fill remaining space
        centerPanel.add(createVisualizationPanel(memoryPanel, datastructurePanel), BorderLayout.CENTER);
        centerPanel.add(datastructureName, BorderLayout.NORTH);

        return centerPanel;
    }

    /**
     * removes the current Panels in residesIn and replaces them with the panels
     * stored inside the DrawStep.
     * Also updates the scrollPosition of the CodePanel to match the new highlited
     * line.
     */
    public void showStep(JPanel centerPanel, DrawStep step) {
        BorderLayout layout = (BorderLayout) centerPanel.getLayout();

        // replace visualization area (memory + datastructure)
        replace(centerPanel, layout, BorderLayout.CENTER,
                createVisualizationPanel(step.memory(), step.datastructure()));

        // replace method name
        replace(centerPanel, layout, BorderLayout.NORTH, step.name());

        // replace code panel
        JPanel newCodePanel = step.codePanel();
        codePanelScroller.scrollToHighlightedLine(newCodePanel);
        replace(centerPanel, layout, BorderLayout.WEST, newCodePanel);

        centerPanel.revalidate();
        centerPanel.repaint();
    }

    private void replace(JPanel panel, BorderLayout layout, String position, Component replacement) {
        Component previous = layout.getLayoutComponent(position);
        if (previous != null) {
            panel.remove(previous);
        }
        panel.add(replacement, position);
    }

    /**
     * Nests the memory and datastructure panel together, so the two scale together,
     * but seperate from the codePanel.
     * The memory panel should always be twice as large as the datastructure panel.
     */
    public JPanel createVisualizationPanel(JPanel memoryPanel, JPanel datastructurePanel) {
        JPanel visualizationPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = createMemoryPanelConstraints();

        visualizationPanel.add(memoryPanel, constraints);

        // new constraints, so the datastructurePanel is half as big as the memoryPanel
        constraints.gridx = 1;
        constraints.weightx = 1;
        visualizationPanel.add(datastructurePanel, constraints);

        return visualizationPanel;
    }

    private GridBagConstraints createMemoryPanelConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0; // spalte
        constraints.gridy = 0; // zeile
        constraints.weightx = 2; // größe in spalte
        constraints.weighty = 1; // größe in zeile
        constraints.fill = GridBagConstraints.BOTH; // strech verhalten

        // space between the two panels
        constraints.insets = new Insets(0, 0, 0, 8);
        return constraints;
    }
}