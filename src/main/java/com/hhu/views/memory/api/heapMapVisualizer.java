package com.hhu.views.memory.api;

import java.util.Map;

public interface heapMapVisualizer<K, V> {

    public String drawHeap(Map<K, V> map);

    public String drawCallStack(Map<K, V> map);
}
