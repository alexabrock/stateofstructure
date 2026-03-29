package com.hhu.util.datastructureView.api;

import guru.nidi.graphviz.model.Graph;

public interface GraphBuilder {
    /* buildGraph sollte von jeder Datenstruktur einzelnd implementiert werden. 
    Baut die abstrakte View */
    Graph buildGraph(Object data);
}
