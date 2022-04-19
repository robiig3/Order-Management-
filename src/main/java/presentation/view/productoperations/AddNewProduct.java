package main.java.presentation.view.productoperations;

import main.java.dao.ProductsDAO;
import main.java.model.Products;
import main.java.presentation.view.ProductOperations;
import main.java.presentation.view.finalstatus.Success;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AddNewProduct {

    public JFrame frame;

    public AddNewProduct(){

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Add new product");

        frame.setSize(500,220);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat()));
        panel.setLayout(new FlowLayout());

        JLabel l1 = new JLabel("Introduce name");
        JTextField tf1 = new JTextField(30);
        JLabel l2 = new JLabel("Introduce stock");
        JTextField tf2 = new JTextField(30);
        JLabel l3 = new JLabel("Introduce price");
        JTextField tf3 = new JTextField(30);

        panel.add(l1);
        panel.add(tf1);
        panel.add(l2);
        panel.add(tf2);
        panel.add(l3);
        panel.add(tf3);

        JButton buttAdd = new JButton("Add");
        panel.add(buttAdd);

        JButton buttback = new JButton("Back");
        panel.add(buttback);

        buttAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);

                String name = tf1.getText();
                int stock = Integer.parseInt(tf2.getText());
                float price = Float.parseFloat(tf3.getText());

                ProductsDAO.insert(new Products(name, stock, price));

                Success succ = new Success();
                succ.frame.setVisible(true);
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
