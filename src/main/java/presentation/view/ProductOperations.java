package main.java.presentation.view;

import main.java.presentation.view.productoperations.AddNewProduct;
import main.java.presentation.view.productoperations.DeleteProduct;
import main.java.presentation.view.productoperations.EditProduct;
import main.java.presentation.view.productoperations.ViewAllProducts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ProductOperations {

    public JFrame frame;

    public ProductOperations(){

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Product Operations");

        frame.setSize(500,220);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat()));
        panel.setLayout(new FlowLayout());

        JButton b1 = new JButton("Add new product");
        JButton b2 = new JButton("Edit product");
        JButton b3 = new JButton("Delete product");
        JButton b4 = new JButton("View all products");
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
                AddNewProduct anp = new AddNewProduct();
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                EditProduct ep = new EditProduct();
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                DeleteProduct dp = new DeleteProduct();
            }
        });

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                ViewAllProducts vap = new ViewAllProducts();
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
