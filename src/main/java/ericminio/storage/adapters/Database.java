package ericminio.storage.adapters;

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
        CallableStatement statement = connection.prepareCall(sql);
        statement.execute();
    }
    public void executeIgnoringErrors(String sql) {
        try { execute(sql); }
        catch (Exception ignored) {}
    }

    public int selectInt(String sql) throws SQLException {
        CallableStatement statement = connection.prepareCall(sql);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    public ResultSet selectRows(String sql) throws SQLException {
        CallableStatement statement = connection.prepareCall(sql);
        return statement.executeQuery();
    }

    public boolean exists(String sql) throws SQLException {
        CallableStatement statement = connection.prepareCall(sql);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }
}
