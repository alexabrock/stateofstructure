package com.hhu.util;

import com.hhu.ui.application.Application;

//Static Wrapper for DrawCalls
public class Visualizer {
    private static DrawCalls drawCalls;

    private Visualizer() {
    }

    //changes the Datastructure to be visualized. If changed, the old visualizations are forgotten.
    public static void register(Object collection) {
        drawCalls = new DrawCalls(collection);
    }

    //Captures the state of a datastructure
    public static void record() {
        if (drawCalls != null) {
            drawCalls.record();
        }
    }

    public static void show() {
        Application.start(drawCalls);
    }

    //To get the DrawCalls back
    public static DrawCalls getDrawCalls() {
        return drawCalls;
    }
}