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

public class DeleteProduct {

    public JFrame frame;

    public DeleteProduct(){

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Delete product");

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

                ProductsDAO.delete(new Products(name, 0, 0));

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
