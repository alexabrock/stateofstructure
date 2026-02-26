package com.hhu.util;

import java.util.concurrent.atomic.AtomicLong;

public class IdGen {
    private final AtomicLong counter = new AtomicLong(1);

    public long nextId() {
        return counter.getAndIncrement();
    }

    public String getId() {
        long id = nextId();
        return String.format("ID-%08d", id);
    }
}