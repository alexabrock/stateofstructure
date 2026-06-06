package com.hhu.util;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Filled with JPanels that capture the Memory View, the Datastructure View the
 * Name and the Code View from a state of a Collection
 */
public class DrawStep {
    private JPanel memory;
    private JPanel datastructure;
    private JLabel name;
    private JPanel codePanel;

    public DrawStep(JPanel memory, JPanel datastructure, JLabel name, JPanel codePanel) {
        this.memory = memory;
        this.datastructure = datastructure;
        this.name = name;
        this.codePanel = codePanel;
    }

    public JPanel codePanel() {
        return codePanel;
    }

    public JPanel memory() {
        return memory;
    }

    public JPanel datastructure() {
        return datastructure;
    }

    public JLabel name() {
        return name;
    }
}