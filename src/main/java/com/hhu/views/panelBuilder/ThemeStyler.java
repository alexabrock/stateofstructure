package com.hhu.views.panelBuilder;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.formdev.flatlaf.FlatDarkLaf;

public final class ThemeStyler {

    private static boolean initialized;
    private static final Font HEADLINE_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 30);

    private ThemeStyler() {
    }

    public static void applyDarkTheme() {
        if (initialized) {
            return;
        }

        FlatDarkLaf.setup();
        initialized = true;
    }

    public static void styleModernCard(JPanel panel) {
        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(200, 211,217), 2),
                        BorderFactory.createEmptyBorder(12, 12, 12, 12)));
    }

    public static void styleToolbar(JPanel panel) {
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
    }

    public static void styleAccentLabel(JLabel label) {
        label.setFont(HEADLINE_FONT);
        label.setForeground(Color.WHITE);
    }

    public static void styleScrollPane(JComponent component) {
        component.putClientProperty("JComponent.roundRect", Boolean.TRUE);
        component.setBorder(BorderFactory.createEmptyBorder());
    }

}
