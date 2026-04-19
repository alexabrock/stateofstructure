package com.hhu.views.panelBuilder;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelBuilder {

    public static JPanel createDatastructurePanel(Object collection) {
        ThemeStyler.applyDarkTheme();
        return DataStructurePanelBuilder.create(collection);
    }

    public static JPanel createMemoryPanel(Object collection) {
        ThemeStyler.applyDarkTheme();
        return MemoryPanelBuilder.create(collection);
    }

    public static JPanel createCodePanel(String code) {
        ThemeStyler.applyDarkTheme();
        return CodePanelBuilder.create(code);
    }

    public static JLabel createMethodNameLabel(String methodName) {
        JLabel label = new JLabel(methodName, SwingConstants.CENTER);
        ThemeStyler.styleAccentLabel(label);
        label.setFont(label.getFont().deriveFont(50f));
        return label;
    }

    public static JPanel createCodePanel(String code, int lineNumber) {
        ThemeStyler.applyDarkTheme();
        return CodePanelBuilder.create(code, lineNumber);
    }
}