package com.hhu.ui.panelBuilder;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.hhu.util.ThemeStyler;

public class TutorialPanel extends JPanel {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel slideContainer = new JPanel(cardLayout);
    private int currentSlide = 1;
    private final int totalSlides = 4;

    public TutorialPanel(JPanel parent) {
        // 1. Find the top-level Window to attach the Dialog
        Window parentWindow = SwingUtilities.windowForComponent(parent);
        JFrame frame = (parentWindow instanceof JFrame) ? (JFrame) parentWindow : null;

        // 2. Create the Modal Dialog
        JDialog dialog = new JDialog(frame, "Getting Started", true);
        dialog.setLayout(new BorderLayout());

        // 3. Setup Slides
        setupSlides();
        dialog.add(slideContainer, BorderLayout.CENTER);

        // 4. Setup Control Buttons
        dialog.add(createControlPanel(dialog), BorderLayout.SOUTH);

        // 5. Display Settings
        dialog.pack(); 
        dialog.setSize(1000, 800);
        dialog.setLocationRelativeTo(parent); // Centers over the parent panel
        dialog.setVisible(true);
    }

    private void setupSlides() {
        slideContainer.add(
                createPrettySlide("welcome.png"), "1");
        slideContainer.add(
                createPrettySlide("recording.png"), "2");
                
        slideContainer.add(
                createPrettySlide("navigation.png"), "3");

        slideContainer.add(
                createPrettySlide("compileButton.png"), "4");
                
    }

    private JPanel createPrettySlide(String imagePath) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);

        java.net.URL imgUrl = getClass().getClassLoader().getResource(imagePath);
        if (imgUrl != null) {
            javax.swing.ImageIcon icon = new javax.swing.ImageIcon(imgUrl);

            // Zielbreite festlegen
            int targetWidth = 900;
            // Höhe proportional berechnen
            int targetHeight = (int) (targetWidth * 1232.0 / 1654.0);

            java.awt.Image img = icon.getImage().getScaledInstance(targetWidth, targetHeight,
                    java.awt.Image.SCALE_SMOOTH);
            card.add(new JLabel(new javax.swing.ImageIcon(img)), BorderLayout.CENTER);
        }

        return card;
    }

    private JPanel createSlide(String title, String content) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel titleLabel = new JLabel("<html><h2>" + title + "</h2></html>", SwingConstants.CENTER);
        JLabel contentLabel = new JLabel("<html><div style='text-align: center;'>" + content + "</div></html>",
                SwingConstants.CENTER);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(contentLabel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createControlPanel(JDialog dialog) {
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton prevBtn = new JButton("Previous");
        prevBtn.setEnabled(false);
        JButton nextBtn = new JButton("Next");

        prevBtn.addActionListener(e -> {
            if (currentSlide > 1) {
                currentSlide--;
                cardLayout.show(slideContainer, String.valueOf(currentSlide));
                prevBtn.setEnabled(currentSlide > 1);
                if (currentSlide != totalSlides) {
                    nextBtn.setText("Next");
                }
            }
        });

        nextBtn.addActionListener(e -> {
            if (currentSlide < totalSlides) {
                currentSlide++;
                cardLayout.show(slideContainer, String.valueOf(currentSlide));

                if (currentSlide == totalSlides) {
                    nextBtn.setText("Close");
                }
                if (currentSlide > 1) {
                    prevBtn.setEnabled(true);
                }
            }
            else {
                dialog.dispose();
            }
        });

        ThemeStyler.styleNavigationButton(prevBtn);
        ThemeStyler.styleNavigationButton(nextBtn);

        controls.add(prevBtn);
        controls.add(nextBtn);
        return controls;
    }
}