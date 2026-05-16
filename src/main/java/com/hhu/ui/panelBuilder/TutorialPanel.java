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

public class TutorialPanel extends JPanel {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel slideContainer = new JPanel(cardLayout);
    private int currentSlide = 1;
    private final int totalSlides = 3;

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
        dialog.pack(); // Sizes dialog to fit content
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(parent); // Centers over the parent panel
        dialog.setVisible(true);
    }

    private void setupSlides() {
        slideContainer.add(createSlide("Welcome to Visualizer",
                "This tool helps you see your data structures in real-time."), "1");

        slideContainer.add(createSlide("Recording State",
                "Use <b>Visualizer.record()</b> inside your loops to capture steps."), "2");

        slideContainer.add(createSlide("Navigation",
                "Use the 'Next' and 'Prev' buttons in the app to walk through code execution."), "3");
                
    }

    private JPanel createPrettySlide(String title, String description, String imagePath) {
        JPanel card = new JPanel(new BorderLayout(15, 15));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Header with custom font
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitle.setForeground(new Color(52, 73, 94));

        // Description using HTML for line-wrapping
        JLabel lblDesc = new JLabel("<html><body style='width: 300px'>" + description + "</body></html>");
        lblDesc.setFont(new Font("SansSerif", Font.PLAIN, 14));

        card.add(lblTitle, BorderLayout.NORTH);
        card.add(lblDesc, BorderLayout.CENTER);

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
        JButton nextBtn = new JButton("Next");

        prevBtn.addActionListener(e -> {
            if (currentSlide > 1) {
                currentSlide--;
                cardLayout.show(slideContainer, String.valueOf(currentSlide));
            }
        });

        nextBtn.addActionListener(e -> {
            if (currentSlide < totalSlides) {
                currentSlide++;
                cardLayout.show(slideContainer, String.valueOf(currentSlide));
            } else {
                dialog.dispose(); // Close on last slide
            }
        });

        controls.add(prevBtn);
        controls.add(nextBtn);
        return controls;
    }
}