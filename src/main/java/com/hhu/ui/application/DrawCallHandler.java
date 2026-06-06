package com.hhu.ui.application;

import com.hhu.util.DrawCalls;
import com.hhu.util.DrawStep;

/**
 * Holds the currently displayed DrawCalls and exposes navigation through its
 * DrawSteps.
 */
class DrawCallHandler {

    private DrawCalls drawCalls;

    DrawCallHandler(DrawCalls drawCalls) {
        this.drawCalls = drawCalls;
    }

    DrawStep nextStep() {
        return drawCalls.nextStep();
    }

    DrawStep previousStep() {
        return drawCalls.prevStep();
    }

    boolean hasNextStep() {
        return drawCalls.hasNextStep();
    }

    boolean hasPreviousStep() {
        return drawCalls.hasPrevStep();
    }

    void replaceDrawCalls(DrawCalls drawCalls) {
        this.drawCalls = drawCalls;
    }
}