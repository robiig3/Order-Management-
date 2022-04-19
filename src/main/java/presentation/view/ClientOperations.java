package main.java.presentation.view;

import main.java.presentation.view.clientoperations.AddNewClient;
import main.java.presentation.view.clientoperations.DeleteClient;
import main.java.presentation.view.clientoperations.EditClient;
import main.java.presentation.view.clientoperations.ViewAllClients;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ClientOperations {

    public JFrame frame;

    public ClientOperations(){

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Client Operations");

        frame.setSize(500,220);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat()));
        panel.setLayout(new FlowLayout());

        JButton b1 = new JButton("Add new client");
        JButton b2 = new JButton("Edit client");
        JButton b3 = new JButton("Delete client");
        JButton b4 = new JButton("View all clients");
        JButton buttback = new JButton("Back");
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        panel.add(buttback);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                AddNewClient anc = new AddNewClient();
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                EditClient ec = new EditClient();
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                DeleteClient dc = new DeleteClient();
            }
        });

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                ViewAllClients vac = new ViewAllClients();
            }
        });

        buttback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                View back = new View();
                back.frame.setVisible(true);
            }
        });

        frame.setContentPane(panel);
        frame.setVisible(true);

    }

}
