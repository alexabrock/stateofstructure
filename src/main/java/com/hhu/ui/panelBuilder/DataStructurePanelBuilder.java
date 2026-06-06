package com.hhu.ui.panelBuilder;

import static guru.nidi.graphviz.attribute.Attributes.attr;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.hhu.datastructureView.GraphBuilder;
import com.hhu.ui.Colors;

import guru.nidi.graphviz.model.Graph;

/**
 * Builds the DataStructure Panel. An simplified vizualisation of a given
 * collection.
 */
class DataStructurePanelBuilder {
    private DataStructurePanelBuilder() {
    }

    /**
     * Returns a JPanel, that holds the Datastructure-Representation of the given
     * Collection.
     * To do so, it calls a Builder from GraphBuilderFactory.
     */
    static JPanel create(Object collection) {
        try {
            Graph graph = colorize(GraphBuilder.buildGraph(collection));
            return GraphPanelRenderer.create("Datastructure Visualization", graph.toString());

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
        return graph
                .graphAttr().with(
                        attr("bgcolor", Colors.BG_COLORD),
                        attr("fontcolor", Colors.FG_COLORD))
                .nodeAttr().with(
                        attr("fontcolor", Colors.FG_COLORD),
                        attr("color", Colors.NODE_BORDER_COLORD),
                        attr("style", "filled"),
                        attr("fillcolor", Colors.NODE_FILL_COLORD))
                .linkAttr().with(
                        attr("color", Colors.EDGE_COLORD),
                        attr("fontcolor", Colors.FG_COLORD));
    }
}