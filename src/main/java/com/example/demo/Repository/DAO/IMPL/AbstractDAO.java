package com.example.demo.Repository.DAO.IMPL;

import com.example.demo.Mapper.IMapper;
import com.example.demo.Repository.DAO.IGeneric;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AbstractDAO<T> implements IGeneric<T> {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
    private static final String URL = resourceBundle.getString("URL");
    private static final String USERNAME = resourceBundle.getString("USERNAME");
    private static final String PASSWORD = resourceBundle.getString("PASSWORD");

    public static Connection getConnection() {
        try {
            Class.forName(resourceBundle.getString("DRIVER"));
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException exception) {
            return null;
        }
    }
    
    private void setParams(PreparedStatement statement, Object... params) {
        try {
            for (int i = 0; i < params.length; i++) {
                int index = i + 1;
                if (params[i] instanceof Integer) {
                    statement.setInt(index, (int) params[i]);
                } else if (params[i] instanceof Long) {
                    statement.setLong(index, (long) params[i]);
                } else if (params[i] instanceof String) {
                    statement.setString(index, (String) params[i]);
                } else if (params[i] instanceof Double) {
                    statement.setDouble(index, (double) params[i]);
                } else if(params[i] instanceof Date) {
                    statement.setDate(index, (Date) params[i]);
                } else if(params[i] == null) {
                    statement.setNull(index, java.sql.Types.NULL);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<T> query(String sql, IMapper<T> mapper, Object... params) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            assert connection != null;
            statement = connection.prepareStatement(sql);
            // set Params
            setParams(statement, params);
            resultSet = statement.executeQuery();
            List<T> result = new ArrayList<>();
            while (resultSet.next()) {
                T t = mapper.rowMapper(resultSet);
                result.add(t);
            }
            return result;
        }catch (SQLException e){
            return null;
        } finally {
            try {
                if (connection != null) connection.close();
                if (statement != null) statement.close();
                if(resultSet != null) resultSet.close();
            } catch (SQLException ex) {
                return null;
            }
        }
    }

    @Override
    public Long insert(String sql, Object... params) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Long id = null;
        try {
            connection = getConnection();
            assert connection != null;
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setParams(statement, params);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) id = resultSet.getLong(1);
            connection.commit();
            return id;
        } catch (SQLException exception) {
            return null;
        } finally {
            try {
                if(connection != null) connection.close();
                if(resultSet != null) resultSet.close();
                if(statement != null) statement.close();
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    @Override
    public void update(String sql, Object... params) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            assert connection != null;
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            setParams(statement, params);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException exception) {
            throw new RuntimeException("Can't not delete");
        } finally {
            try {
                if(connection != null) connection.close();
                if(statement != null) statement.close();
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
