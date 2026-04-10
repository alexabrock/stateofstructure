package com.hhu.views.panelBuilder;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.hhu.datastructureView.GraphBuilderFactory;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;

final class DataStructurePanelBuilder {

    private DataStructurePanelBuilder() {
    }

    static JPanel create(Object collection) {
        try {
        Graph graph = GraphBuilderFactory.getBuilder(collection).buildGraph(collection);
        BufferedImage image = Graphviz.fromGraph(graph).width(400).render(Format.PNG).toImage();

        return GraphPanelRenderer.create("Datastructure Visualization", image);

        // if something went wrong, a Panel with the error is returned
        } catch (RuntimeException ex) {
            JLabel error = new JLabel("Could not render Datastructure Visualization: " + ex.getMessage(), SwingConstants.CENTER);
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBorder(new TitledBorder("Graphviz"));
            panel.add(error, BorderLayout.CENTER);
            return panel;
        }
    }
}