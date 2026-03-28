package com.hhu.views.panelBuilder;

import javax.swing.JPanel;

public class PanelBuilder {

    public static JPanel createDatastructurePanel(Object collection) {
        return DataStructurePanelBuilder.create(collection);
    }

    public static JPanel createMemoryPanel(Object collection) {
        return MemoryPanelBuilder.create(collection);
    }

    public static JPanel createCodePanel(String code) {
        return CodePanelBuilder.create(code);
    }
}