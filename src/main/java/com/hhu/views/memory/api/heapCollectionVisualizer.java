package com.hhu.views.memory.api;

import java.util.Collection;

public interface heapCollectionVisualizer<E> {

    public String drawHeap(Collection<E> collection);

    public String drawCallStack(Collection<E> collection);

}
