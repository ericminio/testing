package ericminio;

import ericminio.domain.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public abstract class CustomerTest {
    protected abstract Scope scoped();
    private Gate gate;

    @Before
    public void newCustomers() {
        gate = scoped().gate();
        gate.save(new Customer("alice"));
    }

    @Test
    public void startWithEmptyCart() {
        Customer alice = gate.find("alice");

        assertThat(alice.getCartSize(), equalTo(0));
    }

    @Test
    public void canAddItemsToTheirCart() {
        Customer alice = gate.find("alice");
        gate.acceptChoice(alice, "this-item");
        gate.acceptChoice(alice, "this-other-item");

        alice = gate.find("alice");
        assertThat(alice.getCartSize(), equalTo(2));
    }

    @Test
    public void cartIsLimitedToThreeItems() {
        Customer alice = gate.find("alice");
        gate.acceptChoice(alice, "item-1");
        gate.acceptChoice(alice, "item-2");
        gate.acceptChoice(alice, "item-3");
        try {
            gate.acceptChoice(alice, "item-4");
            fail();
        }
        catch (Exception raised) {
            assertThat(raised.getMessage(), equalTo("cart size limit reached"));
        }
    }
}
