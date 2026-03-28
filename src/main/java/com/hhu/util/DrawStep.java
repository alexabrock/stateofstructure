package com.hhu.util;

import javax.swing.JPanel;

import com.hhu.datastructures.VStack;
import com.hhu.views.panelBuilder.PanelBuilder;
public record DrawStep(JPanel memory, JPanel datastructure, String methodName) {
//currently only working for Stacks
public static <E> DrawStep fromStack(VStack<E> stack, String methodName) {
        
        JPanel memory = PanelBuilder.createMemoryPanel(stack);
        JPanel datastructure = PanelBuilder.createDatastructurePanel(stack);

        return new DrawStep(memory, datastructure, methodName);
    }
}