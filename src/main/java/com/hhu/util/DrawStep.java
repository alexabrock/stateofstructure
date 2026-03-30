package com.hhu.util;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.hhu.datastructures.VStack;
import com.hhu.views.panelBuilder.PanelBuilder;
public record DrawStep(JPanel memory, JPanel datastructure, JLabel methodLabel) {
//currently only working for Stacks
public static <E> DrawStep fromStack(VStack<E> stack, String methodLabel) {
        
        JPanel memory = PanelBuilder.createMemoryPanel(stack);
        JPanel datastructure = PanelBuilder.createDatastructurePanel(stack);
        JLabel method = PanelBuilder.createMethodNameLabel(methodLabel);

        return new DrawStep(memory, datastructure, method);
    }
}