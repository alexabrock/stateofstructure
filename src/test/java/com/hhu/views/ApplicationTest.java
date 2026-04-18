package com.hhu.views;

import java.util.LinkedHashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


import com.hhu.util.DrawCalls;
import com.hhu.util.DrawStep;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ApplicationTest {

    @Test
    public void testButtonPrevDisabled() {
        JButton prev = new JButton();
        JButton next = new JButton();

        DrawCalls drawCalls = mock(DrawCalls.class);

        when(drawCalls.hasPrevStep()).thenReturn(false);

        Application.updateButtons(prev, next, drawCalls);

        assertFalse(prev.isEnabled());
    }

    @Test
    public void testButtonPrevEnabled() {
        JButton prev = new JButton();
        JButton next = new JButton();

        DrawCalls drawCalls = mock(DrawCalls.class);

        when(drawCalls.hasPrevStep()).thenReturn(true);

        Application.updateButtons(prev, next, drawCalls);

        assertTrue(prev.isEnabled());
    }
    
    @Test
    public void testButtonNextDisabled() {
        JButton prev = new JButton();
        JButton next = new JButton();

        DrawCalls drawCalls = mock(DrawCalls.class);

        when(drawCalls.hasNextStep()).thenReturn(false);

        Application.updateButtons(prev, next, drawCalls);

        assertFalse(next.isEnabled());
    }

    @Test
    public void testButtonNextEnabled() {
        JButton prev = new JButton();
        JButton next = new JButton();

        DrawCalls drawCalls = mock(DrawCalls.class);

        when(drawCalls.hasNextStep()).thenReturn(true);

        Application.updateButtons(prev, next, drawCalls);

        assertTrue(next.isEnabled());
    }

    @Test
    @Ignore
    public void testReplacePanels() {

        JPanel container = new JPanel(new BorderLayout());

        JPanel oldCode = new JPanel();
        JPanel oldMemory = new JPanel();
        JPanel oldData = new JPanel();
        JLabel oldLabel = new JLabel();

        container.add(oldCode, BorderLayout.WEST);
        container.add(oldMemory, BorderLayout.CENTER);
        container.add(oldData, BorderLayout.EAST);
        container.add(oldLabel, BorderLayout.NORTH);


        DrawStep step = mock(DrawStep.class);

        JPanel newCode = new JPanel();
        JPanel newMemory = new JPanel();
        JPanel newData = new JPanel();
        JLabel newLabel = new JLabel();

        when(step.codPanel()).thenReturn(newCode);
        when(step.memory()).thenReturn(newMemory);
        when(step.datastructure()).thenReturn(newData);
        when(step.methodLabel()).thenReturn(newLabel);


        Application.replacePanels(container, step);


        BorderLayout layout = (BorderLayout) container.getLayout();

        assertEquals(newCode, layout.getLayoutComponent(BorderLayout.WEST));
        assertEquals(newMemory, layout.getLayoutComponent(BorderLayout.CENTER));
        assertEquals(newData, layout.getLayoutComponent(BorderLayout.EAST));
        assertEquals(newLabel, layout.getLayoutComponent(BorderLayout.NORTH));
    }

}
