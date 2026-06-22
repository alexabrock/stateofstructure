package com.hhu.ui.application;

import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import com.hhu.ui.panels.codepanel.CodePanelScroller;
import com.hhu.ui.panels.drawsteppanel.DrawStepPanel;
import com.hhu.util.DrawCalls;
import com.hhu.util.compiler.Compiler;

/*
 * Compiles the Text inside the codePanel and updated the Appliation.drawStep
 * It uses the SwingWorker, since the calculation of the Graphs takes a while
 * and without SwingWorker, the calculation can't happen in the background and
 * the Application freezes until the compilation is done.
 */
public class CompilationController {

    private static final String COMPILE_LABEL = "Compile";
    private static final String COMPILING_LABEL = "Compiling...";

    private final DrawCallHandler applicationState;
    private final DrawStepPanel drawStepView;
    private final CodePanelScroller codePanelScroller;
    private final Runnable afterCompilation;

    public CompilationController(
            DrawCallHandler drawCallHandler,
            DrawStepPanel drawStepView,
            CodePanelScroller codePanelScroller,
            Runnable afterCompilation) {
        this.applicationState = drawCallHandler;
        this.drawStepView = drawStepView;
        this.codePanelScroller = codePanelScroller;
        this.afterCompilation = afterCompilation;
    }

    public void compile(JPanel centerPanel, JButton compileButton) {
        String code = codePanelScroller.readCode(centerPanel);
        markCompilationStarted(compileButton);
        createWorker(centerPanel, compileButton, code).execute();
    }

    private SwingWorker<DrawCalls, Void> createWorker(JPanel centerPanel, JButton compileButton, String code) {
        return new SwingWorker<>() {
            @Override
            protected DrawCalls doInBackground() throws InterruptedException {
                return Compiler.compile(code);
            }

            @Override
            protected void done() {
                try {
                    showCompiledDrawCalls(
                            centerPanel,
                            get() // get() returns result of doInBackground
                            );
                } catch (ExecutionException e) {
                    /*
                     * Incase the doInBackground went wrong, a ExecutionException is thrown.
                     * The ExecutionException is a wrapper around, in this case, a
                     * CompilationException.
                     * The wrapped EXception is retrieved by e.getCause
                     */
                    showCompilationError(centerPanel, e.getCause().getMessage());
                } catch (InterruptedException _) {
                    // InterruptedException is thrown if the done() Thread is interrupted 
                    Thread.currentThread().interrupt();
                    showCompilationError(centerPanel, "Compilation interrupted");
                } finally {
                    // finally, damit egal was passiert der Button wieder enabled wird
                    markCompilationFinished(compileButton);
                    afterCompilation.run();
                }
            }
        };
    }

    private void showCompiledDrawCalls(JPanel centerPanel, DrawCalls drawCalls) {
        // forget the old Datastructure vizualisation, if the Code is recompiled
        applicationState.replaceDrawCalls(drawCalls);
        drawStepView.showStep(centerPanel, applicationState.nextStep());
    }

    private void markCompilationStarted(JButton compileButton) {
        compileButton.setEnabled(false);
        compileButton.setText(COMPILING_LABEL);
    }

    private void markCompilationFinished(JButton compileButton) {
        compileButton.setEnabled(true);
        compileButton.setText(COMPILE_LABEL);
    }

    private void showCompilationError(JPanel centerPanel, String message) {
        JOptionPane.showMessageDialog(
                centerPanel,
                message,
                "Compilation Error",
                JOptionPane.ERROR_MESSAGE);
    }
}