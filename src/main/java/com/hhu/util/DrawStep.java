package com.hhu.util;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Filled with JPanels that capture the Memory View, the Data structure View the
 * Name and the Code View from a state of a Collection
 */
public class DrawStep {
    private JPanel memory;
    private JPanel dataStructure;
    private JLabel name;
    private JPanel codePanel;

    public DrawStep(JPanel memory, JPanel dataStructure, JLabel name, JPanel codePanel) {
        this.memory = memory;
        this.dataStructure = dataStructure;
        this.name = name;
        this.codePanel = codePanel;
    }

    public JPanel codePanel() {
        return codePanel;
    }

    public JPanel memory() {
        return memory;
    }

    public JPanel dataStructure() {
        return dataStructure;
    }

    public JLabel name() {
        return name;
    }
}