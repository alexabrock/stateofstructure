package com.hhu.datastructureView;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import com.hhu.datastructureView.impl.javaUtil.ArrayListGraphBuilder;
import com.hhu.datastructureView.impl.javaUtil.LinkedHashSetGraphBuilder;
import com.hhu.datastructureView.impl.javaUtil.LinkedListGraphBuilder;
import com.hhu.datastructureView.impl.javaUtil.StackGraphBuilder;
import com.hhu.datastructureView.impl.javaUtil.TreeMapGraphBuilder;
import com.hhu.datastructureView.impl.javaUtil.TreeSetGraphBuilder;
import com.hhu.datastructureView.impl.progra.generic.BinarySearchTreeGraphBuilder;
import com.hhu.datastructureView.impl.progra.generic.HashSetGraphBuilder;
import com.hhu.datastructureView.impl.progra.generic.PrograLinkedListGraphBuilder;
import com.hhu.datastructures.prograDatastructures.generic.BinarySearchTree;
import com.hhu.datastructures.prograDatastructures.generic.HashSet;
import com.hhu.datastructures.prograDatastructures.generic.PrograLinkedList;

import guru.nidi.graphviz.model.Graph;


public class GraphBuilder {
    
    /* Matches a Builder to a Collection */
        
    public static Graph buildGraph(Object collection) {
        /* Pattern Matching Switch. Bindet den Case direkt an den Cast */
        return switch (collection) {

            /* Java.util */

            case Stack<?> stack ->
                StackGraphBuilder.buildGraph(stack);

            case LinkedList<?> list ->
                LinkedListGraphBuilder.buildGraph(list);

            case TreeMap<?, ?> map ->
                TreeMapGraphBuilder.buildGraph(map);

            case ArrayList<?> list ->
                ArrayListGraphBuilder.buildGraph(list);

            case LinkedHashSet<?> set ->
                LinkedHashSetGraphBuilder.buildGraph(set);

            case TreeSet<?> set ->
                TreeSetGraphBuilder.buildGraph(set);

            /* Progra */

            case PrograLinkedList<?> list ->
                PrograLinkedListGraphBuilder.buildGraph(list);

            case BinarySearchTree<?> tree -> 
                BinarySearchTreeGraphBuilder.buildGraph(tree);

            case HashSet<?> set -> 
                HashSetGraphBuilder.buildGraph(set);

            default ->
                throw new IllegalArgumentException(
                        "Unbekannte Collection: " + collection.getClass().getName());
        };
    }
}