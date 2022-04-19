package main.java.presentation.view.productoperations;

import main.java.dao.ClientsDAO;
import main.java.dao.ProductsDAO;
import main.java.presentation.view.ProductOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ViewAllProducts {

//    public JFrame frame;

    public ViewAllProducts(){

        ProductsDAO.view();

//        frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setTitle("View all products");
//
//        frame.setSize(500,220);
//        frame.setResizable(false);
//        frame.setLocationRelativeTo(null);
//
//        JPanel panel = new JPanel();
//        panel.setBackground(new Color(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat()));
//        panel.setLayout(new FlowLayout());
//
//        JButton buttback = new JButton("Back");
//        panel.add(buttback);
//
//        buttback.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                frame.setVisible(false);
//                ProductOperations back = new ProductOperations();
//                back.frame.setVisible(true);
//            }
//        });
//
//
//        frame.setContentPane(panel);
//        frame.setVisible(true);
    }
}
