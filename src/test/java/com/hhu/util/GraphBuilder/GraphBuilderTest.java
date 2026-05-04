package com.hhu.util.graphBuilder;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;

import com.hhu.datastructureView.GraphBuilder;
import com.hhu.datastructures.legacyDatastructures.VStack;

import guru.nidi.graphviz.model.Graph;

public class GraphBuilderTest {


    @Test
    public void testUseStackRenderer() {
        Collection<?> collection = new VStack<>();

        Graph graph = GraphBuilder.buildGraph(collection);

        assertEquals(graph.name().toString(), "stackGraph");
    }
    }



