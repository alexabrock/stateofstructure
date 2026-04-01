package com.hhu.util;


import javax.swing.JLabel;
import javax.swing.JPanel;
//DTO 
public class DrawStep{
    private JPanel memory;
    private JPanel datastructure;
    private JLabel methodLabel;

    public DrawStep(JPanel memory, JPanel datastructure, JLabel methodLabel) {
        this.memory = memory;
        this.datastructure = datastructure;
        
        this.methodLabel = methodLabel;
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