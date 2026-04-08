package com.hhu.views.application;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GraphicsEnvironment;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.hhu.datastructures.api.Visualizable;
import com.hhu.util.DrawCalls;
import com.hhu.util.DrawStep;

public class Application {

    public static <E> void startApplication(Object collection) {
        // Swing needs this
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Headless environment detected ");
            return;
        }
        Visualizable visualizable = (Visualizable) collection;
        DrawCalls drawCalls = visualizable.getDrawCalls();
        DrawStep firstStep = drawCalls.nextStep();

        FlatDarculaLaf.setup();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("State to Structure");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // need to initialize these, so the button logik is cleaner (button can just
            // replace the existing code panels and doesnt need to check if they exist)
            JPanel codePanel = firstStep.codPanel();
            JPanel memoryPanel = firstStep.memory();
            JPanel datastructurePanel = firstStep.datastructure();
            JLabel methodName = firstStep.methodLabel();

            JPanel centerPanel = createCenterPanel(codePanel, memoryPanel, datastructurePanel, methodName);
            frame.add(centerPanel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(prevButton(centerPanel, collection));
            buttonPanel.add(nextButton(centerPanel, collection));

            frame.add(buttonPanel, BorderLayout.SOUTH);

            frame.setSize(2500, 1200);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static JButton nextButton(JPanel residesIn, Object collection) {
        JButton button = new JButton("Next");
        button.addActionListener(e -> {
            Visualizable visualizable = (Visualizable) collection;

            DrawCalls drawCalls = visualizable.getDrawCalls();
            DrawStep step = drawCalls.nextStep();

            if (step != null) {
                replacePanels(residesIn, step);
            }
        });
        return button;
    }

    private static JButton prevButton(JPanel residesIn, Object collection) {
        JButton button = new JButton("Previous");
        button.addActionListener(e -> {
            Visualizable visualizable = (Visualizable) collection;

            DrawCalls drawCalls = visualizable.getDrawCalls();
            DrawStep step = drawCalls.prevStep();

            if (step != null) {
                replacePanels(residesIn, step);
            }
        });
        return button;
    }

    private static void replacePanels(JPanel residesIn, DrawStep step) {
        BorderLayout layout = (BorderLayout) residesIn.getLayout();

        // replace memory panel
        Component oldMemory = layout.getLayoutComponent(BorderLayout.CENTER);
        residesIn.remove(oldMemory);
        residesIn.add(step.memory(), BorderLayout.CENTER);

        // replace datastructure panel
        Component oldDatastructure = layout.getLayoutComponent(BorderLayout.EAST);
        residesIn.remove(oldDatastructure);
        residesIn.add(step.datastructure(), BorderLayout.EAST);

        // replace method name
        Component oldMethodName = layout.getLayoutComponent(BorderLayout.NORTH);
        residesIn.remove(oldMethodName);
        residesIn.add(step.methodLabel(), BorderLayout.NORTH);

        // replace code panel
        Component oldCode = layout.getLayoutComponent(BorderLayout.WEST);
        residesIn.remove(oldCode);
        residesIn.add(step.codPanel(), BorderLayout.WEST);

        residesIn.revalidate();
        residesIn.repaint();
    }

    private static JPanel createCenterPanel(JPanel codePanel, JPanel memoryPanel, JPanel datastructurePanel,
            JLabel methodName) {
        JPanel centerPanel = new JPanel(new BorderLayout(8, 8));

        centerPanel.add(codePanel, BorderLayout.WEST);
        // CENTER stretches to fill remaining space
        // TODO: MAke memory panel bigger
        centerPanel.add(memoryPanel, BorderLayout.CENTER);
        centerPanel.add(datastructurePanel, BorderLayout.EAST);
        centerPanel.add(methodName, BorderLayout.NORTH);

        return centerPanel;
    }

}
