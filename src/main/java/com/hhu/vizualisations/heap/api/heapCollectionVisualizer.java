package com.hhu.vizualisations.heap.api;

import java.util.Collection;

public interface heapCollectionVisualizer<E> {

    public String visualizeHeap(Collection<E> collection);

    public String visualizeCallStack(Collection<E> collection);

}
