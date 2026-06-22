package com.hhu.ui.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import javax.swing.JButton;
import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import com.hhu.ui.panels.NavigationPanel;
import com.hhu.ui.panels.codepanel.CodePanelScroller;
import com.hhu.ui.panels.drawsteppanel.DrawStepPanel;

public class NavigationPanelTest {
    private DrawCallHandler drawCallHandler;
    private DrawStepPanel drawStepPanel;
    private CodePanelScroller codePanelScroller;

    private NavigationPanel navigationPanel;

    @Before
    public void setUp() {
        drawCallHandler = mock(DrawCallHandler.class);
        drawStepPanel = mock(DrawStepPanel.class);
        codePanelScroller = mock(CodePanelScroller.class);

        navigationPanel = new NavigationPanel(
                drawCallHandler,
                drawStepPanel,
                codePanelScroller);
    }

    private boolean containsButton(JPanel panel, String buttonText) {
        return java.util.Arrays.stream(panel.getComponents())
                .filter(component -> component instanceof JButton)
                .map(component -> (JButton) component)
                .anyMatch(button -> buttonText.equals(button.getText()));
    }

    @Test
    public void createshouldContain4Buttons() {
        JPanel centerPanel = new JPanel();

        JPanel result = navigationPanel.create(centerPanel);

        //compile, next & prev
        assertEquals(4, result.getComponentCount());
    }

    @Test
    public void createshouldContainCompileButton() {
        JPanel centerPanel = new JPanel();

        JPanel result = navigationPanel.create(centerPanel);

        assertTrue(containsButton(result, "Compile"));
    }

    @Test
    public void createshouldContainPreviousButton() {
        JPanel centerPanel = new JPanel();

        JPanel result = navigationPanel.create(centerPanel);

        assertTrue(containsButton(result, "Previous"));
    }

    @Test
    public void createshouldContainNextButton() {
        JPanel centerPanel = new JPanel();

        JPanel result = navigationPanel.create(centerPanel);

        assertTrue(containsButton(result, "Next"));
    }

    @Test
    public void testButtonPrevDisabled() {
        JButton prev = new JButton();
        JButton next = new JButton();

        when(drawCallHandler.hasPreviousStep()).thenReturn(false);

        NavigationPanel.updateButtons(prev, next, drawCallHandler);

        assertFalse(prev.isEnabled());
    }

    @Test
    public void testButtonPrevEnabled() {
        JButton prev = new JButton();
        JButton next = new JButton();

        when(drawCallHandler.hasPreviousStep()).thenReturn(true);

        NavigationPanel.updateButtons(prev, next, drawCallHandler);

        assertTrue(prev.isEnabled());
    }

    @Test
    public void testButtonNextDisabled() {
        JButton prev = new JButton();
        JButton next = new JButton();

        when(drawCallHandler.hasNextStep()).thenReturn(false);

        NavigationPanel.updateButtons(prev, next, drawCallHandler);

        assertFalse(next.isEnabled());
    }

    @Test
    public void testButtonNextEnabled() {
        JButton prev = new JButton();
        JButton next = new JButton();

        when(drawCallHandler.hasNextStep()).thenReturn(true);

        NavigationPanel.updateButtons(prev, next, drawCallHandler);

        assertTrue(next.isEnabled());
    }

    
}
