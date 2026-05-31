package com.hhu.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoMidnightBlueIJTheme;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.hhu.ui.Colors;

/*
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

    /*
     * adds a border around the Panel
     */
    public static void styleModernCard(JPanel panel) {
        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Colors.LINE_BORDER, 2),
                        BorderFactory.createEmptyBorder(12, 12, 12, 12)));
    }

    /*
     * adds a border around the Panel
     */
    public static void styleToolbar(JPanel panel) {
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
    }

    /*
     * adds a border around the Panel
     */
    public static void styleCenterPanel(JPanel panel) {
        panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
    }

    /*
     * sets font and font-color of the Panel
     */
    public static void styleHeadline(JLabel label) {
        label.setFont(HEADLINE_FONT);
        label.setForeground(Color.BLACK);
    }

    /*
     * adds a border around the Panel
     */
    public static void styleScrollPane(JComponent component) {
        component.setBorder(BorderFactory.createEmptyBorder());
    }

    /*
     * sets the font, margin and size of the button
     */
    public static void styleNavigationButton(JButton button) {
        button.setFont(button.getFont().deriveFont(Font.BOLD, 16f));
        button.setPreferredSize(new Dimension(160, 56));

        button.setBackground(Color.WHITE);
        button.setForeground(new Color(26, 26, 26));
        button.setFocusPainted(false);

        // Erstellt einen hauchdünnen, modernen Rahmen statt des klobigen
        // Swing-Standards
        button.setBorder(BorderFactory.createLineBorder(new Color(218, 220, 224), 1));
    }

}
