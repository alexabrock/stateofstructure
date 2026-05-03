package com.hhu.views.panelBuilder;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.atpfivt.ljv.LJV;

import com.hhu.views.Colors;

/*
* Builds the Memory Panel. An abstract vizualisation of a given collection in the Java-Heap.
 */
class MemoryPanelBuilder {
    private MemoryPanelBuilder() {
    }

    /*
     * Returns a JPanel, that holds the Memory-Representation of the given Collection. Uses LJV to do so.
     */
    static JPanel create(Object collection) {
        String dot = applyDarkMode(createDot(collection));

        try {
            
            return GraphPanelRenderer.create("Memory Visualization", dot);

        } catch (RuntimeException ex) {
            // if something went wrong, a Panel with the error is returned
            JLabel error = new JLabel("Could not render LJV DOT: " + ex.getMessage(), SwingConstants.CENTER);
            JPanel panel = new JPanel(new BorderLayout());
            ThemeStyler.styleModernCard(panel);
            panel.add(error, BorderLayout.CENTER);
            return panel;
        }
    }

    /*
     * Ignores Fields, that are not relevant to the state of the datastructure, are
     * always null unless accessed first or add unneccessary noise to the
     * datastructure-vizualisation.
     */
    private static String createDot(Object collection) {
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

    /*
     * clinks into the by LJV generated dot and modifies the colors to support
     * darkMode
     */
    static String applyDarkMode(String dot) {
        String themedAttributes = String.format(
                "%n  graph [bgcolor=\"%s\", fontcolor=\"%s\"];%n"
                        + "  node [fontcolor=\"%s\", color=\"%s\", style=\"filled\", fillcolor=\"%s\"];%n"
                        + "  edge [color=\"%s\", fontcolor=\"%s\"];%n",
                Colors.BG_COLORM,
                Colors.FG_COLORM,
                Colors.FG_COLORM,
                Colors.NODE_BORDER_COLORM,
                Colors.NODE_FILL_COLORM,
                Colors.EDGE_COLORM,
                Colors.FG_COLORM);

        // After the first brace in the dot is where the styling of the graph happens
        int firstBrace = dot.indexOf('{');
        if (firstBrace < 0) {
            return dot;
        }
        /*
         * dot... { + themed attributes + ... dot
         */
        return dot.substring(0, firstBrace + 1) + themedAttributes + dot.substring(firstBrace + 1);
    }
}