package ericminio.storage;

import ericminio.domain.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class CartRepositoryUsingTest extends StorageTest {
    CartRepositoryUsing cartRepositoryUsing;

    @Before
    public void sut() {
        cartRepositoryUsing = new CartRepositoryUsing(inMemoryDatabase());
    }

    @Test
    public void labelSizeLimitation() {
        try {
            Customer ed = new Customer("ed");
            ed.chooses("this too long label will be rejected");
            new CustomerRepositoryUsing(inMemoryDatabase()).save(ed);
            cartRepositoryUsing.save(ed);
            fail();
        }
        catch (Exception creating) {
            assertThat(creating.getCause().getMessage(),
                    equalTo("data exception: string data, right truncation;  table: CART column: LABEL"));
        }
    }
}
