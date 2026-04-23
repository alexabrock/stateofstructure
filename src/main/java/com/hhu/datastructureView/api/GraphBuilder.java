package com.hhu.datastructureView.api;

import guru.nidi.graphviz.model.Graph;

public interface GraphBuilder {
    /* Builds a GraphViz Graph from a given collection*/
    
    Graph buildGraph(Object collection);
}