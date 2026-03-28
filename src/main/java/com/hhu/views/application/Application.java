package com.hhu.views.application;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.GraphicsEnvironment;

import com.hhu.views.panelBuilder.PanelBuilder;

import com.hhu.datastructures.VStack;
import com.hhu.util.DrawStep;
import com.hhu.util.FileReader;

public class Application {
    public static <E> void startApplication(Path path, Object collection) {
        //Swing needs this
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Headless environment detected ");
            return;
        }

        String code = FileReader.fileToString(path);

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("State to Structure");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JPanel codePanel = PanelBuilder.createCodePanel(code);
            JPanel memoryPanel = PanelBuilder.createMemoryPanel(collection);
            JPanel datastructurePanel = PanelBuilder.createDatastructurePanel(collection);

            JPanel centerPanel = createCenterPanel(codePanel, memoryPanel, datastructurePanel);
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
            VStack<String> stack = (VStack<String>) collection;

            DrawStep step = stack.nextStep();

            if (step != null) {
                replacePanels(residesIn, step);
            }
        });
        return button;
    }
    
    private static JButton prevButton(JPanel residesIn, Object collection) {
        JButton button = new JButton("Previous");
        button.addActionListener(e -> {
            VStack<String> stack = (VStack<String>) collection;

            DrawStep step = stack.prevStep();

            if (step != null) {
                replacePanels(residesIn, step);
            }
        });
        return button;
    }

    private static void replacePanels(JPanel residesIn, DrawStep step) {
        BorderLayout layout = (BorderLayout) residesIn.getLayout();

        // replace memory panel 
        java.awt.Component oldMemory = layout.getLayoutComponent(BorderLayout.CENTER);
        residesIn.remove(oldMemory);
        residesIn.add(step.memory(), BorderLayout.CENTER);

        // replace datastructure panel 
        java.awt.Component oldDatastructure = layout.getLayoutComponent(BorderLayout.EAST);
        residesIn.remove(oldDatastructure);
        residesIn.add(step.datastructure(), BorderLayout.EAST);

        residesIn.revalidate();
        residesIn.repaint();
    }

    private static JPanel createCenterPanel(JPanel codePanel, JPanel memoryPanel, JPanel datastructurePanel) {
        JPanel centerPanel = new JPanel(new BorderLayout(8, 8));

        centerPanel.add(codePanel, BorderLayout.WEST);
        // CENTER stretches to fill remaining space
        centerPanel.add(memoryPanel, BorderLayout.CENTER);
        centerPanel.add(datastructurePanel, BorderLayout.EAST);

        return centerPanel;
    }

}
