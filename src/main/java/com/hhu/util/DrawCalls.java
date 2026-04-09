package com.hhu.util;

import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.hhu.views.panelBuilder.PanelBuilder;

//Manages a List of DrawSteps
public class DrawCalls {

    private LinkedList<DrawStep> drawCalls = new LinkedList<>();
    private int currentStepIndex = 0;

    public boolean hasNextStep() {
        return currentStepIndex < drawCalls.size();
    }

    public DrawStep nextStep() {
        if (!hasNextStep()) {
            return drawCalls.getLast();
        }
        return drawCalls.get(currentStepIndex++); // show current, then advance
    }

    public DrawStep prevStep() {
        if (currentStepIndex <= 1) {
            return drawCalls.get(0);
        }
        currentStepIndex -= 2; // undo the last next-advance AND go one back
        return drawCalls.get(currentStepIndex++); // show it, leave index just past it
    }

    // needs to be Object, since Progra DS != Collection
    public <E> void record(Object collection, String methodName) {

        JPanel memory = PanelBuilder.createMemoryPanel(collection);
        JPanel datastructure = PanelBuilder.createDatastructurePanel(collection);
        JLabel methodLabel = PanelBuilder.createMethodNameLabel(methodName);

        int lineNumber = FileManager.findCallerLine();
        String code = FileManager.findCallerClass();
        JPanel codePanel = PanelBuilder.createCodePanel(code, lineNumber);

        this.drawCalls.add(new DrawStep(memory, datastructure, methodLabel, codePanel));
    }

}