package com.hhu.util;

import javax.swing.JLabel;
import javax.swing.JPanel;

//DTO 
public class DrawStep {
    private JPanel memory;
    private JPanel datastructure;
    private JLabel methodLabel;
    private JPanel codePanel;

    public DrawStep(JPanel memory, JPanel datastructure, JLabel methodLabel, JPanel codPanel) {
        this.memory = memory;
        this.datastructure = datastructure;
        this.methodLabel = methodLabel;
        this.codePanel = codPanel;
    }

    public JPanel codPanel() {
        return codePanel;
    }

    public JPanel memory() {
        return memory;
    }

    public JPanel datastructure() {
        return datastructure;
    }

    public JLabel methodLabel() {
        return methodLabel;
    }
}