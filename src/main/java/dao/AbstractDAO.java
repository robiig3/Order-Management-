package main.java.dao;

import main.java.connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AbstractDAO<T>{
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private String createFindQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + "=?");
        return sb.toString();
    }

    private String createAddQuery(T object) throws IllegalArgumentException, IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" VALUES (");
        for(Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if(field.get(object) instanceof Integer) {
                sb.append(field.get(object));
                sb.append(",");
            }
            else {
                sb.append("'");
                sb.append(field.get(object));
                sb.append("',");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(");");
        System.out.println(sb.toString());
        return sb.toString();
    }

    private String createViewAllQuery() throws IllegalArgumentException, IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(type.getSimpleName());
        sb.append(";");
        return sb.toString();
    }

    public List<T> ViewAll() throws IllegalArgumentException, IllegalAccessException {
        Connection bdConnection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createViewAllQuery();
        try {
            bdConnection = ConnectionFactory.getConnection();
            statement = bdConnection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch(SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(bdConnection);
        }
        return null;
    }

    public JTable createTable(List<T> objects) throws IllegalArgumentException, IllegalAccessException {
        ArrayList<String> columnNamesArrayList = new ArrayList<String>();
        for(Field field : objects.get(0).getClass().getDeclaredFields()) {
            field.setAccessible(true);
            columnNamesArrayList.add(field.getName());
        }
        String[] columnNames = new String[columnNamesArrayList.size()];
        columnNames = columnNamesArrayList.toArray(columnNames);
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        Iterator<T> i = objects.iterator();
        while(i.hasNext()) {
            T object = i.next();
            ArrayList<Object> columnDataAsArrayList = new ArrayList<Object>();
            for(Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                columnDataAsArrayList.add(field.get(object));
            }
            Object[] columnDataAsArray = new Object[columnDataAsArrayList.size()];
            columnDataAsArray = columnDataAsArrayList.toArray(columnDataAsArray);
            tableModel.addRow(columnDataAsArray);
        }
        JTable table = new JTable(tableModel);
        return table;
    }

    public void add(T object) throws IllegalArgumentException, IllegalAccessException {
        Connection bdConnection = null;
        PreparedStatement  statement = null;
        String query = createAddQuery(object);
        try {
            bdConnection = ConnectionFactory.getConnection();
            statement = (PreparedStatement) bdConnection.prepareStatement(query);
            statement.executeUpdate();
        } catch(SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(bdConnection);
        }
    }

    public List<T> findByFirstName(String firstName) {
        Connection bdConnection = null;
        PreparedStatement  statement = null;
        ResultSet resultSet = null;
        String query = createFindQuery("first_name");
        try {
            bdConnection = ConnectionFactory.getConnection();
            statement = (PreparedStatement) bdConnection.prepareStatement(query);
            statement.setString(1, firstName);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch(SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findByFirstName " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(bdConnection);
        }
        return null;
    }

    public T findById(int id) {
        Connection bdConnection = null;
        PreparedStatement  statement = null;
        ResultSet resultSet = null;
        String query = createFindQuery("id");
        try {
            bdConnection = ConnectionFactory.getConnection();
            statement = (PreparedStatement) bdConnection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch(SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(bdConnection);
        }
        return null;
    }

    private List<T> createObjects(ResultSet resultSet){
        List<T> list = new ArrayList<T>();

        try {
            try {
                while(resultSet.next()) {
                    T instance = type.newInstance();
                    for(Field field: type.getDeclaredFields()) {
                        Object value =  resultSet.getObject(field.getName());
                        PropertyDescriptor propertyDescriptor = new  PropertyDescriptor(field.getName(), type);
                        Method method = propertyDescriptor.getWriteMethod();
                        method.invoke(instance, value);
                    }
                    list.add(instance);
                }
            } catch (IllegalAccessException | SecurityException | IllegalArgumentException | InvocationTargetException | SQLException | IntrospectionException e) {
                e.printStackTrace();
            }
        }catch(InstantiationException e) {
            e.printStackTrace();
        }

        return list;
    }

}