package ericminio.storage.adapters;

import ericminio.domain.Cart;
import ericminio.domain.Customer;
import ericminio.storage.support.StorageTest;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class RepositoryUsingDatabaseTest extends StorageTest {
    private RepositoryUsingDatabase repositoryUsingDatabase;
    private Database database;

    @Before
    public void sut() {
        database = inMemoryDatabase();
        repositoryUsingDatabase = new RepositoryUsingDatabase(database);
    }

    @Test
    public void labelSizeLimitation() {
        try {
            Cart cart = new Cart();
            cart.addItem("this too long label will be rejected");
            repositoryUsingDatabase.save(new Customer("any name") {{ setCart(cart); }});
            fail();
        }
        catch (Exception creating) {
            assertThat(creating.getCause().getMessage(),
                    equalTo("data exception: string data, right truncation;  table: CART column: LABEL"));
        }
    }

    @Test
    public void customerSaveDoesNotDuplicateCustomer() throws SQLException {
        Customer ed = new Customer("ed");
        repositoryUsingDatabase.save(ed);
        repositoryUsingDatabase.save(ed);
        int count = database.selectInt("select count(1) from customer");

        assertThat(count, equalTo(1));
    }
}
