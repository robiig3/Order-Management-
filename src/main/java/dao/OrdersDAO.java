package main.java.dao;

import main.java.connection.ConnectionFactory;
import main.java.model.Clients;
import main.java.model.Orders;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrdersDAO {

    protected static final Logger LOGGER = Logger.getLogger(ClientsDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO orders (id_client,id_product,quantity,total_price)"
            + " VALUES (?,?,?,?)";

    public static String insert(Orders order) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        String name = null;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, order.getIdClient());
            insertStatement.setInt(2, order.getIdProduct());
            insertStatement.setInt(3, order.getQuantity());
            insertStatement.setFloat(4, order.getTotalPrice());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrdersDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return name;
    }

}
