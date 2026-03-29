package com.hhu.util.datastructureView.impl;

import com.hhu.datastructures.VTreeMap;
import com.hhu.util.datastructureView.api.GraphBuilder;

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

        for (Map.Entry<?, ?> entry : map.entrySet()) {

            Node key = node(entry.getKey().toString());
            Node value = node(entry.getValue().toString());

            keys.add(key);
            values.add(value);
            links.add(key.link(value));
        }
        //um Reihenfolge wiederherzustellen
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
                //key und value graph zusammenführen
                .with(
                        keyGraph,
                        valuesGraph)
                .with(links);

        return g;
    }

}
