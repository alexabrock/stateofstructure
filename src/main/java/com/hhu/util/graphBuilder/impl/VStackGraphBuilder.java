package com.hhu.util.graphBuilder.impl;

import com.hhu.datastructures.VStack;
import com.hhu.util.graphBuilder.api.GraphBuilder;

import java.util.Collection;

import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

public class VStackGraphBuilder implements GraphBuilder {
    @Override
    public Graph buildGraph(Object collection) {
        if (!(collection instanceof VStack<?> stack)) {
            throw new IllegalArgumentException("Expected VStack");
        }

        // Stack in Liste umwandeln (von unten nach oben)
        java.util.List<Object> elements = new java.util.ArrayList<>();
        for (Object el : stack) {
            elements.add(el);
        }

        // Graph erstellen (Top -> Bottom Darstellung = vertical)
        var g = graph("stackGraph").directed();

        java.util.List<guru.nidi.graphviz.model.Node> nodes = new java.util.ArrayList<>();

        // Nodes erstellen
        for (int i = 0; i < elements.size(); i++) {
            var n = node(String.valueOf(elements.get(i)))
                    .with(Shape.BOX);
            nodes.add(n);
        }

        // Kanten zwischen Stack-Elementen (von oben nach unten)
        for (int i = nodes.size() - 1; i > 0; i--) {
            g = g.with(nodes.get(i).link(nodes.get(i - 1)));
        }

        if (!nodes.isEmpty()) {
            // TOP Label (rechts vom obersten Element)
            var topLabel = node("top").with(Shape.PLAIN);
            g = g.with(
                    nodes.get(nodes.size() - 1)
                            .link(guru.nidi.graphviz.model.Factory.to(topLabel)
                                    .with("dir", "none")) // kein Pfeil
            );

            // BOTTOM Label (rechts vom untersten Element)
            var bottomLabel = node("bottom").with(Shape.PLAIN);

            // Pfeil UMGEKEHRT: bottom -> unterstes Element
            g = g.with(
                    bottomLabel.link(nodes.get(0)));
        }

        return g;
    }
    
}
