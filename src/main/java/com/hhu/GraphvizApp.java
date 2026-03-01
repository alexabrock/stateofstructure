package com.hhu;

import java.awt.GraphicsEnvironment;
import java.util.LinkedList;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;

import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

import com.hhu.views.application.Application;

//Frame erstellen, der drei Bereiche hat: Links ein Bereich mit formatiertem Java-Code, in der Mitte eine Graphviz-Visualisierung eines einfachen gerichteten Graphen (Graph A) und rechts eine weitere Graphviz-Visualisierung eines anderen gerichteten Graphen (Graph B). 

public class GraphvizApp {
    public static void main(String[] args) {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Headless environment detected - Swing viewer is not available.");
            return;
        }

        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        
        String code = """
                public class Demo {
                    public static void main(String[] args) {
                        LinkedList<Integer> list = new LinkedList<>();
                        list.add(1);
                        list.add(2);
                        list.add(3);
                        String dot = new LJV().drawGraph(list);
                        System.out.println(dot);
                    }
                }""";

        Application.startListApplication(code, list);
    }






    private static Graph buildGraphA() {
        return graph("graphA").directed()
                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT))
                .nodeAttr().with(Shape.RECTANGLE)
                .with(
                        node("Start").with(Color.GREEN3),
                        node("Parse"),
                        node("Validate"),
                        node("Done").with(Color.BLUE))
                .with(
                        node("Start").link(node("Parse")),
                        node("Parse").link(node("Validate")),
                        node("Validate").link(node("Done")));
    }


}
