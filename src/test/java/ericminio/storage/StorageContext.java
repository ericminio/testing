package ericminio.storage;

import ericminio.Context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StorageContext implements Context {

    private Database database;

    public StorageContext() throws Exception {
        Class.forName("org.hsqldb.jdbcDriver");
        Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "sa", "sa");
        this.database = new Database(connection);
    }

    public Database getDatabase() {
        return database;
    }

    public void clean() throws SQLException {
        database.executeIgnoringErrors("drop table customer");
        database.executeIgnoringErrors("drop table cart");
        database.executeIgnoringErrors("drop sequence customer_id_sequence");
        database.executeIgnoringErrors("drop sequence cart_id_sequence");

        database.execute("create table customer(id int, name varchar(15))");
        database.execute("create sequence customer_id_sequence");

        database.execute("create table cart(id int, customer_id int, label varchar(15))");
        database.execute("create sequence cart_id_sequence");
    }
}
