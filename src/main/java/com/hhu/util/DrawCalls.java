package com.hhu.util;

import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.hhu.ui.panels.PanelBuilder;

/** Handles iteration & state recording logic for a List of DrawSteps */
public class DrawCalls {

    private LinkedList<DrawStep> steps = new LinkedList<>();
    private Object collection;
    // -1, damit mit nextStep() das 1. (0.) Element returnt wird
    private int currentStepIndex = -1;

    DrawCalls(Object collection) {
        this.collection = collection;
    }

    public boolean hasNextStep() {
        return currentStepIndex < steps.size() - 1;
    }

    public boolean hasPrevStep() {
        return currentStepIndex > 0;
    }

    public DrawStep nextStep() {

        if (!hasNextStep()) {
            return steps.getLast();
        }

        currentStepIndex++;
        return steps.get(currentStepIndex);
    }

    public DrawStep prevStep() {

        if (!hasPrevStep()) {
            return steps.getFirst();
        }

        currentStepIndex--;
        return steps.get(currentStepIndex);
    }

    void record() {

        JPanel memory = PanelBuilder.createMemoryPanel(collection);
        JPanel dataStructure = PanelBuilder.createDatastructurePanel(collection);
        JLabel collectionName = PanelBuilder.createNameLabel(collection.getClass().getSimpleName());

        int lineNumber = Caller.findCallerLine();
        String code = Caller.findCallerClass();
        JPanel codePanel = PanelBuilder.createCodePanel(code, lineNumber);

        steps.add(new DrawStep(memory, dataStructure, collectionName, codePanel));
    }
}