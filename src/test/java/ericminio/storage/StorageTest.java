package ericminio.storage;

import ericminio.Scope;
import ericminio.domain.Customers;

import java.sql.Connection;
import java.sql.DriverManager;

public class StorageTest implements Scope {

    private Database database;

    public StorageTest() {
        new Drop(inMemoryDatabase()).now();
        new Structure(inMemoryDatabase()).now();
    }

    @Override
    public Customers customers() {
        Customers customers = new Customers();
        customers.setCustomerRepository(new CustomerRepositoryUsingDatabase(inMemoryDatabase()));
        customers.setCartRepository(new CartRepositoryUsingDatabase(inMemoryDatabase()));
        return customers;
    }

    protected Database inMemoryDatabase() {
        if (this.database == null) {
            try {
                Class.forName("org.hsqldb.jdbcDriver");
                Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "sa", "sa");
                this.database = new Database(connection);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return database;
    }

}
