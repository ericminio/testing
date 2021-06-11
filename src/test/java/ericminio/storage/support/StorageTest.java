package ericminio.storage.support;

import ericminio.Scope;
import ericminio.domain.Customers;
import ericminio.storage.adapters.CartRepositoryUsingDatabase;
import ericminio.storage.adapters.CustomerRepositoryUsingDatabase;
import ericminio.storage.adapters.Database;
import ericminio.storage.migrations.CreateAll;

import java.sql.Connection;
import java.sql.DriverManager;

public class StorageTest implements Scope {

    private Database database;

    public StorageTest() {
        DropAll.now(inMemoryDatabase());
        CreateAll.now(inMemoryDatabase());
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
