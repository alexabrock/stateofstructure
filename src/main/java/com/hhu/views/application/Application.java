package com.hhu.views.application;

import java.awt.GridLayout;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.atpfivt.ljv.LJV;

import com.hhu.views.datastructure.GraphBuilder;
import com.hhu.views.panelBuilder.PanelBuilder;
import com.hhu.util.FileReader;

public class Application {

    /* Currently only tested on LinkedList */
    public static <E> void startListApplication(Path path, LinkedList<E> list) {
        String ljvDot = new LJV().setTreatAsPrimitive(Character.class).drawGraph(list);
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
            frame.add(PanelBuilder.createDatastructurePanel(GraphBuilder.buildGraphFromList(list)));

            frame.setSize(1700, 700);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

}
