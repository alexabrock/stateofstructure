package com.hhu.views.panelBuilder;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelBuilder {
    //die einzelnen applyDarkTheme Aufrufe sind damit die einzelnen Panel auch im darkMode sind.

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

    public static JLabel createNameLabel(String name) {
        JLabel label = new JLabel(name, SwingConstants.CENTER);
        ThemeStyler.styleAccentLabel(label);
        label.setFont(label.getFont().deriveFont(50f));
        return label;
    }

    public static JPanel createCodePanel(String code, int lineNumber) {
        ThemeStyler.applyDarkTheme();
        return CodePanelBuilder.create(code, lineNumber);
    }
}