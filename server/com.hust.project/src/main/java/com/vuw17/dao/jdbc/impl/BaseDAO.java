package com.vuw17.dao.jdbc.impl;

import com.vuw17.dao.jdbc.GenericDAO;

import java.math.BigDecimal;
import java.sql.*;

public class BaseDAO<T> implements GenericDAO<T> {
    public Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/hotel_management?user=root&password=123456789&useUnicode=true&characterEncoding=utf8&useSSL=false";
            return DriverManager.getConnection(url);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Failed Connection : " + e.getMessage());
            return null;
        }
    }

    @Override
    public int insertOne(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int id = -1;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setParameter(preparedStatement, parameters);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                id = (int) resultSet.getLong(1);
            }
            connection.commit();
        } catch (SQLException e) {

            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                System.out.println("Failed__InsertOne__BaseDAO__0 : " + e1.getMessage());
                return -1;
            }
            System.out.println("Failed__InsertOne__BaseDAO__1");
            System.out.println("" + e.getMessage());
            return -1;
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e2) {
                System.out.println("Failed__InsertOne__BaseDAO__2 : " + e2.getMessage());
                return -1;
            }
        }
        return id;
    }

    public void setParameter(PreparedStatement preparedStatement, Object... parameters) {
        for (int i = 0; i < parameters.length; i++) {
            Object parameter = parameters[i];
            int index = i + 1;
            try {
                if (parameter instanceof String) {
                    System.out.println("String");
                    preparedStatement.setString(index, (String) parameter);
                }
                if (parameter instanceof BigDecimal) {
                    preparedStatement.setBigDecimal(index, (BigDecimal) parameter);
                }
                if (parameter instanceof Boolean) {
                    System.out.println("Boolean");
                    preparedStatement.setBoolean(index, (boolean) parameter);
                }
                if (parameter instanceof Integer) {
                    System.out.println("Int");
                    preparedStatement.setInt(index, (int) parameter);
                }
            } catch (SQLException e) {
                System.out.println("Loi parameter");
            }

        }
    }

}
