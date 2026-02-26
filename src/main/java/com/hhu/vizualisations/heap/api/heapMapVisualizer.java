package com.hhu.vizualisations.heap.api;

import java.util.Map;

public interface heapMapVisualizer<K, V> {

    public String visualizeHeap(Map<K, V> map);

    public String visualizeCallStack(Map<K, V> map);
}
