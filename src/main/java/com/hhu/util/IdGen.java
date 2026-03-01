package com.hhu.util;

import java.util.concurrent.atomic.AtomicLong;

// Class for generating unique incrementing IDs, starting from 1
//Used for visualization of the Heap and Call Stack, to assign unique IDs to objects and method calls

public class IdGen {
    private final AtomicLong counter = new AtomicLong(1);

    public long nextId() {
        return counter.getAndIncrement();
    }

}