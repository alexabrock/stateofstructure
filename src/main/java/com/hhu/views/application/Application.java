package com.hhu.views.application;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.atpfivt.ljv.LJV;
import java.awt.GraphicsEnvironment;

import com.hhu.views.panelBuilder.PanelBuilder;

import guru.nidi.graphviz.model.Graph;

import com.hhu.datastructures.VStack;
import com.hhu.util.DrawStep;
import com.hhu.util.FileReader;
import com.hhu.util.graphBuilder.GraphBuilderFactory;

public class Application {
    public static <E> void startApplication(Path path, Object collection) {

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

            JButton button = button(centerPanel, collection);
            frame.add(button, BorderLayout.SOUTH);

            frame.setSize(2700, 1200);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static JButton button( JPanel residesIn, Object collection) {
        JButton button = new JButton("Next");
        button.addActionListener(e -> {
            VStack<String> stack = (VStack<String>) collection;

            DrawStep step = stack.nextStep();

            if (step != null) {
                //replace memory panel
            String memoryDot = step.memoryDot();
            //this is really ugly wow
            JPanel newMemoryPanel = PanelBuilder.createDotGraphPanel("Memory Visualization", memoryDot);
                residesIn.remove(1);
                residesIn.add(newMemoryPanel, 1);

                // replace datastructure panel
                Graph datastructure = step.datastructure();
                JPanel newDatastructurePanel = PanelBuilder.createGraphPanel("Datastructure Visualization", datastructure);
                residesIn.remove(2);
                residesIn.add(newDatastructurePanel, 2);



                residesIn.revalidate();
                residesIn.repaint();
            }
        });
        return button;
    }

    private static JPanel createCenterPanel(JPanel codePanel, JPanel memoryPanel, JPanel datastructurePanel) {
        JPanel centerPanel = new JPanel(new GridLayout(1, 3, 8, 8));

        centerPanel.add(codePanel);
        centerPanel.add(memoryPanel);
        centerPanel.add(datastructurePanel);

        return centerPanel;
    }

}
