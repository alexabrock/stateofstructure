package com.hhu.views.panelBuilder;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

//class is final so it cant be extended, private constructor so it cant be instantiated, since its a helper class
final class GraphPanelRenderer {

    private GraphPanelRenderer() {
    }

    // transforms an image into a JPanel
    static JPanel create(String title, BufferedImage image) {

        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setOpaque(false);
        imagePanel.add(new ScaledImagePanel(image), BorderLayout.CENTER);

        JLabel headline = new JLabel(title, SwingConstants.CENTER);
        headline.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        ThemeStyler.styleAccentLabel(headline);
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        ThemeStyler.styleModernCard(panel);

        panel.add(imagePanel, BorderLayout.CENTER);
        panel.add(headline, BorderLayout.NORTH);

        return panel;
    }

    private static final class ScaledImagePanel extends JPanel {
        private final BufferedImage image;

        private ScaledImagePanel(BufferedImage image) {
            this.image = image;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            if (image == null || getWidth() <= 0 || getHeight() <= 0) {
                return;
            }

            Graphics2D graphics2D = (Graphics2D) graphics.create();
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);

            double widthRatio = getWidth() / (double) image.getWidth();
            double heightRatio = getHeight() / (double) image.getHeight();
            double scale = Math.min(widthRatio, heightRatio);

            int scaledWidth = Math.max(1, (int) Math.round(image.getWidth() * scale));
            int scaledHeight = Math.max(1, (int) Math.round(image.getHeight() * scale));
            int x = (getWidth() - scaledWidth) / 2;
            int y = (getHeight() - scaledHeight) / 2;

            graphics2D.drawImage(image, x, y, scaledWidth, scaledHeight, null);
            graphics2D.dispose();
        }
    }
}