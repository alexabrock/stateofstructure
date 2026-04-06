package com.hhu.datastructures;

import java.util.ArrayList;

import com.hhu.datastructures.api.Visualizable;
import com.hhu.util.DrawCalls;


//TODO: implement for all public Methods (https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)
public class VArrayList<E> extends ArrayList<E> implements Visualizable{
    private DrawCalls drawCalls = new DrawCalls();

    public DrawCalls getDrawCalls() {
        return drawCalls;
    }

    public boolean add(E e) {
        drawCalls.record(this, "add()");
        return super.add(e);
    }
}
