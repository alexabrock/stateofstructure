package com.hhu;
//Testsklasse für die Graphvisualisierung mit GraphStream und Lightweight Java Visualizer (LJV)

//Diese Klasse erstellt eine einfache GUI mit drei Bereichen: zwei für die GraphStream-Visualisierungen und einen für die LJV-DOT-Graphenvisualisierung.
//Für die DOT-Visualisierung wird versucht, den output von LJV.drawGraph() (ist eine DOT) mit Graphstream zu rendern
//Das funktioniert nicht.

import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import org.atpfivt.ljv.LJV;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.Viewer.ThreadingModel;


public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        String graphVizOutput = new LJV().drawGraph(list);
        System.out.println(list);
        System.out.println(graphVizOutput);

        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Headless environment erkannt – UI wird nicht gestartet.");
            return;
        }

        SwingUtilities.invokeLater(() -> {
            System.setProperty("org.graphstream.ui", "swing");

            Graph leftGraph = new SingleGraph("Left Graph");
            leftGraph.addNode("A");
            leftGraph.addNode("B");
            leftGraph.addNode("C");
            leftGraph.addEdge("AB", "A", "B");
            leftGraph.addEdge("BC", "B", "C");
            leftGraph.addEdge("CA", "C", "A");

            Graph rightGraph = new SingleGraph("Right Graph");
            rightGraph.addNode("1");
            rightGraph.addNode("2");
            rightGraph.addNode("3");
            rightGraph.addNode("4");
            rightGraph.addEdge("12", "1", "2");
            rightGraph.addEdge("23", "2", "3");
            rightGraph.addEdge("34", "3", "4");
            rightGraph.addEdge("41", "4", "1");

            Viewer leftViewer = new SwingViewer(leftGraph, ThreadingModel.GRAPH_IN_GUI_THREAD);
            leftViewer.enableAutoLayout();
            View leftView = leftViewer.addDefaultView(false);
            leftView.getCamera().setAutoFitView(true);

            Viewer rightViewer = new SwingViewer(rightGraph, ThreadingModel.GRAPH_IN_GUI_THREAD);
            rightViewer.enableAutoLayout();
            View rightView = rightViewer.addDefaultView(false);
            rightView.getCamera().setAutoFitView(true);

            JScrollPane dotGraphPane = createDotGraphPane(graphVizOutput);

            JPanel graphPanel = new JPanel(new GridLayout(1, 3));
            graphPanel.add((Component) leftView);
            graphPanel.add((Component) rightView);
            graphPanel.add(dotGraphPane);

            JFrame frame = new JFrame("My GUI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1800, 600);
            frame.setContentPane(graphPanel);
            frame.setVisible(true);
        });
    }

    private static JScrollPane createDotGraphPane(String dotSource) {
        JLabel label = new JLabel(
                "DOT-Graph konnte nicht gerendert werden. Bitte Graphviz installieren oder GRAPHVIZ_DOT/graphviz_dot setzen.",
                SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        String dotExecutable = findDotExecutable();
        if (dotExecutable != null) {
            try {
                Process process = new ProcessBuilder(dotExecutable, "-Tpng").start();
                try (OutputStream processInput = process.getOutputStream()) {
                    processInput.write(dotSource.getBytes(StandardCharsets.UTF_8));
                }

                ByteArrayOutputStream imageData = new ByteArrayOutputStream();
                process.getInputStream().transferTo(imageData);
                String errorOutput = new String(process.getErrorStream().readAllBytes(), StandardCharsets.UTF_8);

                int exitCode = process.waitFor();
                if (exitCode == 0) {
                    BufferedImage rendered = ImageIO.read(new ByteArrayInputStream(imageData.toByteArray()));
                    if (rendered != null) {
                        label = new JLabel(new ImageIcon(rendered));
                        label.setHorizontalAlignment(SwingConstants.CENTER);
                        label.setVerticalAlignment(SwingConstants.CENTER);
                    }
                } else {
                    label.setText("DOT-Renderfehler: " + errorOutput);
                }
            } catch (IOException | InterruptedException e) {
                if (e instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
                label.setText("DOT-Renderfehler: " + e.getMessage());
            }
        }

        JScrollPane dotScrollPane = new JScrollPane(label);
        dotScrollPane.setBorder(new TitledBorder("Graphviz (DOT)"));
        return dotScrollPane;
    }

    private static String findDotExecutable() {
        String envDotUpper = System.getenv("GRAPHVIZ_DOT");
        String envDotLower = System.getenv("graphviz_dot");

        String fromEnv = firstRunnable(envDotUpper, envDotLower);
        if (fromEnv != null) {
            return fromEnv;
        }

        String graphvizHome = System.getenv("GRAPHVIZ_HOME");
        if (graphvizHome != null && !graphvizHome.isBlank()) {
            String fromHome = firstRunnable(
                    Path.of(graphvizHome, "bin", "dot.exe").toString(),
                    Path.of(graphvizHome, "bin", "dot").toString());
            if (fromHome != null) {
                return fromHome;
            }
        }

        String discovered = discoverDotFromSystemPath();
        if (discovered != null) {
            return discovered;
        }

        return firstRunnable(
                "dot",
                "dot.exe",
                "C:/Program Files/Graphviz/bin/dot.exe",
                "C:/Program Files (x86)/Graphviz/bin/dot.exe",
                "C:/Program Files/Graphviz*/bin/dot.exe");
    }

    private static String discoverDotFromSystemPath() {
        String os = System.getProperty("os.name", "").toLowerCase();
        String[] command = os.contains("win") ? new String[] { "where", "dot" } : new String[] { "which", "dot" };

        try {
            Process process = new ProcessBuilder(command).start();
            byte[] stdout = process.getInputStream().readAllBytes();
            process.waitFor();
            String output = new String(stdout, StandardCharsets.UTF_8).trim();
            if (!output.isEmpty()) {
                String firstLine = output.split("\\R", 2)[0].trim();
                return isRunnable(firstLine) ? firstLine : null;
            }
        } catch (IOException | InterruptedException e) {
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
        }

        return null;
    }

    private static String firstRunnable(String... candidates) {
        for (String candidate : candidates) {
            if (candidate == null || candidate.isBlank()) {
                continue;
            }

            String cleaned = candidate.trim();
            if ((cleaned.startsWith("\"") && cleaned.endsWith("\""))
                    || (cleaned.startsWith("'") && cleaned.endsWith("'"))) {
                cleaned = cleaned.substring(1, cleaned.length() - 1);
            }

            if (isRunnable(cleaned)) {
                return cleaned;
            }
        }
        return null;
    }

    private static boolean isRunnable(String executable) {
        try {
            Process process = new ProcessBuilder(executable, "-V").start();
            process.getInputStream().readAllBytes();
            process.getErrorStream().readAllBytes();
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (IOException | InterruptedException e) {
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            return false;
        }
    }
}
