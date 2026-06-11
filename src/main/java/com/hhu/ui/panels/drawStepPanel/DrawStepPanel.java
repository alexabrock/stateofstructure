package com.hhu.ui.panels.drawStepPanel;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.hhu.ui.panels.codePanel.CodePanelScroller;
import com.hhu.util.DrawStep;
import com.hhu.util.ThemeStyler;

/**
 * Builds and updates the center area that renders the current DrawStep.
 */
public class DrawStepPanel {

    private final CodePanelScroller codePanelScroller;

    // Speichert die letzte Position der Trennlinien (-1 = noch keine manuelle Anpassung)
    private int leftDividerLocation = -1;
    private int rightDividerLocation = -1;

    public DrawStepPanel(CodePanelScroller codePanelScroller) {
        this.codePanelScroller = codePanelScroller;
    }

    public JPanel createCenterPanel(DrawStep step) {
        JPanel centerPanel = new JPanel(new BorderLayout(8, 8));
        ThemeStyler.styleCenterPanel(centerPanel);

        centerPanel.add(step.name(), BorderLayout.NORTH);

        JPanel codePanel = step.codePanel();
        codePanelScroller.scrollToHighlightedLine(codePanel);

        JPanel workspacePanel = createWorkspacePanel(codePanel, step.memory(), step.datastructure());
        centerPanel.add(workspacePanel, BorderLayout.CENTER);

        return centerPanel;
    }

    public void showStep(JPanel centerPanel, DrawStep step) {
        BorderLayout layout = (BorderLayout) centerPanel.getLayout();

        replace(centerPanel, layout, BorderLayout.NORTH, step.name());

        JPanel newCodePanel = step.codePanel();
        codePanelScroller.scrollToHighlightedLine(newCodePanel);

        JPanel workspacePanel = createWorkspacePanel(newCodePanel, step.memory(), step.datastructure());
        replace(centerPanel, layout, BorderLayout.CENTER, workspacePanel);

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

    private JPanel createWorkspacePanel(JPanel codePanel, JPanel memoryPanel, JPanel datastructurePanel) {
        // Rechtes SplitPane: Trennt Memory und Datastructure
        JSplitPane rightSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, memoryPanel, datastructurePanel);
        rightSplit.setResizeWeight(0.5); // 50:50
        rightSplit.setDividerSize(8);

        // Linkes SplitPane: Trennt Code von dem rechten SplitPane
        JSplitPane leftSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, codePanel, rightSplit);
        leftSplit.setDividerSize(8);
        leftSplit.setResizeWeight(0.33); // 1/3 zu 2/3

        leftSplit.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, evt -> {
            leftDividerLocation = leftSplit.getDividerLocation();
        });

        rightSplit.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, evt -> {
            rightDividerLocation = rightSplit.getDividerLocation();
        });

        if (leftDividerLocation > 0) {
            leftSplit.setDividerLocation(leftDividerLocation);
        }
        if (rightDividerLocation > 0) {
            rightSplit.setDividerLocation(rightDividerLocation);
        }

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(leftSplit, BorderLayout.CENTER);
        return panel;
    }
}