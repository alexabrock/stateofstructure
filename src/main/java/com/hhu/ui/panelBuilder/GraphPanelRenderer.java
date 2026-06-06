package com.hhu.ui.panelBuilder;

import java.awt.BorderLayout;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.hhu.util.ThemeStyler;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import org.jdesktop.swingx.JXImageView;
/**
 *  Renders Graphviz DOT output as a zoomable Swing panel.
 */
class GraphPanelRenderer {
    private static final double MIN_ZOOM = 0.3;
    private static final double MAX_ZOOM = 2.0;
    private static final double ZOOM_IN_FACTOR = 1.1;
    private static final double ZOOM_OUT_FACTOR = 0.9;

    private GraphPanelRenderer() {
    }

    /**
     * Transforms a DOT into a zoomable JPanel
     */
    static JPanel create(String title, String dot) {

        BufferedImage image = Graphviz.fromString(dot).width(1500).render(Format.PNG).toImage();

        JPanel imagePanel = new JPanel(new BorderLayout());
        ThemeStyler.styleApplicationBackground(imagePanel);
        imagePanel.add(createImageView(image), BorderLayout.CENTER);

        JLabel headline = new JLabel(title, SwingConstants.CENTER);
        ThemeStyler.styleHeadline(headline);
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        ThemeStyler.stylePanel(panel);

        panel.add(headline, BorderLayout.NORTH);
        panel.add(imagePanel, BorderLayout.CENTER);

        return panel;
    }

    private static JXImageView createImageView(BufferedImage image) {
        JXImageView imageView = new JXImageView();
        imageView.setImage(image);
        imageView.setEditable(true);
        imageView.setDragEnabled(false);
        imageView.addMouseWheelListener(event -> zoomWithMouseWheel(imageView, event));
        return imageView;
    }

    private static void zoomWithMouseWheel(JXImageView imageView, MouseWheelEvent event) {
        event.consume();
        double zoomFactor = event.getWheelRotation() < 0 ? ZOOM_IN_FACTOR : ZOOM_OUT_FACTOR;
        imageView.setScale(clamp(imageView.getScale() * zoomFactor, MIN_ZOOM, MAX_ZOOM));
    }

    private static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}