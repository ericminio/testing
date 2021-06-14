package ericminio.storage.adapters;

import ericminio.domain.Cart;
import ericminio.domain.Customer;
import ericminio.domain.ports.Repository;
import ericminio.storage.Carts;
import ericminio.storage.Customers;
import ericminio.storage.Database;

import java.sql.SQLException;

public class RepositoryUsingDatabase implements Repository {
    private Database database;

    public RepositoryUsingDatabase(Database database) {
        this.database = database;
    }

    @Override
    public void save(Customer customer) {
        try {
            int customer_id = new Customers(database).createIfNotExists(customer);
            new Carts(database).recreate(customer.getCart(), customer_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer find(String name) {
        try {
            int customer_id = new Customers(database).id(name);
            Cart cart = new Carts(database).find(customer_id);
            Customer customer = new Customer(name);
            customer.setCart(cart);
            return customer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
