package com.hhu.datastructureView;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import com.hhu.datastructureView.impl.javaUtil.ArrayListGraph;
import com.hhu.datastructureView.impl.javaUtil.LinkedHashSetGraph;
import com.hhu.datastructureView.impl.javaUtil.LinkedListGraph;
import com.hhu.datastructureView.impl.javaUtil.StackGraph;
import com.hhu.datastructureView.impl.javaUtil.TreeMapGraph;
import com.hhu.datastructureView.impl.javaUtil.TreeSetGraph;
import com.hhu.datastructureView.impl.progra.BinarySearchTreeGraph;
import com.hhu.datastructureView.impl.progra.HashSetGraph;
import com.hhu.datastructureView.impl.progra.PrograLinkedListGraph;
import com.hhu.prograDatastructures.generic.PrograBinarySearchTree;
import com.hhu.prograDatastructures.generic.PrograHashSet;
import com.hhu.prograDatastructures.generic.PrograLinkedList;

import guru.nidi.graphviz.model.Graph;


public class GraphBuilder {
    
    /* Matches a Builder to a Collection */
        
    public static Graph buildGraph(Object collection) {
        /* Pattern Matching Switch. Bindet den Case direkt an den Cast */
        return switch (collection) {

            /* Java.util */

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

            /* Progra */

            case PrograLinkedList<?> list ->
                PrograLinkedListGraph.buildGraph(list);

            case PrograBinarySearchTree<?> tree -> 
                BinarySearchTreeGraph.buildGraph(tree);

            case PrograHashSet<?> set -> 
                HashSetGraph.buildGraph(set);

            default ->
                throw new IllegalArgumentException(
                        "Unbekannte Collection: " + collection.getClass().getName());
        };
    }
}