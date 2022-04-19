package main.java.presentation.view.finalstatus;

import main.java.presentation.view.ProductOrders;
import main.java.presentation.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Failure {

    public JFrame frame;

    public Failure(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Failure");

        frame.setSize(500, 220);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat()));
        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Failure! There are not enough products in stock! :(");
        JButton buttTryAgain = new JButton("Introduce new quantity");
        JButton buttBack = new JButton("Back to start");
        panel.add(label);
        panel.add(buttTryAgain);
        panel.add(buttBack);

        buttTryAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                ProductOrders view = new ProductOrders();
                view.frame.setVisible(true);
            }
        });

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
