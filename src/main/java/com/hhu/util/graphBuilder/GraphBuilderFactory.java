package com.hhu.util.graphBuilder;

import com.hhu.datastructures.VLinkedList;
import com.hhu.datastructures.VStack;
import com.hhu.datastructures.VTreeMap;
import com.hhu.util.graphBuilder.api.GraphBuilder;
import com.hhu.util.graphBuilder.impl.VListGraphBuilder;
import com.hhu.util.graphBuilder.impl.VStackGraphBuilder;
import com.hhu.util.graphBuilder.impl.VTreeMapGraphBuilder;

public class GraphBuilderFactory {

    public static GraphBuilder getBuilder(Object collection) {

        if (collection instanceof VStack<?>) {
            return new VStackGraphBuilder();
        }

        if (collection instanceof VLinkedList<?>) {
            return new VListGraphBuilder();
        }

        if (collection instanceof VTreeMap<?, ?>) {
            return new VTreeMapGraphBuilder();
        }

        throw new IllegalArgumentException("Unbekannte Collection");
    }

}