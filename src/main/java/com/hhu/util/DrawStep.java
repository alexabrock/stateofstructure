package com.hhu.util;

import javax.swing.JPanel;

import com.hhu.datastructures.VStack;
import com.hhu.views.panelBuilder.PanelBuilder;

import guru.nidi.graphviz.model.Graph;

//Kann nicht direkt JPanels speichern, weil VStack dann eine JPanel Liste speichert und damit LJV Swing-Jpanels reflektieren muss

public record DrawStep(String memoryDot, Graph structureDot) {

    public static <E> DrawStep fromStack(VStack<E> stack) {
        String memoryDot = PanelBuilder.getMemoryPanelDot(stack);

        Graph structureDot = PanelBuilder.getDatastructureGraph(stack);

        return new DrawStep(memoryDot, structureDot);
    }
}