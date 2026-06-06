package com.hhu.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.formdev.flatlaf.FlatLightLaf;
import com.hhu.ui.Colors;

/**
 * Handles the styling of Swing-Components in the view Package.
 */
public class ThemeStyler {

    private static boolean initialized;
    private static final Font HEADLINE_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 30);

    private ThemeStyler() {
    }

    public static void applyTheme() {
        if (initialized) {
            return;
        }
        FlatLightLaf.setup();
        initialized = true;
    }

    public static void styleApplicationBackground(JPanel panel) {
        panel.setBackground(Colors.APPLICATION_BACKGROUND);
        panel.setOpaque(true);
    }

    /**
     * Adds a border around the Panel
     */
    public static void stylePanel(JPanel panel) {
        styleApplicationBackground(panel);
        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Colors.LINE_BORDER, 2),
                        BorderFactory.createEmptyBorder(12, 12, 12, 12)));
    }

    /**
     * Adds a border around the Panel
     */
    public static void styleToolbar(JPanel panel) {
        styleApplicationBackground(panel);
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
    }

    /**
     * Adds a border around the Panel
     */
    public static void styleCenterPanel(JPanel panel) {
        styleApplicationBackground(panel);
        panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
    }

    /**
     * Sets font and font-color of the Panel
     */
    public static void styleHeadline(JLabel label) {
        label.setFont(HEADLINE_FONT);
        label.setForeground(Colors.FONT);
    }

    public static void styleCodeCard(JPanel panel) {
        stylePanel(panel);
        panel.setBackground(Colors.CODE_PANEL_BACKGROUND);
    }

    /**
     * Adds a border around the Panel
     */
    public static void styleScrollPane(JComponent component) {
        component.setBorder(BorderFactory.createEmptyBorder());
    }

    /**
     * Sets the font, margin and size of the button
     */
    public static void styleNavigationButton(JButton button) {
        button.setFont(button.getFont().deriveFont(Font.BOLD, 16f));
        button.setPreferredSize(new Dimension(160, 56));

        button.setBackground(Color.WHITE);
        button.setForeground(Colors.FONT);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(218, 220, 224), 1));
    }

}
