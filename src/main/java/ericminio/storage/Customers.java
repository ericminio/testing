package ericminio.storage;

import ericminio.domain.Customer;

import java.sql.SQLException;

public class Customers {
    private Database database;

    public Customers(Database database) {
        this.database = database;
    }

    public int createIfNotExists(Customer customer) throws SQLException {
        boolean exists = database.exists("select id from customer where name=?", new Object[] {customer.getName()});
        return exists ? id(customer.getName()) : create(customer);
    }

    public int id(String name) throws SQLException {
        return database.selectInt("select id from customer where name = ?", new Object[] {name});
    }

    private int create(Customer customer) throws SQLException {
        int customer_id = database.selectInt("call next value for customer_id_sequence", new Object[]{});
        database.execute("insert into customer(id, name) values(?, ?)", new Object[] {customer_id, customer.getName()});
        return customer_id;
    }
}
