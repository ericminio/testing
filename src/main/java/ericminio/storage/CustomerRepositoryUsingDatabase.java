package ericminio.storage;

import ericminio.domain.Customer;
import ericminio.ports.CustomerRepository;

import java.sql.SQLException;

import static java.lang.String.format;

public class CustomerRepositoryUsingDatabase implements CustomerRepository {

    private Database database;

    public CustomerRepositoryUsingDatabase(Database database) {
        this.database = database;
    }

    @Override
    public void create(Customer customer) {
        try {
            int id = database.selectInt("call next value for customer_id_sequence");
            database.execute(format("insert into customer(id, name) values(%d, '%s')", id, customer.getName()));
        } catch (SQLException e) {
            throw new RuntimeException("insert failed", e);
        }
    }

    @Override
    public Customer findOneByName(String name) {
        try {
            database.selectInt(format("select id from customer where name = '%s'", name));
            return new Customer(name);
        } catch (SQLException e) {
            throw new RuntimeException("select failed", e);
        }
    }
}
