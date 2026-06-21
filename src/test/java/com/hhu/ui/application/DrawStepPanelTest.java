package com.hhu.ui.application;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.Test;

import com.hhu.ui.panels.codepanel.CodePanelScroller;
import com.hhu.ui.panels.drawsteppanel.DrawStepPanel;
import com.hhu.util.DrawStep;

public class DrawStepPanelTest {

    
    @Test
    public void testCreateCenterPanelReturnsAJPanel() {
        CodePanelScroller codePanelScroller = mock(CodePanelScroller.class);
        DrawStepPanel drawStepPanel = new DrawStepPanel(codePanelScroller);
        DrawStep drawStep = mock(DrawStep.class);
        
        when(drawStep.codePanel()).thenReturn(new JPanel());
        when(drawStep.memory()).thenReturn(new JPanel());
        when(drawStep.datastructure()).thenReturn(new JPanel());
        when(drawStep.name()).thenReturn(new JLabel("test"));

        JPanel jPanel = drawStepPanel.createCenterPanel(drawStep);

        assertNotNull(jPanel);
        assertTrue(jPanel instanceof JPanel);
    }
}
