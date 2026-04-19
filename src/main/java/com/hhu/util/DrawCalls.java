package com.hhu.util;

import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.hhu.views.panelBuilder.PanelBuilder;

public class DrawCalls {

    private LinkedList<DrawStep> drawCalls = new LinkedList<>();
    //-1, damit mit nextStep() das 1. Element returnt wird
    private int currentStepIndex = -1;

    public boolean hasNextStep() {
        return currentStepIndex < drawCalls.size() - 1;
    }

    public boolean hasPrevStep() {
        return currentStepIndex > 0;
    }

    public DrawStep nextStep() {

        if (!hasNextStep()) {
            return drawCalls.getLast();
        }

        currentStepIndex++;
        return drawCalls.get(currentStepIndex);
    }

    public DrawStep prevStep() {

        if (!hasPrevStep()) {
            return drawCalls.getFirst();
        }

        currentStepIndex--;
        return drawCalls.get(currentStepIndex);
    }

    @Deprecated
    //Would be used, if record is not implemented by DataStructure
    public <E> void record(Object collection, String methodName) {

        JPanel memory = PanelBuilder.createMemoryPanel(collection);
        JPanel datastructure = PanelBuilder.createDatastructurePanel(collection);
        JLabel methodLabel = PanelBuilder.createMethodNameLabel(methodName);

        int lineNumber = FileManager.findCallerLine();
        String code = FileManager.findCallerClass();
        JPanel codePanel = PanelBuilder.createCodePanel(code, lineNumber);

        drawCalls.add(new DrawStep(memory, datastructure, methodLabel, codePanel));
    }

    public <E> void record(Object collection) {

        JPanel memory = PanelBuilder.createMemoryPanel(collection);
        JPanel datastructure = PanelBuilder.createDatastructurePanel(collection);
        JLabel name = PanelBuilder.createMethodNameLabel(collection.getClass().getSimpleName());

        int lineNumber = FileManager.findCallerLine();
        String code = FileManager.findCallerClass();
        JPanel codePanel = PanelBuilder.createCodePanel(code, lineNumber);

        drawCalls.add(new DrawStep(memory, datastructure, name, codePanel));
    }
}