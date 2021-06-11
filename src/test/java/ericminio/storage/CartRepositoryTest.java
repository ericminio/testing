package ericminio.storage;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class CartRepositoryTest extends StorageTest {

    @Test
    public void LabelSizeLimitation() {
        try {
            int customer_id = new VisitorRepository(getDatabase()).createNew("any-name");
            new CartRepository(getDatabase()).createNew(customer_id, "this is a long label that will be rejected");
            fail();
        }
        catch (Exception e) {
            assertThat(e.getCause().getMessage(),
                    equalTo("data exception: string data, right truncation;  table: CART column: LABEL"));

        }
    }
}
