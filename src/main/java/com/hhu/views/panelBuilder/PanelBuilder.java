package com.hhu.views.panelBuilder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.formdev.flatlaf.FlatDarculaLaf;

public class PanelBuilder {

    public static JPanel createDatastructurePanel(Object collection) {
        FlatDarculaLaf.setup();
        return DataStructurePanelBuilder.create(collection);
    }

    public static JPanel createMemoryPanel(Object collection) {
        FlatDarculaLaf.setup();
        return MemoryPanelBuilder.create(collection);
    }

    public static JPanel createCodePanel(String code) {
        FlatDarculaLaf.setup();
        return CodePanelBuilder.create(code);
    }

    public static JLabel createMethodNameLabel(String methodName) {
            JLabel label = new JLabel(methodName, SwingConstants.CENTER);
            label.setFont(label.getFont().deriveFont(50f));
            label.setForeground(Color.pink);
            return label;
    }

    public static JPanel createCodePanel(String code, int lineNumber) {
        FlatDarculaLaf.setup();
        return CodePanelBuilder.create(code, lineNumber);
    }
}