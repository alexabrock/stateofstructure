package com.hhu.util.datastructureView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeMap;

import com.hhu.legacyDatastructures.VArrayList;
import com.hhu.legacyDatastructures.VLinkedList;
import com.hhu.legacyDatastructures.VStack;
import com.hhu.legacyDatastructures.VTreeMap;
import com.hhu.prograDatastructures.PrograList;
import com.hhu.util.datastructureView.api.GraphBuilder;
import com.hhu.util.datastructureView.impl.ArrayListGraphBuilder;
import com.hhu.util.datastructureView.impl.LinkedListGraphBuilder;
import com.hhu.util.datastructureView.impl.VPrograListBuilder;
import com.hhu.util.datastructureView.impl.StackGraphBuilder;
import com.hhu.util.datastructureView.impl.TreeMapGraphBuilder;

public class GraphBuilderFactory {

    public static GraphBuilder getBuilder(Object collection) {

        if (collection instanceof Stack) {
            return new StackGraphBuilder<>();
        }

        if (collection instanceof LinkedList<?>) {
            return new LinkedListGraphBuilder<>();
        }

        if (collection instanceof TreeMap<?, ?>) {
            return new TreeMapGraphBuilder();
        }
        if (collection instanceof PrograList) {
            return new VPrograListBuilder();
        }
        if (collection instanceof ArrayList<?>) {
            return new ArrayListGraphBuilder();
        }

        throw new IllegalArgumentException("Unbekannte Collection");
    }

}