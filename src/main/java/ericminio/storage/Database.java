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

    public String selectString(String sql) throws SQLException {
        CallableStatement statement = connection.prepareCall(sql);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        return resultSet.getString(1);
    }
}
