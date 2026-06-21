package com.hhu.datastructureview;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import com.hhu.datastructureview.impl.javautil.ArrayListGraph;
import com.hhu.datastructureview.impl.javautil.HashSetGraph;
import com.hhu.datastructureview.impl.javautil.LinkedHashSetGraph;
import com.hhu.datastructureview.impl.javautil.LinkedListGraph;
import com.hhu.datastructureview.impl.javautil.StackGraph;
import com.hhu.datastructureview.impl.javautil.TreeMapGraph;
import com.hhu.datastructureview.impl.javautil.TreeSetGraph;
import com.hhu.datastructureview.impl.progra.BinarySearchTreeGraph;
import com.hhu.datastructureview.impl.progra.PrograHashSetGraph;
import com.hhu.datastructureview.impl.progra.PrograLinkedListGraph;
import com.hhu.progradatastructures.generic.PrograBinarySearchTree;
import com.hhu.progradatastructures.generic.PrograHashSet;
import com.hhu.progradatastructures.generic.PrograLinkedList;

import guru.nidi.graphviz.model.Graph;

public class GraphBuilder {

    /** Matches a Builder to a Collection */

    public static Graph buildGraph(Object collection) {
        // Pattern Matching Switch. Bindet den Case direkt an den Cast
        return switch (collection) {

            // Java.util

            case Stack<?> stack ->
                StackGraph.buildGraph(stack);

            case LinkedList<?> list ->
                LinkedListGraph.buildGraph(list);

            case TreeMap<?, ?> map ->
                TreeMapGraph.buildGraph(map);

            case ArrayList<?> list ->
                ArrayListGraph.buildGraph(list);

            case LinkedHashSet<?> set ->
                LinkedHashSetGraph.buildGraph(set);

            case TreeSet<?> set ->
                TreeSetGraph.buildGraph(set);

            case HashSet<?> set ->
                HashSetGraph.buildGraph(set);

            // Progra

            case PrograLinkedList<?> list ->
                PrograLinkedListGraph.buildGraph(list);

            case PrograBinarySearchTree<?> tree ->
                BinarySearchTreeGraph.buildGraph(tree);

            case PrograHashSet<?> set ->
                PrograHashSetGraph.buildGraph(set);

            default ->
                throw new IllegalArgumentException(
                        "Unbekannte Collection: " + collection.getClass().getName());
        };
    }
}