package com.hhu.util.datastructureView.impl.javaUtil;

import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.to;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.hhu.util.datastructureView.api.GraphBuilder;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.LinkSource;
import guru.nidi.graphviz.model.Node;

public class TreeMapGraphBuilder implements GraphBuilder {

    @Override
    public Graph buildGraph(Object collection) {

        if (!(collection instanceof TreeMap<?, ?>)) {
            throw new IllegalArgumentException("Expected TreeMap");
        }

        try {
            TreeMap<?, ?> map = (TreeMap<?, ?>) collection;
            Graph g = graph("treeMapGraph");

            if (map.isEmpty()) {
                return g;
            }
            
            // Entry<K,V> is a private nested class
            Field rootField = TreeMap.class.getDeclaredField("root");
            rootField.setAccessible(true);
            Object root = rootField.get(map);

            return buildRecursive(g, root);



        } catch (Exception e) {
            throw new RuntimeException("Could not inspect TreeSet structure", e);
        }


/*         List<Node> keys = new ArrayList<>();
        List<Node> values = new ArrayList<>();
        List<LinkSource> links = new ArrayList<>();

        for (Map.Entry<?, ?> entry : map.entrySet()) {

            Node key = node(entry.getKey().toString());
            Node value = node(entry.getValue().toString());

            keys.add(key);
            values.add(value);
            links.add(key.link(value));
        }
        // um Reihenfolge wiederherzustellen
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
                // key und value graph zusammenführen
                .with(
                        keyGraph,
                        valuesGraph)
                .with(links);

        return g; */
    }

    private Graph buildRecursive(Graph g, Object entry) throws Exception{
        if (entry == null) {
            return g;
        }

        Class<?> entryClass = entry.getClass();

        Field keyField = entryClass.getDeclaredField("key");
        Field valueField = entryClass.getDeclaredField("value");
        Field leftField = entryClass.getDeclaredField("left");
        Field rightField = entryClass.getDeclaredField("right");

        keyField.setAccessible(true);
        valueField.setAccessible(true);
        leftField.setAccessible(true);
        rightField.setAccessible(true);

        Object key = keyField.get(entry);
        Object value = valueField.get(entry);
        Object left = leftField.get(entry);
        Object right = rightField.get(entry);

        Node currentNode = node(String.valueOf(key) + ", " + String.valueOf(value)).with(Shape.CIRCLE);

        g = g.with(currentNode);

        if (left != null) {
            Object leftKey = keyField.get(left);
            Object leftValue = valueField.get(left);

            Node leftNode = node(String.valueOf(leftKey) + ", " + String.valueOf(leftValue));

            g = g.with(currentNode.link(to(leftNode)));

            g = buildRecursive(g, left);
        }

        if (right != null) {
            Object rightKey = keyField.get(right);
            Object rightValue = valueField.get(right);

            Node rightNode = node(String.valueOf(rightKey) + ", " + String.valueOf(rightValue));

            g = g.with(currentNode.link(to(rightNode)));

            g = buildRecursive(g, right);
        }


        return g;
    }

}
