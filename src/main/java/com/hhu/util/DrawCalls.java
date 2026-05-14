package com.hhu.util;

import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.hhu.ui.panelBuilder.PanelBuilder;

/* Handles iteration & state recording logic for a List of DrawSteps*/
public class DrawCalls {

    private LinkedList<DrawStep> drawCalls = new LinkedList<>();
    private Object collection;
    // -1, damit mit nextStep() das 1. (0.) Element returnt wird
    private int currentStepIndex = -1;

    DrawCalls(Object collection) {
        this.collection = collection;
    }

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

    void record() {

        JPanel memory = PanelBuilder.createMemoryPanel(collection);
        JPanel datastructure = PanelBuilder.createDatastructurePanel(collection);
        JLabel collectionName = PanelBuilder.createNameLabel(collection.getClass().getSimpleName());

        int lineNumber = Caller.findCallerLine();
        String code = Caller.findCallerClass();
        JPanel codePanel = PanelBuilder.createCodePanel(code, lineNumber);

        drawCalls.add(new DrawStep(memory, datastructure, collectionName, codePanel));
    }
}