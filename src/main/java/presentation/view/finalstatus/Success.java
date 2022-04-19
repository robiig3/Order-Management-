package main.java.presentation.view.finalstatus;

import main.java.presentation.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Success {

    public JFrame frame;

    public Success() {

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Succes");

        frame.setSize(500, 220);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat()));
        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Successfully completed! :)");
        JButton buttBack = new JButton("Back to start");
        panel.add(label);
        panel.add(buttBack);

        buttBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                View view = new View();
                view.frame.setVisible(true);
            }
        });

        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}
