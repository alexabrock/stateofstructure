package com.hhu.util.graphBuilder.impl;

import com.hhu.datastructures.VTreeMap;
import com.hhu.util.graphBuilder.api.GraphBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;

import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.model.Graph;

import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import guru.nidi.graphviz.model.Node;
import guru.nidi.graphviz.model.LinkSource;

public class VTreeMapGraphBuilder implements GraphBuilder {

    @Override
    public Graph buildGraph(Object collection) {

        if (!(collection instanceof VTreeMap<?, ?>)) {
            throw new IllegalArgumentException("Expected VTreeMap");
        }

        VTreeMap<?, ?> map = (VTreeMap<?, ?>) collection;

        List<Node> keys = new ArrayList<>();
        List<Node> values = new ArrayList<>();
        List<LinkSource> links = new ArrayList<>();

        for (Object entryObj : map.entrySet()) {
            Map.Entry<?, ?> entry = (Map.Entry<?, ?>) entryObj;

            Node k = node(entry.getKey().toString());
            Node v = node(entry.getValue().toString());

            keys.add(k);
            values.add(v);
            links.add(k.link(v));
        }

        keys = keys.reversed();
        values = values.reversed();

        Graph keyGraph = graph("keyGraph").cluster()
                .graphAttr()
                .with(Label.of("Keys"),
                        Style.FILLED,
                        Color.named("lightblue").fill())
                .with(keys);

        Graph valuesGraph = graph("valuesGraph").cluster()
                .graphAttr().with(Label.of("Values"),
                        Style.FILLED,
                        Color.named("lightpink").fill())
                .with(values);

        Graph g = graph("mapGraph").directed()
                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT))
                .nodeAttr().with(Shape.RECTANGLE,
                        Style.FILLED,
                        Color.named("white").fill())
                .with(
                        keyGraph,
                        valuesGraph)
                .with(links);

        return g;
    }

}
