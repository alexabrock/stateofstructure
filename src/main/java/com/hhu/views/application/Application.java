package com.hhu.views.application;

import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.atpfivt.ljv.LJV;

import com.hhu.views.datastructure.GraphBuilder;
import com.hhu.views.panelBuilder.PanelBuilder;

public class Application {

    /* Currently only tested on LinkedList */
    public static <E> void startListApplication(String code, LinkedList<E> list) {
        String ljvDot = new LJV().drawGraph(list);

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
