package com.hhu.util;

import com.hhu.ui.application.Application;

//Static Wrapper for DrawCalls
public class Visualizer {
    private static DrawCalls drawCalls;

    //changes the Datastructure to be Visualized. If changed, the old Visualizations are forgotten.
    public static void register(Object collection) {
        drawCalls = new DrawCalls(collection);
    }

    //Captures the state of a Datastructure
    public static void record() {
        if (drawCalls != null) {
            drawCalls.record();
        }
    }

    public static void show() {
        Application.start(drawCalls);
    }
}