package com.hhu.views.panelBuilder;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;

public class PanelBuilder {
    public static JPanel createDatastructurePanel(Graph graph) {
        return createGraphPanel("Datastructure Visualization", graph);
    }

    public static JPanel createMemoryPanel(String dot) {
        return createDotGraphPanel("Memory Visualization", dot);
    }

    public static JPanel createCodePanel(String code) {
        JTextArea textArea = new JTextArea(code);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        textArea.setEditable(false);

        JLabel headline = new JLabel("Source Code", SwingConstants.CENTER);
        headline.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBorder(new TitledBorder("Code"));
        panel.add(headline, BorderLayout.NORTH);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        return panel;
    }

    private static JPanel createGraphPanel(String title, Graph graph) {
        BufferedImage image = Graphviz.fromGraph(graph).width(550).render(Format.PNG).toImage();
        return buildImagePanel(title, image);
    }

    private static JPanel createDotGraphPanel(String title, String dot) {
        try {
            BufferedImage image = Graphviz.fromString(dot).width(550).render(Format.PNG).toImage();
            return buildImagePanel(title, image);
        } catch (RuntimeException ex) {
            JLabel error = new JLabel("Could not render LJV DOT: " + ex.getMessage(), SwingConstants.CENTER);
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBorder(new TitledBorder("Graphviz"));
            panel.add(error, BorderLayout.CENTER);
            return panel;
        }
    }

    private static JPanel buildImagePanel(String title, BufferedImage image) {
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel headline = new JLabel(title, SwingConstants.CENTER);
        headline.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBorder(new TitledBorder("Graphviz"));
        panel.add(headline, BorderLayout.NORTH);
        panel.add(new JScrollPane(imageLabel), BorderLayout.CENTER);
        return panel;
    }
}
