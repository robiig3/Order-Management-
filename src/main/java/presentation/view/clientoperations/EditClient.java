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
import java.util.ArrayList;
import java.util.Random;

public class EditClient {

    public JFrame frame;

    public EditClient(){

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Edit client");

        frame.setSize(500,220);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat()));
        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Select client name:");
        panel.add(label);

        JComboBox combo = new JComboBox();
        ArrayList<String> list = ClientsDAO.selectColumnByName();

        for (String s:
             list) {
            combo.addItem(s);
        }

        panel.add(combo);

        JButton buttOk = new JButton("Ok");
        JButton buttback = new JButton("Back");
        panel.add(buttOk);
        panel.add(buttback);

        buttOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel l1 = new JLabel("New address: ");
                JTextField tf1 = new JTextField(30);
                JLabel l2 = new JLabel("New age: ");
                JTextField tf2 = new JTextField(30);
                JLabel l3 = new JLabel("New email: ");
                JTextField tf3 = new JTextField(30);
                JButton buttEdit = new JButton("Click here to edit");

                panel.add(l1);
                panel.add(tf1);
                panel.add(l2);
                panel.add(tf2);
                panel.add(l3);
                panel.add(tf3);
                panel.add(buttEdit);

                frame.setContentPane(panel);
                System.out.println((String) combo.getSelectedItem());
                buttEdit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ClientsDAO.editClient(new Clients((String) combo.getSelectedItem(), tf1.getText(), Integer.parseInt(tf2.getText()), tf3.getText()));

                        frame.setVisible(false);
                        Success succ = new Success();
                        succ.frame.setVisible(true);
                    }
                });

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
