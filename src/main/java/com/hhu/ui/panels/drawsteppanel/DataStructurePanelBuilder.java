package com.hhu.ui.panels.drawsteppanel;

import static guru.nidi.graphviz.attribute.Attributes.attr;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.hhu.datastructureview.GraphBuilder;
import com.hhu.ui.Colors;

import guru.nidi.graphviz.model.Graph;

/**
 * Builds the DataStructure Panel. A simplified vizualization of a given
 * collection.
 */
public class DataStructurePanelBuilder {
    private DataStructurePanelBuilder() {
    }

    /**
     * Returns a JPanel, that holds the Datastructure-Representation of the given
     * Collection.
     * To do so, it calls a Builder from GraphBuilderFactory.
     */
    public static JPanel create(Object collection) {
        try {
            Graph graph = colorize(GraphBuilder.buildGraph(collection));
            return GraphPanel.create("Datastructure Visualization", graph.toString());

        } catch (RuntimeException ex) {
            // if something went wrong, a Panel with the error is returned
            JLabel error = new JLabel("Could not render Datastructure Visualization: " + ex.getMessage(),
                    SwingConstants.CENTER);
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(error, BorderLayout.CENTER);
            return panel;
        }
    }

    /**
     * Adds colors to a finished Graph
     */
    static Graph colorize(Graph graph) {
        final String FONTCOLOR = "fontcolor";
        return graph
                .graphAttr().with(
                        attr("bgcolor", Colors.BG_COLORD),
                        attr(FONTCOLOR, Colors.FG_COLORD))
                .nodeAttr().with(
                        attr(FONTCOLOR, Colors.FG_COLORD),
                        attr("color", Colors.NODE_BORDER_COLORD),
                        attr("style", "filled"),
                        attr("fillcolor", Colors.NODE_FILL_COLORD))
                .linkAttr().with(
                        attr("color", Colors.EDGE_COLORD),
                        attr(FONTCOLOR, Colors.FG_COLORD));
    }
}