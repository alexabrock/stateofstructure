package com.hhu.ui.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.hhu.ui.Colors;

/** Creates the very outer shell of the Application. */
public class ApplicationFrame {

    private static final String TITLE = "State to Structure";

    public JFrame create(JPanel centerPanel, JPanel buttonPanel) {
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Colors.APPLICATION_BACKGROUND);
        frame.setLayout(new BorderLayout());
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.85);
        int height = (int) (screenSize.height * 0.85);

        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);

        return frame;
    }

}