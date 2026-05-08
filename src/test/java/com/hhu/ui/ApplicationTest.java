package com.hhu.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.Ignore;
import org.junit.Test;

import com.hhu.ui.application.Application;
import com.hhu.util.DrawCalls;
import com.hhu.util.DrawStep;

public class ApplicationTest {

    @Test
    public void testButtonPrevDisabled() {
        JButton prev = new JButton();
        JButton next = new JButton();

        DrawCalls drawCalls = mock(DrawCalls.class);

        when(drawCalls.hasPrevStep()).thenReturn(false);

        Application.updateButtons(prev, next);

        assertFalse(prev.isEnabled());
    }

    @Test
    public void testButtonPrevEnabled() {
        JButton prev = new JButton();
        JButton next = new JButton();

        DrawCalls drawCalls = mock(DrawCalls.class);

        when(drawCalls.hasPrevStep()).thenReturn(true);

        Application.updateButtons(prev, next);

        assertTrue(prev.isEnabled());
    }

    @Test
    public void testButtonNextDisabled() {
        JButton prev = new JButton();
        JButton next = new JButton();

        DrawCalls drawCalls = mock(DrawCalls.class);

        when(drawCalls.hasNextStep()).thenReturn(false);

        Application.updateButtons(prev, next);

        assertFalse(next.isEnabled());
    }

    @Test
    public void testButtonNextEnabled() {
        JButton prev = new JButton();
        JButton next = new JButton();

        DrawCalls drawCalls = mock(DrawCalls.class);

        when(drawCalls.hasNextStep()).thenReturn(true);

        Application.updateButtons(prev, next);

        assertTrue(next.isEnabled());
    }
}
