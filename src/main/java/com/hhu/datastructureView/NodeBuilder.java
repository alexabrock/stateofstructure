package com.hhu.datastructureView;

import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.to;

import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.TreeMap;

import com.hhu.datastructures.prograDatastructures.generic.HashSet;

import guru.nidi.graphviz.attribute.Arrow;
import guru.nidi.graphviz.attribute.Attributes;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Rank.RankType;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

public class NodeBuilder {
    public static Node getNode(String label){
        // eindeutige Node-ID
        String id = java.util.UUID.randomUUID().toString();

        Node node = node(id).with(attr("label", label));

        return node;
    }
}
