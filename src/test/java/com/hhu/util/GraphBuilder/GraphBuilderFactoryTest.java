package com.hhu.util.GraphBuilder;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Map;
import org.junit.Test;

import com.hhu.datastructures.VLinkedList;
import com.hhu.datastructures.VStack;
import com.hhu.datastructures.VTreeMap;
import com.hhu.util.graphBuilder.GraphBuilderFactory;
import com.hhu.util.graphBuilder.api.GraphBuilder;

import guru.nidi.graphviz.model.Graph;

public class GraphBuilderFactoryTest {

    @Test
    public void testUseVListRenderer() {

        Collection<?> collection = new VLinkedList<>();

        GraphBuilder builder = GraphBuilderFactory.getBuilder(collection);

        Graph graph = builder.buildGraph(collection);

        assertEquals(graph.name().toString(), "listGraph");
    }

    @Test
    public void testUseVStackRenderer() {
        Collection<?> collection = new VStack<>();

        GraphBuilder builder = GraphBuilderFactory.getBuilder(collection);

        Graph graph = builder.buildGraph(collection);

        assertEquals(graph.name().toString(), "stackGraph");
    }

    @Test
    public void testUseVTreeMapRenderer() {
        Map<String, Integer> collection = new VTreeMap<>();

        GraphBuilder builder = GraphBuilderFactory.getBuilder(collection);

        Graph graph = builder.buildGraph(collection);

        assertEquals(graph.name().toString(), "mapGraph");
    }
    }



