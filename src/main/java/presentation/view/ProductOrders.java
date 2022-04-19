package main.java.presentation.view;

import main.java.dao.ClientsDAO;
import main.java.dao.OrdersDAO;
import main.java.dao.ProductsDAO;
import main.java.model.Clients;
import main.java.model.Orders;
import main.java.model.Products;
import main.java.presentation.WriteToFile;
import main.java.presentation.view.finalstatus.Failure;
import main.java.presentation.view.finalstatus.Success;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ProductOrders {

    public JFrame frame;

    public ProductOrders(){

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Product Orders");

        frame.setSize(500,220);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat()));
        panel.setLayout(new FlowLayout());

        JComboBox comboClients = new JComboBox();
        ArrayList<String> listClients = ClientsDAO.selectColumnByName();

        JComboBox comboProducts = new JComboBox();
        ArrayList<String> listProducts = ProductsDAO.selectColumnByName();

        for (String s:
                listClients) {
            comboClients.addItem(s);
        }
        for (String s:
                listProducts) {
            comboProducts.addItem(s);
        }

        panel.add(comboClients);
        panel.add(comboProducts);

        JLabel label = new JLabel("Insert quantity here  ->  ");
        JTextField tf = new JTextField(5);
        JButton buttProceed = new JButton("Proceed");
        JButton buttback = new JButton("Back");
        panel.add(label);
        panel.add(tf);
        panel.add(buttProceed);
        panel.add(buttback);

        buttProceed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Clients client = ClientsDAO.findByName((String) comboClients.getSelectedItem());
                Products product = ProductsDAO.findByName((String) comboProducts.getSelectedItem());

                int quantity = Integer.parseInt(tf.getText());

                if(product.getStock() < quantity){
                    frame.setVisible(false);
                    Failure fail = new Failure();
                    fail.frame.setVisible(true);
                }else{
                    product.setStock(product.getStock() - Integer.parseInt(tf.getText()));
                    ProductsDAO.editProduct(product);

                    System.out.println("clientid = " + client.getId() + "\nprodusid = " + product.getId());
                    float totalPrice = quantity * product.getPrice();
                    Orders order = new Orders(client.getId(), product.getId(), quantity, totalPrice);

                    OrdersDAO.insert(order);

                    String bill = new String("");
                    bill += client.toString();
                    bill += product.toString();
                    bill += "quantity=" + quantity +"\n";
                    bill += "total price=" + totalPrice;
                    WriteToFile.printBill(bill);

                    frame.setVisible(false);
                    Success succ = new Success();
                    succ.frame.setVisible(true);
                }
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
