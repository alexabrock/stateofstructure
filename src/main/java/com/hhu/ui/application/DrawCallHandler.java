package com.hhu.ui.application;

import com.hhu.util.DrawCalls;
import com.hhu.util.DrawStep;

/**
 * Holds the currently displayed DrawCalls and exposes navigation through its
 * DrawSteps.
 */
public class DrawCallHandler {

    private DrawCalls drawCalls;

    public DrawCallHandler(DrawCalls drawCalls) {
        this.drawCalls = drawCalls;
    }

    public DrawStep nextStep() {
        return drawCalls.nextStep();
    }

    public DrawStep previousStep() {
        return drawCalls.prevStep();
    }

    public boolean hasNextStep() {
        return drawCalls.hasNextStep();
    }

    public boolean hasPreviousStep() {
        return drawCalls.hasPrevStep();
    }

    public void replaceDrawCalls(DrawCalls drawCalls) {
        this.drawCalls = drawCalls;
    }
}