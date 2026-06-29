package com.hhu.datastructureview.graphbuilder.impl;

import static org.junit.Assert.assertTrue;
import java.util.Collection;
import java.util.Objects;

import guru.nidi.graphviz.model.MutableNode;

public class LabelChecker {
    // Für den Fall, dass nach dem "label"-Attribut gesucht wird 
    public static void checkContainsLabels(Collection<MutableNode> nodes, String... expectedLabels) {
        for (String label : expectedLabels) {
            boolean found = nodes.stream().anyMatch(
                    // The label is not explicitly set for nodes like head and tail,
                    // but those do not matter in this test.
                    n -> Objects.toString(n.get("label"), "").equals(label));
            assertTrue("Label '" + label + "' nicht im Graph gefunden.", found);
        }
    }
    
    // Für den Fall, dass direkt der Node-Name geprüft wird (Klasse benutzt Node builder nicht, weil sie nur unique Nodes beinhalten darf (LinkedHashSets))
    public static void checkContainsNames(Collection<MutableNode> nodes, String... expectedNames) {
        for (String name : expectedNames) {
            boolean found = nodes.stream().anyMatch(n -> n.name() != null && n.name().toString().equals(name));
            assertTrue("Node mit Name '" + name + "' nicht im Graph gefunden.", found);
        }
    }
}