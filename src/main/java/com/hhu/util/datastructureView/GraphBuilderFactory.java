package com.hhu.util.datastructureView;

import java.util.Stack;

import com.hhu.datastructures.VArrayList;
import com.hhu.datastructures.VLinkedList;
import com.hhu.datastructures.VPrograList;
import com.hhu.datastructures.VStack;
import com.hhu.datastructures.VTreeMap;
import com.hhu.util.datastructureView.api.GraphBuilder;
import com.hhu.util.datastructureView.impl.VArrayListGraphBuilder;
import com.hhu.util.datastructureView.impl.VListGraphBuilder;
import com.hhu.util.datastructureView.impl.VPrograListBuilder;
import com.hhu.util.datastructureView.impl.VStackGraphBuilder;
import com.hhu.util.datastructureView.impl.VTreeMapGraphBuilder;

public class GraphBuilderFactory {

    public static GraphBuilder getBuilder(Object collection) {

        if (collection instanceof Stack) {
            return new VStackGraphBuilder<>();
        }

        if (collection instanceof VLinkedList<?>) {
            return new VListGraphBuilder();
        }

        if (collection instanceof VTreeMap<?, ?>) {
            return new VTreeMapGraphBuilder();
        }
        if (collection instanceof VPrograList) {
            return new VPrograListBuilder();
        }
        if(collection instanceof VArrayList<?>){
            return new VArrayListGraphBuilder();
        }

        throw new IllegalArgumentException("Unbekannte Collection");
    }

}