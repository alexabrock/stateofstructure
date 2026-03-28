package com.hhu.util.graphBuilder.api;

import guru.nidi.graphviz.model.Graph;

public interface GraphBuilder {
    /* buildGraph sollte von jeder Datenstruktur einzelnd implementiert werden. 
    Baut die abstrakte View */
    Graph buildGraph(Object data);
}
