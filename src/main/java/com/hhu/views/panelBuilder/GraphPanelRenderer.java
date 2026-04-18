package com.hhu.views.panelBuilder;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
//class is final so it cant be extended, private constructor so it cant be instantiated, since its a helper class
final class GraphPanelRenderer {

    private GraphPanelRenderer() {
    }
    //transforms an image into a JPanel
    static JPanel create(String title, BufferedImage image) {

        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel headline = new JLabel(title, SwingConstants.CENTER);
        headline.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        ThemeStyler.styleAccentLabel(headline);

        JPanel panel = new JPanel(new BorderLayout(0, 8));
        ThemeStyler.styleModernCard(panel);

        panel.add(imageLabel, BorderLayout.CENTER);
        panel.add(headline, BorderLayout.NORTH);

        return panel;
    }
}