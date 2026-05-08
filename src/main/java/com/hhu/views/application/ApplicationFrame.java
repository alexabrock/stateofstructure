package com.hhu.views.application;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/* Creates the very outer shell of the Application.*/
class ApplicationFrame {

    private static final String TITLE = "State to Structure";
    private static final int WIDTH = 2500;
    private static final int HEIGHT = 1200;

    JFrame create(JPanel centerPanel, JPanel buttonPanel) {
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        return frame;
    }
    
}