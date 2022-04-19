package main.java.dao;

import main.java.connection.ConnectionFactory;
import main.java.model.Clients;
import main.java.presentation.view.ClientOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientsDAO extends AbstractDAO<Clients>{

    protected static final Logger LOGGER = Logger.getLogger(ClientsDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO clients (name,address,age,email)"
            + " VALUES (?,?,?,?)";

    private static final String deleteStatementString = "DELETE FROM clients where name = ?";

    private static final String findStatementString = "SELECT * FROM clients where name = ?";

    private static final String selectStatementString = "SELECT name FROM clients";

    private static final String updateStatementString = "UPDATE clients SET address = ?, age = ?, email = ? WHERE name = ?";

    public static Clients findByName(String clientName) {
        Clients toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setString(1, clientName);
            rs = findStatement.executeQuery();
            rs.next();

            int id = rs.getInt("id");
            String name = rs.getString("name");
            String address = rs.getString("address");
            int age = rs.getInt("age");
            String email = rs.getString("email");

            toReturn = new Clients(id, name, address, age, email);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientsDAO:findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    public static String insert(Clients client) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        String name = null;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, client.getName());
            insertStatement.setString(2, client.getAddress());
            insertStatement.setInt(3, client.getAge());
            insertStatement.setString(4, client.getEmail());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientsDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return name;
    }

    public static String delete(Clients client) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement deleteStatement = null;
        String name = null;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setString(1, client.getName());
            deleteStatement.executeUpdate();

            ResultSet rs = deleteStatement.getGeneratedKeys();
            if (rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientsDAO:delete " + e.getMessage());
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
            LOGGER.log(Level.WARNING,"ClientsDAO:selectColumnByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    public static void editClient(Clients client){

        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        String name = null;
        try {
            insertStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, client.getAddress());
            insertStatement.setInt(2, client.getAge());
            insertStatement.setString(3, client.getEmail());
            insertStatement.setString(4, client.getName());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientsDAO:edit " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }

    }

    public static void view(){

        ClientsDAO client = new ClientsDAO();

        try {
            JTable table = client.createTable(client.ViewAll());

            JFrame frame = new JFrame();
            frame.setTitle("View all clients");
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
                    ClientOperations back = new ClientOperations();
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
