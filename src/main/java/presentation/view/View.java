package main.java.presentation.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class View {

    public JFrame frame;

    public View(){

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Order Management");

        frame.setSize(500,220);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat()));

        JButton b1 = new JButton("Client Operations");
        JButton b2 = new JButton("Product Operations");
        JButton b3 = new JButton("Product Orders");
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);

        panel.setLayout(new GridBagLayout());

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                ClientOperations cOperations = new ClientOperations();
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                ProductOperations pOperations = new ProductOperations();
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                ProductOrders pOrders = new ProductOrders();
            }
        });

        frame.setContentPane(panel);
        frame.setVisible(true);

    }

}