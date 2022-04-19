package main.java.presentation.view.clientoperations;

import main.java.dao.ClientsDAO;
import main.java.model.Clients;
import main.java.presentation.view.ClientOperations;
import main.java.presentation.view.View;
import main.java.presentation.view.finalstatus.Success;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AddNewClient {

    public JFrame frame;

    public AddNewClient(){

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Add new client");

        frame.setSize(500,220);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat()));
        panel.setLayout(new FlowLayout());

        JLabel l1 = new JLabel("Introduce name");
        JTextField tf1 = new JTextField(30);
        JLabel l2 = new JLabel("Introduce address");
        JTextField tf2 = new JTextField(30);
        JLabel l3 = new JLabel("Introduce age");
        JTextField tf3 = new JTextField(30);
        JLabel l4 = new JLabel("Introduce email");
        JTextField tf4 = new JTextField(30);

        panel.add(l1);
        panel.add(tf1);
        panel.add(l2);
        panel.add(tf2);
        panel.add(l3);
        panel.add(tf3);
        panel.add(l4);
        panel.add(tf4);

        JButton buttAdd = new JButton("Add");
        panel.add(buttAdd);

        JButton buttback = new JButton("Back");
        panel.add(buttback);

        buttAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);

                String name = tf1.getText();
                String address = tf2.getText();
                int age = Integer.parseInt(tf3.getText());
                String email = tf4.getText();

                ClientsDAO.insert(new Clients(name, address, age, email));

                Success succ = new Success();
                succ.frame.setVisible(true);
            }
        });

        buttback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                ClientOperations back = new ClientOperations();
                back.frame.setVisible(true);
            }
        });

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

}
