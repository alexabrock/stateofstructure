package com.hhu.datastructureView;

import java.util.Stack;


import guru.nidi.graphviz.model.Graph;


public class GraphBuilder {
    
    /* Matches a Builder to a Collection */
        
    public static Graph buildGraph(Object collection) {
        /* Pattern Matching Switch. Bindet den Case direkt an den Cast */
        return switch (collection) {

            /* Java.util */

            case Stack<?> stack ->
                StackGraphBuilder.buildGraph(stack);

            default ->
                throw new IllegalArgumentException(
                        "Unbekannte Collection: " + collection.getClass().getName());
        };
    }
}