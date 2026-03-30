package com.hhu.views.panelBuilder;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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

    //Should be moved into its own class cause SRP?
    public static JLabel createMethodNameLabel(String methodName) {
            JLabel label = new JLabel(methodName, SwingConstants.CENTER);
            label.setFont(label.getFont().deriveFont(20f));
            return label;
    }
}