package com.hhu.views.panelBuilder;

import static guru.nidi.graphviz.attribute.Attributes.attr;

import guru.nidi.graphviz.model.Graph;

final class GraphvizDarkModeStyler {
        //datastructure colors
        private static final String BG_COLORD = "#3c3f41";
        private static final String FG_COLORD = "#ffffff";
        private static final String EDGE_COLORD = "#ffffff";
        private static final String NODE_FILL_COLORD = "#004666";
        private static final String NODE_BORDER_COLORD = "#00B0FF";

        //memory colors
        private static final String BG_COLORM = "#3c3f41";
        private static final String FG_COLORM = "#ffffff";
        private static final String EDGE_COLORM = "#ffffff";
        private static final String NODE_FILL_COLORM = "#664E00";
        private static final String NODE_BORDER_COLORM = "#FFD54F";

        private GraphvizDarkModeStyler() {
        }

        static Graph applyDataStructure(Graph graph) {
                return graph
                                .graphAttr().with(
                                                attr("bgcolor", BG_COLORD),
                                                attr("fontcolor", FG_COLORD))
                                .nodeAttr().with(
                                                attr("fontcolor", FG_COLORD),
                                                attr("color", NODE_BORDER_COLORD),
                                                attr("style", "filled"),
                                                attr("fillcolor", NODE_FILL_COLORD))
                                .linkAttr().with(
                                                attr("color", EDGE_COLORD),
                                                attr("fontcolor", FG_COLORD));
        }

        static String applyMemory(String dot) {
                String themedAttributes = String.format(
                                "%n  graph [bgcolor=\"%s\", fontcolor=\"%s\"];%n"
                                                + "  node [fontcolor=\"%s\", color=\"%s\", style=\"filled\", fillcolor=\"%s\"];%n"
                                                + "  edge [color=\"%s\", fontcolor=\"%s\"];%n",
                                BG_COLORM,
                                FG_COLORM,
                                FG_COLORM,
                                NODE_BORDER_COLORM,
                                NODE_FILL_COLORM,
                                EDGE_COLORM,
                                FG_COLORM);

                int firstBrace = dot.indexOf('{');
                if (firstBrace < 0) {
                        return dot;
                }
                return dot.substring(0, firstBrace + 1) + themedAttributes + dot.substring(firstBrace + 1);
        }
}