package com.hhu;

import java.awt.GraphicsEnvironment;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

import com.hhu.views.application.Application;

//Frame erstellen, der drei Bereiche hat: Links ein Bereich mit formatiertem Java-Code, in der Mitte eine Graphviz-Visualisierung eines einfachen gerichteten Graphen (Graph A) und rechts eine weitere Graphviz-Visualisierung eines anderen gerichteten Graphen (Graph B). 

public class GraphvizApp {
    public static void main(String[] args) {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Headless environment detected - Swing viewer is not available.");
            return;
        }

        LinkedList<String> list = new LinkedList<>();
        list.add("Hello");
        list.add("Progra");
        list.add("2026");

        Path path = Paths.get("src/main/java/com/hhu/GraphvizApp.java");
        Application.startListApplication(path, list);
    }

}
