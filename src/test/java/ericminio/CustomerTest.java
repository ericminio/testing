package ericminio;

import ericminio.domain.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public abstract class CustomerTest {
    protected abstract Scope scoped();
    private Interactions please;

    @Before
    public void aNewCustomer() {
        please = scoped().interactions();
        please.save(new Customer("alice"));
    }

    @Test
    public void startsWithEmptyCart() {
        Customer alice = please.find("alice");

        assertThat(alice.getCartSize(), equalTo(0));
    }

    @Test
    public void canAddItemsToTheirCart() {
        Customer alice = please.find("alice");
        please.recordChoice(alice, "this-item");
        please.recordChoice(alice, "this-other-item");

        alice = please.find("alice");
        assertThat(alice.getCartSize(), equalTo(2));
    }

    @Test
    public void isLimitedToThreeItemsInCart() {
        Customer alice = please.find("alice");
        please.recordChoice(alice, "item-1");
        please.recordChoice(alice, "item-2");
        please.recordChoice(alice, "item-3");
        try {
            please.recordChoice(alice, "item-4");
            fail();
        }
        catch (Exception raised) {
            assertThat(raised.getMessage(), equalTo("cart size limit reached"));
        }
    }
}
