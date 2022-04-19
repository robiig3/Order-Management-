package main.java.presentation.view.clientoperations;

import com.mysql.cj.xdevapi.Client;
import main.java.dao.ClientsDAO;
import main.java.model.Clients;
import main.java.presentation.view.ClientOperations;
import main.java.presentation.view.finalstatus.Success;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class DeleteClient {

    public JFrame frame;

    public DeleteClient(){

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Delete client");

        frame.setSize(500,220);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat()));
        panel.setLayout(new FlowLayout());

        JLabel l1 = new JLabel("Introduce name");
        JTextField tf1 = new JTextField(30);
        panel.add(l1);
        panel.add(tf1);

        JButton buttDelete = new JButton("Delete");
        panel.add(buttDelete);

        JButton buttback = new JButton("Back");
        panel.add(buttback);

        buttDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);

                String name = tf1.getText();

                ClientsDAO.delete(new Clients(name, "", 0, ""));

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
