package com.hhu.views.panelBuilder;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.atpfivt.ljv.LJV;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

final class MemoryPanelBuilder {

    private MemoryPanelBuilder() {
    }

    static JPanel create(Object collection) {
        String dot = GraphvizDarkModeStyler.apply(createDot(collection));

        try {

            BufferedImage image = Graphviz.fromString(dot).width(900).render(Format.PNG).toImage();
            return GraphPanelRenderer.create("Memory Visualization",image);
            
        //if something went wrong, a Panel with the error is returned 
        } catch (RuntimeException ex) {
            JLabel error = new JLabel("Could not render LJV DOT: " + ex.getMessage(), SwingConstants.CENTER);
            JPanel panel = new JPanel(new BorderLayout());
            ThemeStyler.styleModernCard(panel);
            panel.add(error, BorderLayout.CENTER);
            return panel;
        }
    }

    static String createDot(Object collection) {
        return new LJV()
                .setTreatAsPrimitive(String.class)
                .addIgnoreField("drawCalls")
                .addIgnoreField("currentStepIndex")
                .addIgnoreField("hashIsZero")
                .addIgnoreField("coder")
                .addIgnoreField("modCount")
                .addIgnoreField("capacityIncrement")
                .addIgnoreField("comparator")
                .addIgnoreField("keySet")
                .addIgnoreField("navigableKeySet")
                .addIgnoreField("entrySet")
                .addIgnoreField("descendingMap")
                .addIgnoreField("color")
                .addIgnoreField("values")
                .drawGraph(collection);
    }
}