package com.hhu.views.panelBuilder;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.hhu.datastructureView.GraphBuilderFactory;
import com.hhu.views.Colors;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;

import static guru.nidi.graphviz.attribute.Attributes.attr;

import guru.nidi.graphviz.model.Graph;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/*
* Builds the DataStructure Panel. An simplified vizualisation of a given collection.
*/
class DataStructurePanelBuilder {
    private DataStructurePanelBuilder() {
    }
    
    /*
     * Returns a JPanel, that holds the Datastructure-Representation of the given
     * Collection.
     * To do so, it calls a Builder from GraphBuilderFactory.
     */
    static JPanel create(Object collection) {
        try {
            Graph graph = applyDarkMode(GraphBuilderFactory.getBuilder(collection).buildGraph(collection));
            return GraphPanelRenderer.create("Datastructure Visualization", graph.toString());

        } catch (RuntimeException ex) {
            // if something went wrong, a Panel with the error is returned
            JLabel error = new JLabel("Could not render Datastructure Visualization: " + ex.getMessage(), SwingConstants.CENTER);
            JPanel panel = new JPanel(new BorderLayout());
            ThemeStyler.styleModernCard(panel);
            panel.add(error, BorderLayout.CENTER);
            return panel;
        }
    }
    
    /*
     * Adds  darkMode colors to a finished Graph
     */
    static Graph applyDarkMode(Graph graph) {
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