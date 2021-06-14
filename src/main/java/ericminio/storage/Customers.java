package ericminio.storage;

import ericminio.domain.Customer;

import java.sql.SQLException;

import static java.lang.String.format;

public class Customers {
    private Database database;

    public Customers(Database database) {
        this.database = database;
    }

    public int createIfNotExists(Customer customer) throws SQLException {
        boolean exists = database.exists(format("select id from customer where name='%s'", customer.getName()));
        return exists ? id(customer.getName()) : create(customer);
    }

    public int id(String name) throws SQLException {
        return database.selectInt(format("select id from customer where name = '%s'", name));
    }

    private int create(Customer customer) throws SQLException {
        int customer_id = database.selectInt("call next value for customer_id_sequence");
        database.execute(format("insert into customer(id, name) values(%d, '%s')", customer_id, customer.getName()));
        return customer_id;
    }
}
