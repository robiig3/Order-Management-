package main.java.dao;

import main.java.connection.ConnectionFactory;
import main.java.model.Clients;
import main.java.model.Products;
import main.java.presentation.view.ClientOperations;
import main.java.presentation.view.ProductOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductsDAO extends AbstractDAO<Products> {

    protected static final Logger LOGGER = Logger.getLogger(ProductsDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO products (name,stock,price)"
            + " VALUES (?,?,?)";

    private static final String deleteStatementString = "DELETE FROM products where name = ?";

    private static final String findStatementString = "SELECT * FROM products where name = ?";

    private static final String selectStatementString = "SELECT name FROM products";

    private static final String updateStatementString = "UPDATE products SET stock = ?, price = ? WHERE name = ?";

    public static Products findByName(String productName) {
        Products toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setString(1, productName);
            rs = findStatement.executeQuery();
            rs.next();

            int id = rs.getInt("id");
            String name = rs.getString("name");
            int stock = rs.getInt("stock");
            float price = rs.getFloat("price");

            toReturn = new Products(id, name, stock, price);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductsDAO:findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    public static String insert(Products product) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        String name = null;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, product.getName());
            insertStatement.setInt(2, product.getStock());
            insertStatement.setFloat(3, product.getPrice());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductsDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return name;
    }

    public static String delete(Products product) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;
        String name = null;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setString(1, product.getName());
            deleteStatement.executeUpdate();

            ResultSet rs = deleteStatement.getGeneratedKeys();
            if (rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductsDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return name;
    }

    public static ArrayList<String> selectColumnByName() {

        ArrayList<String> toReturn = new ArrayList<String>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(selectStatementString);
            rs = findStatement.executeQuery();

            while (rs.next()) {
                toReturn.add(rs.getString("name"));
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductsDAO:selectColumnByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    public static void editProduct(Products product){

        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        String name = null;
        try {
            insertStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, product.getStock());
            insertStatement.setFloat(2, product.getPrice());
            insertStatement.setString(3, product.getName());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductsDAO:edit " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }

    }

    public static void view(){

        ProductsDAO product = new ProductsDAO();

        try {
            JTable table = product.createTable(product.ViewAll());

            JFrame frame = new JFrame();
            frame.setTitle("View all products");
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.NORTH);
            frame.setSize(900, 500);
            frame.setLocationRelativeTo(null);

            JButton buttback = new JButton("Back");
            frame.add(buttback);

            buttback.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    ProductOperations back = new ProductOperations();
                    back.frame.setVisible(true);
                }
            });

            frame.setVisible(true);

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
