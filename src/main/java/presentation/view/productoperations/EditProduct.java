package main.java.presentation.view.productoperations;

import main.java.dao.ClientsDAO;
import main.java.dao.ProductsDAO;
import main.java.model.Clients;
import main.java.model.Products;
import main.java.presentation.view.ProductOperations;
import main.java.presentation.view.finalstatus.Success;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class EditProduct {

    public JFrame frame;

    public EditProduct(){

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Edit product");

        frame.setSize(500,220);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat()));
        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Select product name:");
        panel.add(label);

        JComboBox combo = new JComboBox();
        ArrayList<String> list = ProductsDAO.selectColumnByName();

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

                JLabel l1 = new JLabel("New stock: ");
                JTextField tf1 = new JTextField(30);
                JLabel l2 = new JLabel("New price: ");
                JTextField tf2 = new JTextField(30);
                JButton buttEdit = new JButton("Click here to edit");

                panel.add(l1);
                panel.add(tf1);
                panel.add(l2);
                panel.add(tf2);
                panel.add(buttEdit);

                frame.setContentPane(panel);
                System.out.println((String) combo.getSelectedItem());
                buttEdit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ProductsDAO.editProduct(new Products((String) combo.getSelectedItem(), Integer.parseInt(tf1.getText()), Float.parseFloat(tf2.getText())));

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
                ProductOperations back = new ProductOperations();
                back.frame.setVisible(true);
            }
        });


        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}
