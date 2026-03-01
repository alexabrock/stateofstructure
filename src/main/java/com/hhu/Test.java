package com.hhu;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import org.atpfivt.ljv.LJV;

import guru.nidi.graphviz.attribute.Attributes;
import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Font;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;

import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.to;

//Diese Klasse ist ein Test, um die Ausgabe von LJV.drawGraph() (ist eine DOT) mit Graphviz zu rendern. Das funktioniert.
//Ist auch der einzige Weg, den ich gefunden habe, um die DOT-Ausgabe von LJV zu visualisieren. GraphStream kann das nicht rendern, zumindest nicht direkt. Es gibt zwar Möglichkeiten, DOT-Dateien mit GraphStream zu importieren, aber das ist kompliziert und funktioniert nicht immer zuverlässig. Daher habe ich mich entschieden, Graphviz zu verwenden, um die DOT-Ausgabe von LJV zu visualisieren.


public class Test {
    public static void main(String[] args) throws IOException {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        String graphVizOutput = new LJV().drawGraph(list);
        System.out.println(graphVizOutput);

        Graph g = graph("example1").directed()
                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT))
                .nodeAttr().with(Font.name("SansSerif"))
                .linkAttr().with("class", "link-class")
                .with(
                        node("a").with(Color.RED).link(node("b")),
                        node("b").link(
                                to(node("c")).with(Attributes.attr("weight", 5), Style.DASHED)));

        File out = new File("example/ex1.png");
        out.getParentFile().mkdirs();
        Graphviz.fromGraph(g).height(100).render(Format.PNG).toFile(out);
    }
}
