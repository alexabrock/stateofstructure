package com.hhu.views.panelBuilder;

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
import javax.swing.border.TitledBorder;

import com.formdev.flatlaf.FlatDarkLaf;
import com.hhu.views.Colors;

    /*
     * Handles the styling of Swing-Components in the view Package.
     */
public class ThemeStyler {

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
        label.setForeground(Color.WHITE);
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
        button.setFont(button.getFont().deriveFont(Font.BOLD, 18f));
        button.setMargin(new Insets(10, 24, 10, 24));
        button.setPreferredSize(new Dimension(160, 56));
    }

}
