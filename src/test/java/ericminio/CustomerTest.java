package ericminio;

import ericminio.domain.CartLimitReached;
import ericminio.domain.Customer;
import ericminio.domain.Customers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public abstract class CustomerTest {
    protected abstract Scope scoped();
    private Customers customers;

    @Before
    public void newCustomers() {
        customers = scoped().customers();
        customers.save(new Customer("alice"));
    }

    @Test
    public void startWithEmptyCart() {
        Customer alice = customers.find("alice");

        assertThat(alice.getCartSize(), equalTo(0));
    }

    @Test
    public void canAddItemsToTheirCart() {
        Customer alice = customers.find("alice");
        alice.chooses("this-item");
        alice.chooses("this-other-item");
        customers.save(alice);
        alice = customers.find("alice");

        assertThat(alice.getCartSize(), equalTo(2));
    }

    @Test
    public void cartIsLimitedToThreeItems() {
        Customer alice = customers.find("alice");
        alice.chooses("item-1");
        alice.chooses("item-2");
        alice.chooses("item-3");
        try {
            alice.chooses("item-4");
            fail();
        }
        catch (Exception raised) {
            assertThat(raised, instanceOf(CartLimitReached.class));
        }
    }
}
