package com.hhu.views.application;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.GraphicsEnvironment;

import com.hhu.views.panelBuilder.PanelBuilder;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
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

        FlatDarculaLaf.setup();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("State to Structure");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JPanel codePanel = PanelBuilder.createCodePanel(code);
            JPanel memoryPanel = PanelBuilder.createMemoryPanel(collection);
            JPanel datastructurePanel = PanelBuilder.createDatastructurePanel(collection);
            //initiate Label with Datastructure Name
            JLabel methodName = PanelBuilder.createMethodNameLabel(collection.getClass().toString());

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
        Component oldMemory = layout.getLayoutComponent(BorderLayout.CENTER);
        residesIn.remove(oldMemory);
        residesIn.add(step.memory(), BorderLayout.CENTER);

        // replace datastructure panel 
        Component oldDatastructure = layout.getLayoutComponent(BorderLayout.EAST);
        residesIn.remove(oldDatastructure);
        residesIn.add(step.datastructure(), BorderLayout.EAST);

        //replace method name
        Component oldMethodName = layout.getLayoutComponent(BorderLayout.NORTH);
        residesIn.remove(oldMethodName);
        residesIn.add(step.methodLabel(), BorderLayout.NORTH);

        residesIn.revalidate();
        residesIn.repaint();
    }

    private static JPanel createCenterPanel(JPanel codePanel, JPanel memoryPanel, JPanel datastructurePanel, JLabel methodName) {
        JPanel centerPanel = new JPanel(new BorderLayout(8, 8));

        centerPanel.add(codePanel, BorderLayout.WEST);
        // CENTER stretches to fill remaining space
        //TODO: MAke memory panel bigger
        centerPanel.add(memoryPanel, BorderLayout.CENTER);
        centerPanel.add(datastructurePanel, BorderLayout.EAST);
        centerPanel.add(methodName, BorderLayout.NORTH);

        return centerPanel;
    }

}
