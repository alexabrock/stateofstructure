package com.hhu.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GraphicsEnvironment;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.hhu.datastructures.legacyDatastructures.api.Visualizable;
import com.hhu.util.DrawCalls;
import com.hhu.util.DrawStep;

import com.hhu.views.panelBuilder.ThemeStyler;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;

public class Application {

    public static <E> void start(DrawCalls drawCalls) {
        // Swing needs this
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Headless environment detected ");
            return;
        }
        // Visualizable visualizable = (Visualizable) collection;
        // DrawCalls drawCalls = visualizable.getDrawCalls();
        DrawStep firstStep = drawCalls.nextStep();

        ThemeStyler.applyDarkTheme();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("State to Structure");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // need to initialize these, so the button logik is cleaner (button can just
            // replace the existing code panels and doesnt need to check if they exist)
            JPanel codePanel = firstStep.codPanel();
            JPanel memoryPanel = firstStep.memory();
            JPanel datastructurePanel = firstStep.datastructure();
            JLabel methodName = firstStep.name();

            JPanel centerPanel = createCenterPanel(codePanel, memoryPanel, datastructurePanel, methodName);
            frame.add(centerPanel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 24, 8));
            ThemeStyler.styleToolbar(buttonPanel);

            JButton prevButton = new JButton("Previous");
            JButton nextButton = new JButton("Next");
            styleNavigationButton(prevButton);
            styleNavigationButton(nextButton);

            // Initial state setzen
            prevButton.setEnabled(true);
            nextButton.setEnabled(drawCalls.hasNextStep());

            
            prevButton.addActionListener(e -> {
                DrawStep step = drawCalls.prevStep();
                replacePanels(centerPanel, step);

                updateButtons(prevButton, nextButton, drawCalls);
            });

            nextButton.addActionListener(e -> {
                DrawStep step = drawCalls.nextStep();
                replacePanels(centerPanel, step);

                updateButtons(prevButton, nextButton, drawCalls);
            });

            buttonPanel.add(prevButton);
            buttonPanel.add(nextButton);

            frame.add(buttonPanel, BorderLayout.SOUTH);

            frame.setSize(2500, 1200);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    static void updateButtons(JButton prev, JButton next, DrawCalls drawCalls) {
        prev.setEnabled(drawCalls.hasPrevStep());
        next.setEnabled(drawCalls.hasNextStep());
    }
    
    static void styleNavigationButton(JButton button) {
        button.setFont(button.getFont().deriveFont(Font.BOLD, 18f));
        button.setMargin(new Insets(10, 24, 10, 24));
        button.setPreferredSize(new Dimension(160, 56));
    }

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
        residesIn.add(step.codPanel(), BorderLayout.WEST);

        residesIn.revalidate();
        residesIn.repaint();
    }

    static JPanel createCenterPanel(JPanel codePanel, JPanel memoryPanel, JPanel datastructurePanel,
            JLabel methodName) {
        JPanel centerPanel = new JPanel(new BorderLayout(8, 8));

        centerPanel.add(codePanel, BorderLayout.WEST);
        // CENTER stretches to fill remaining space
        centerPanel.add(createVisualizationPanel(memoryPanel, datastructurePanel), BorderLayout.CENTER);
        centerPanel.add(methodName, BorderLayout.NORTH);

        return centerPanel;
    }
    
    static JPanel createVisualizationPanel(JPanel memoryPanel, JPanel datastructurePanel) {
        JPanel visualizationPanel = new JPanel(new GridBagLayout());

        GridBagConstraints memoryConstraints = new GridBagConstraints();
        memoryConstraints.gridx = 0;
        memoryConstraints.gridy = 0;
        memoryConstraints.weightx = 2.0;
        memoryConstraints.weighty = 1.0;
        memoryConstraints.fill = GridBagConstraints.BOTH;
        memoryConstraints.insets = new Insets(0, 0, 0, 8);

        GridBagConstraints datastructureConstraints = new GridBagConstraints();
        datastructureConstraints.gridx = 1;
        datastructureConstraints.gridy = 0;
        datastructureConstraints.weightx = 1.0;
        datastructureConstraints.weighty = 1.0;
        datastructureConstraints.fill = GridBagConstraints.BOTH;

        visualizationPanel.add(memoryPanel, memoryConstraints);
        visualizationPanel.add(datastructurePanel, datastructureConstraints);

        return visualizationPanel;
    }
}
