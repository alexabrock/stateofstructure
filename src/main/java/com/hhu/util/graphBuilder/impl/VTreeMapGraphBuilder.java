package com.hhu.util.graphBuilder.impl;

import com.hhu.datastructures.VTreeMap;
import com.hhu.util.graphBuilder.api.GraphBuilder;

import java.util.Collection;
import java.util.Map;

import guru.nidi.graphviz.attribute.Rank;

import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;

import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

public class VTreeMapGraphBuilder implements GraphBuilder {

        @Override
        public Graph buildGraph(Object collection) {
            VTreeMap<?, ?> map = (VTreeMap<?, ?>) collection;

            Graph g = graph("mapGraph").directed()
                    .graphAttr().with(Rank.dir(LEFT_TO_RIGHT))
                    .nodeAttr().with(Shape.RECTANGLE);
    
            for (Object entryObj : map.entrySet()) {
                Map.Entry<?, ?> entry = (Map.Entry<?, ?>) entryObj;
                String keyNodeName = entry.getKey().toString();
                String valueNodeName = entry.getValue().toString();
                g = g.with(node(keyNodeName));
                g = g.with(node(valueNodeName));
                g = g.with(node(keyNodeName).link(node(valueNodeName)));
            }
            return g;
        }
    
}
