package com.hhu.ui.application;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Test;

import com.hhu.ui.panels.ApplicationFrame;

public class ApplicationFrameTest {
    @Test
    public void createReturnsAFrame() {
        JPanel centerPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        ApplicationFrame applicationFrame = new ApplicationFrame();

        JFrame result = applicationFrame.create(centerPanel, buttonPanel);

        assertNotNull(result);
        assertTrue(result instanceof JFrame);

    }
}
