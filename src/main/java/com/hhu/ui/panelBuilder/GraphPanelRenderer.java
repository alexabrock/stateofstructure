package com.hhu.ui.panelBuilder;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.StringReader;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.StringReader;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javax.swing.SwingUtilities;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.gvt.GVTTreeRendererAdapter;
import org.apache.batik.swing.gvt.GVTTreeRendererEvent;
import org.apache.batik.swing.svg.GVTTreeBuilderAdapter;
import org.apache.batik.swing.svg.GVTTreeBuilderEvent;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;

import com.hhu.ui.Colors;
import com.hhu.util.ThemeStyler;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.svg.GVTTreeBuilderAdapter;
import org.apache.batik.swing.svg.GVTTreeBuilderEvent;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;

import com.hhu.util.ThemeStyler;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;

/**
 * Renders Graphviz DOT as a zoomable and pannable SVG panel.
 */
class GraphPanelRenderer {

    private GraphPanelRenderer() {
    }

    /**
     * * Transforms a DOT graph into a zoomable and pannable JPanel.
     */
    static JPanel create(String title, String dot) {
        SvgGraphCanvas canvas = new SvgGraphCanvas();
        canvas.load(Graphviz.fromString(dot).render(Format.SVG).toString());

        JPanel imagePanel = new JPanel(new BorderLayout());
        ThemeStyler.styleApplicationBackground(imagePanel);
        imagePanel.add(canvas, BorderLayout.CENTER);

        JLabel headline = new JLabel(title, SwingConstants.CENTER);
        ThemeStyler.styleHeadline(headline);

        JPanel panel = new JPanel(new BorderLayout(0, 8));
        ThemeStyler.stylePanel(panel);

        panel.add(imagePanel, BorderLayout.CENTER);
        panel.add(headline, BorderLayout.NORTH);

        return panel;
    }

    /**
     * * Batik can only fit the SVG after the GVT tree exists and after Swing has
     * assigned a component size. This wrapper keeps the original graph bounds and
     * recomputes the view transform whenever one of those values changes.
     */
    private static final class SvgGraphCanvas extends JSVGCanvas {
        private static final double MIN_ZOOM = 0.2;
        private static final double MAX_ZOOM = 5.0;
        private static final double WHEEL_ZOOM_BASE = 1.1;
        private static final double FIT_PADDING = 24.0;

        private double zoomFactor = 1.0;
        private double offsetX = 0.0;
        private double offsetY = 0.0;
        private Point dragAnchor;

        private SvgGraphCanvas() {
            setDocumentState(JSVGCanvas.ALWAYS_STATIC);
            setRecenterOnResize(false);
            setDisableInteractions(true);
            setOpaque(false);

            setPreferredSize(new Dimension(1, 1));

            addGVTTreeBuilderListener(new GVTTreeBuilderAdapter() {
                @Override
                public void gvtBuildCompleted(GVTTreeBuilderEvent event) {
                    SwingUtilities.invokeLater(() -> {
                        resetView();
                        applyViewTransform();
                    });
                }
            });
            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent event) {
                    applyViewTransform();
                }
            });
            installMouseInteraction();
        }

        private void load(String svg) {
            try {
                SVGDocument document = new SAXSVGDocumentFactory(XMLResourceDescriptor.getXMLParserClassName())
                        .createSVGDocument(null, new StringReader(svg));
                setSVGDocument(document);
            } catch (Exception ex) {
                throw new IllegalStateException("Could not render Graphviz SVG", ex);
            }
        }

        private void installMouseInteraction() {
            addMouseWheelListener(this::handleMouseWheelZoom);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent event) {
                    if (event.getClickCount() == 2) {

                        resetView();
                        applyViewTransform();
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

            Rectangle2D graphBounds = getGraphBounds();
            if (graphBounds == null) {
                return;
            }
            double oldScale = getTotalScale(graphBounds);
            double newZoomFactor = clamp(zoomFactor * Math.pow(WHEEL_ZOOM_BASE, -event.getPreciseWheelRotation()),
                    MIN_ZOOM, MAX_ZOOM);
            if (Double.compare(newZoomFactor, zoomFactor) == 0) {
                return;
            }

            double graphX = toGraphX(event.getX(), graphBounds, oldScale);
            double graphY = toGraphY(event.getY(), graphBounds, oldScale);

            zoomFactor = newZoomFactor;
            double newScale = getTotalScale(graphBounds);
            offsetX = event.getX() - getCenteredX(graphBounds, newScale)
                    - ((graphX - graphBounds.getX()) * newScale);
            offsetY = event.getY() - getCenteredY(graphBounds, newScale)
                    - ((graphY - graphBounds.getY()) * newScale);

            applyViewTransform();
        }

        private void handleMouseDragged(MouseEvent event) {
            if (dragAnchor == null) {
                return;
            }

            offsetX += event.getX() - dragAnchor.x;
            offsetY += event.getY() - dragAnchor.y;
            dragAnchor = event.getPoint();

            applyViewTransform();
        }

        private void resetView() {
            zoomFactor = 1.0;
            offsetX = 0.0;
            offsetY = 0.0;
        }

        private void applyViewTransform() {
            Rectangle2D graphBounds = getGraphBounds();
            if (graphBounds == null || getWidth() <= 0 || getHeight() <= 0) {
                return;
            }

            double scale = getTotalScale(graphBounds);
            AffineTransform transform = new AffineTransform();
            transform.translate(getCenteredX(graphBounds, scale) + offsetX, getCenteredY(graphBounds, scale) + offsetY);
            transform.scale(scale, scale);
            transform.translate(-graphBounds.getX(), -graphBounds.getY());
            setRenderingTransform(transform);
        }

        private Rectangle2D getGraphBounds() {
            if (getGraphicsNode() == null || getGraphicsNode().getBounds() == null) {
                return null;
            }

            return getGraphicsNode().getBounds();
        }

        private double toGraphX(double canvasX, Rectangle2D graphBounds, double scale) {
            return ((canvasX - getCenteredX(graphBounds, scale) - offsetX) / scale) + graphBounds.getX();
        }

        private double toGraphY(double canvasY, Rectangle2D graphBounds, double scale) {
            return ((canvasY - getCenteredY(graphBounds, scale) - offsetY) / scale) + graphBounds.getY();
        }

        private double getTotalScale(Rectangle2D graphBounds) {
            return getFitScale(graphBounds) * zoomFactor;
        }

        private double getFitScale(Rectangle2D graphBounds) {
            double availableWidth = Math.max(1.0, getWidth() - FIT_PADDING * 2.0);
            double availableHeight = Math.max(1.0, getHeight() - FIT_PADDING * 2.0);
            double widthScale = availableWidth / graphBounds.getWidth();
            double heightScale = availableHeight / graphBounds.getHeight();
            return Math.min(widthScale, heightScale);
        }

        private double getCenteredX(Rectangle2D graphBounds, double scale) {
            return (getWidth() - graphBounds.getWidth() * scale) / 2.0;
        }

        private double getCenteredY(Rectangle2D graphBounds, double scale) {
            return (getHeight() - graphBounds.getHeight() * scale) / 2.0;
        }

        private static double clamp(double value, double min, double max) {
            return Math.max(min, Math.min(max, value));
        }
    }
}
