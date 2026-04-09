package com.hhu.util.datastructureView;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import com.hhu.legacyDatastructures.VArrayList;
import com.hhu.legacyDatastructures.VLinkedList;
import com.hhu.legacyDatastructures.VStack;
import com.hhu.legacyDatastructures.VTreeMap;
import com.hhu.util.datastructureView.api.GraphBuilder;
import com.hhu.util.datastructureView.impl.javaUtil.ArrayListGraphBuilder;
import com.hhu.util.datastructureView.impl.javaUtil.LinkedHashSetGraphBuilder;
import com.hhu.util.datastructureView.impl.javaUtil.LinkedListGraphBuilder;
import com.hhu.util.datastructureView.impl.javaUtil.StackGraphBuilder;
import com.hhu.util.datastructureView.impl.javaUtil.TreeMapGraphBuilder;
import com.hhu.util.datastructureView.impl.javaUtil.TreeSetGraphBuilder;

public class GraphBuilderFactory {

    public static GraphBuilder getBuilder(Object collection) {

        if (collection instanceof Stack) {
            return new StackGraphBuilder();
        }

        if (collection instanceof LinkedList<?>) {
            return new LinkedListGraphBuilder();
        }

        if (collection instanceof TreeMap<?,?>) {
            return new TreeMapGraphBuilder();
        }
        if (collection instanceof ArrayList<?>) {
            return new ArrayListGraphBuilder();
        }
        if (collection instanceof LinkedHashSet<?>) {
            return new LinkedHashSetGraphBuilder();
        }
        if (collection instanceof TreeSet<?>) {
            return new TreeSetGraphBuilder();
        }

        throw new IllegalArgumentException("Unbekannte Collection");
    }

}