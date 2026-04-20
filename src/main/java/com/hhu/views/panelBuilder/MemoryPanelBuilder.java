package com.hhu.views.panelBuilder;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.atpfivt.ljv.LJV;

import com.hhu.views.Colors;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

final class MemoryPanelBuilder {
    private MemoryPanelBuilder() {
    }

    static JPanel create(Object collection) {
        String dot = applyDarkMode(createDot(collection));

        try {

            BufferedImage image = Graphviz.fromString(dot).width(1200).render(Format.PNG).toImage();
            return GraphPanelRenderer.create("Memory Visualization", image);

            // if something went wrong, a Panel with the error is returned
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

        int firstBrace = dot.indexOf('{');
        if (firstBrace < 0) {
            return dot;
        }
        return dot.substring(0, firstBrace + 1) + themedAttributes + dot.substring(firstBrace + 1);
    }
}