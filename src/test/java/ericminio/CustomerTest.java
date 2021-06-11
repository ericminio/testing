package ericminio;

import ericminio.domain.Customer;
import ericminio.domain.Customers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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

}
