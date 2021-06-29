package ericminio.storage;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

    private Connection connection;

    public Database(Connection connection) {
        this.connection = connection;
    }

    public void execute(String sql) throws SQLException {
        execute(sql, new Object[]{});
    }
    public void executeIgnoringErrors(String sql) {
        try { execute(sql); }
        catch (Exception ignored) {}
    }

    public void execute(String sql, Object[] parameters) throws SQLException {
        CallableStatement statement = setParameters(sql, parameters);
        statement.execute();
    }

    public ResultSet selectRows(String sql, Object[] parameters) throws SQLException {
        CallableStatement statement = setParameters(sql, parameters);
        return statement.executeQuery();
    }

    public boolean exists(String sql, Object[] parameters) throws SQLException {
        CallableStatement statement = setParameters(sql, parameters);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

    public int selectInt(String sql, Object[] parameters) throws SQLException {
        CallableStatement statement = setParameters(sql, parameters);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    private CallableStatement setParameters(String sql, Object[] parameters) throws SQLException {
        CallableStatement statement = connection.prepareCall(sql);
        for (int i = 0; i < parameters.length; i++) {
            statement.setObject(i + 1, parameters[i]);
        }
        return statement;
    }
}
