package com.hhu.views.panelBuilder;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.formdev.flatlaf.FlatDarkLaf;

public final class ThemeStyler {

    private static boolean initialized;

    private ThemeStyler() {
    }

    public static void applyDarkTheme() {
        if (initialized) {
            return;
        }

        FlatDarkLaf.setup();
        initialized = true;
    }

    public static Color uiColor(String key, Color fallback) {
        Color color = UIManager.getColor(key);
        return color != null ? color : fallback;
    }

    public static void styleModernCard(JPanel panel) {
        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(uiColor("Component.borderColor", new Color(70, 70, 70))),
                        BorderFactory.createEmptyBorder(12, 12, 12, 12)));
    }

    public static void styleToolbar(JPanel panel) {
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
    }

    public static void styleAccentLabel(JLabel label) {
        label.setForeground(uiColor("Component.accentColor", new Color(125, 152, 255)));
    }

    public static void styleScrollPane(JComponent component) {
        component.putClientProperty("JComponent.roundRect", Boolean.TRUE);
        component.setBorder(BorderFactory.createEmptyBorder());
    }

}
