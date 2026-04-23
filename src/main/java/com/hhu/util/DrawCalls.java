package com.hhu.util;

import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.hhu.views.panelBuilder.PanelBuilder;

/* Handles iteration & state recording logic for a List of DrawSteps*/
public class DrawCalls {

    private LinkedList<DrawStep> drawCalls = new LinkedList<>();
    // -1, damit mit nextStep() das 1. (0.) Element returnt wird
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
    // Would be used by datastructure, if record is not implemented by DataStructure
    //Is currently being used by legacyDatastructures
    public <E> void record(Object collection, String methodName) {

        JPanel memory = PanelBuilder.createMemoryPanel(collection);
        JPanel datastructure = PanelBuilder.createDatastructurePanel(collection);
        JLabel methodLabel = PanelBuilder.createNameLabel(methodName);

        int lineNumber = Caller.findCallerLine();
        String code = Caller.findCallerClass();
        JPanel codePanel = PanelBuilder.createCodePanel(code, lineNumber);

        drawCalls.add(new DrawStep(memory, datastructure, methodLabel, codePanel));
    }

    public <E> void record(Object collection) {

        JPanel memory = PanelBuilder.createMemoryPanel(collection);
        JPanel datastructure = PanelBuilder.createDatastructurePanel(collection);
        JLabel collectionName = PanelBuilder.createNameLabel(collection.getClass().getSimpleName());

        int lineNumber = Caller.findCallerLine();
        String code = Caller.findCallerClass();
        JPanel codePanel = PanelBuilder.createCodePanel(code, lineNumber);

        drawCalls.add(new DrawStep(memory, datastructure, collectionName, codePanel));
    }
}