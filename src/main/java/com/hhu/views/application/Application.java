package com.hhu.views.application;

import java.awt.GridLayout;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.atpfivt.ljv.LJV;

import com.hhu.views.panelBuilder.PanelBuilder;
import com.hhu.datastructures.VLinkedList;
import com.hhu.util.FileReader;
import com.hhu.util.GraphBuilder;

public class Application {

    /* Currently only tested on LinkedList */
    public static <E> void startListApplication(Path path, Collection<E> collection) {
        String ljvDot = new LJV().setTreatAsPrimitive(Character.class).drawGraph(collection);

        String code;

        try {
            code = FileReader.fileToString(path);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading code file.");
            return;
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("State to Structure");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(1, 3, 8, 8));
            
            frame.add(PanelBuilder.createCodePanel(code));
            frame.add(PanelBuilder.createMemoryPanel(ljvDot));
            frame.add(PanelBuilder.createDatastructurePanel(GraphBuilder.buildGraph(collection)));

            frame.setSize(2700, 1200);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

}
