package com.hhu.datastructureView;

import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.model.Factory.node;

import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.attribute.Style;

import guru.nidi.graphviz.model.Node;

/* Builds unique Nodes, that will not be Merged by GraphViz */
public class NodeBuilder {
    public static Node getNode(String label) {
        // eindeutige Node-ID
        String id = java.util.UUID.randomUUID().toString();

        Node node = node(id).with(attr("label", label));

        return node;
    }
    
    // needs to be unique, since graphviz merges Nodes with the same Name
    public static Node nullNode(Object key) {
        return getNode("")
                .with(Shape.RECTANGLE)
                .with(attr("width", 0.25), attr("height", 0.05));
    }
}
