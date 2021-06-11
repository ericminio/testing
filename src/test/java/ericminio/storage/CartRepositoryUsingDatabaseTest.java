package ericminio.storage;

import ericminio.domain.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class CartRepositoryUsingDatabaseTest extends StorageTest {
    CartRepositoryUsingDatabase cartRepositoryUsingDatabase;

    @Before
    public void sut() {
        cartRepositoryUsingDatabase = new CartRepositoryUsingDatabase(inMemoryDatabase());
    }

    @Test
    public void labelSizeLimitation() {
        try {
            Customer ed = new Customer("ed");
            ed.chooses("this too long label will be rejected");
            new CustomerRepositoryUsingDatabase(inMemoryDatabase()).save(ed);
            cartRepositoryUsingDatabase.save(ed);
            fail();
        }
        catch (Exception creating) {
            assertThat(creating.getCause().getMessage(),
                    equalTo("data exception: string data, right truncation;  table: CART column: LABEL"));
        }
    }
}
