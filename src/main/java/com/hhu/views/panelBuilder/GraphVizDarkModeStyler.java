package com.hhu.views.panelBuilder;

import static guru.nidi.graphviz.attribute.Attributes.attr;

import guru.nidi.graphviz.model.Graph;

final class GraphvizDarkModeStyler {

    private static final String BG_COLOR = "#3c3f41";
    private static final String FG_COLOR = "#ffffff";
    private static final String EDGE_COLOR = "#ffffff";
    private static final String NODE_FILL_COLOR = "#2e2e2e";
    private static final String NODE_BORDER_COLOR = "#ffffff";

    private GraphvizDarkModeStyler() {
    }

    static Graph apply(Graph graph) {
        return graph
                .graphAttr().with(
                        attr("bgcolor", BG_COLOR),
                        attr("fontcolor", FG_COLOR))
                .nodeAttr().with(
                        attr("fontcolor", FG_COLOR),
                        attr("color", NODE_BORDER_COLOR),
                        attr("style", "filled"),
                        attr("fillcolor", NODE_FILL_COLOR))
                .linkAttr().with(
                        attr("color", EDGE_COLOR),
                        attr("fontcolor", FG_COLOR));
    }

    static String apply(String dot) {
        String themedAttributes = String.format(
                "%n  graph [bgcolor=\"%s\", fontcolor=\"%s\"];%n"
                        + "  node [fontcolor=\"%s\", color=\"%s\", style=\"filled\", fillcolor=\"%s\"];%n"
                        + "  edge [color=\"%s\", fontcolor=\"%s\"];%n",
                BG_COLOR,
                FG_COLOR,
                FG_COLOR,
                NODE_BORDER_COLOR,
                NODE_FILL_COLOR,
                EDGE_COLOR,
                FG_COLOR);

        int firstBrace = dot.indexOf('{');
        if (firstBrace < 0) {
            return dot;
        }
        return dot.substring(0, firstBrace + 1) + themedAttributes + dot.substring(firstBrace + 1);
    }
}