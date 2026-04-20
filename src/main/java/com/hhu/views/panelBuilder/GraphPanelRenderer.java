package com.hhu.views.panelBuilder;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
final class GraphPanelRenderer {

    private GraphPanelRenderer() {
    }

    // transforms an image into a JPanel
    static JPanel create(String title, BufferedImage image) {
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.add(new ScaledImagePanel(image), BorderLayout.CENTER);

        JLabel headline = new JLabel(title, SwingConstants.CENTER);
        ThemeStyler.styleAccentLabel(headline);
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        ThemeStyler.styleModernCard(panel);

        panel.add(imagePanel, BorderLayout.CENTER);
        panel.add(headline, BorderLayout.NORTH);

        return panel;
    }

    //Only used, for the zoom of the datastructure and memory panel 
    private static final class ScaledImagePanel extends JPanel {
        private static final double MIN_ZOOM = 0.3;
        private static final double MAX_ZOOM = 2.0;
        private static final double ZOOM_STEP = 0.1;

        private final BufferedImage image;
        private double zoomFactor = 1.0;
        private double offsetX = 0.0;
        private double offsetY = 0.0;
        private Point dragAnchor;

        private ScaledImagePanel(BufferedImage image) {
            this.image = image;
            setOpaque(false);
            installZoomInteraction();
        }

        private void installZoomInteraction() {
            addMouseWheelListener(this::handleMouseWheelZoom);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent event) {
                    if (event.getClickCount() == 2) {
                        toggleDoubleClickZoom();
                    }
                }

                @Override
                public void mousePressed(MouseEvent event) {
                    dragAnchor = event.getPoint();
                    setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                }

                @Override
                public void mouseReleased(MouseEvent event) {
                    dragAnchor = null;
                    setCursor(Cursor.getDefaultCursor());
                }
            });
            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent event) {
                    handleMouseDragged(event);
                }
            });
        }

        private void handleMouseWheelZoom(MouseWheelEvent event) {
            event.consume();
            if (event.getWheelRotation() < 0) {
                changeZoom(ZOOM_STEP);
                return;
            }
            changeZoom(-ZOOM_STEP);
        }

        private void toggleDoubleClickZoom() {
            if (zoomFactor > 1.0) {
                zoomFactor = 1.0;
                offsetX = 0.0;
                offsetY = 0.0;
            } else {
                zoomFactor = 3.0;
            }
            clampOffsets();
            repaint();
        }

        private void changeZoom(double delta) {
            zoomFactor = clamp(zoomFactor + delta, MIN_ZOOM, MAX_ZOOM);
            clampOffsets();
            repaint();
        }

        private void handleMouseDragged(MouseEvent event) {
            if (dragAnchor == null) {
                return;
            }
            int deltaX = event.getX() - dragAnchor.x;
            int deltaY = event.getY() - dragAnchor.y;
            offsetX += deltaX;
            offsetY += deltaY;
            dragAnchor = event.getPoint();
            clampOffsets();
            repaint();
        }

        private void clampOffsets() {
            double halfOverflowX = Math.max(0.0, getScaledImageWidth() - getWidth()) / 2.0;
            double halfOverflowY = Math.max(0.0, getScaledImageHeight() - getHeight()) / 2.0;
            offsetX = clamp(offsetX, -halfOverflowX, halfOverflowX);
            offsetY = clamp(offsetY, -halfOverflowY, halfOverflowY);
        }

        private double getScaledImageWidth() {
            return image.getWidth() * getCurrentScale();
        }

        private double getScaledImageHeight() {
            return image.getHeight() * getCurrentScale();
        }

        private double getCurrentScale() {
            if (getWidth() <= 0 || getHeight() <= 0 || image == null) {
                return 1.0;
            }
            double widthRatio = getWidth() / (double) image.getWidth();
            double heightRatio = getHeight() / (double) image.getHeight();
            return Math.min(widthRatio, heightRatio) * zoomFactor;
        }

        private static double clamp(double value, double min, double max) {
            return Math.max(min, Math.min(max, value));
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

            double scale = getCurrentScale();

            int scaledWidth = Math.max(1, (int) Math.round(image.getWidth() * scale));
            int scaledHeight = Math.max(1, (int) Math.round(image.getHeight() * scale));
            clampOffsets();
            int x = (int) Math.round((getWidth() - scaledWidth) / 2.0 + offsetX);
            int y = (int) Math.round((getHeight() - scaledHeight) / 2.0 + offsetY);

            graphics2D.drawImage(image, x, y, scaledWidth, scaledHeight, null);
            graphics2D.dispose();
        }
    }
}