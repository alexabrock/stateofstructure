package com.hhu.ui.panelBuilder;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/*
 * Returns a JPanel from an Object
 */
public class PanelBuilder {
    private PanelBuilder() {
    }
    /*
     * The repeaded applyDarkTheme calls are needed, so the individual panels are in
     * darkMode.
     */

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
        ThemeStyler.styleHeadline(label);
        label.setFont(label.getFont().deriveFont(50f));
        return label;
    }

    public static JPanel createCodePanel(String code, int lineNumber) {
        ThemeStyler.applyDarkTheme();
        return CodePanelBuilder.create(code, lineNumber);
    }
}