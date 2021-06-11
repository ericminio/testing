package ericminio;

import ericminio.domain.Customer;
import ericminio.domain.Customers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public abstract class VisitorTest {

    private Customers customers;
    protected abstract TestContext getContext();

    @Before
    public void brandNewCustomer() {
        customers = getContext().getCustomers();
        customers.save(new Customer("alice"));
    }

    @Test
    public void startsWithEmptyCart() {
        Customer alice = customers.findByName("alice");

        assertThat(alice.getCartSize(), equalTo(0));
    }

    @Test
    public void canAddToCart() {
        Customer alice = customers.findByName("alice");
        alice.chooses("this-item");
        customers.save(alice);
        alice = customers.findByName("alice");

        assertThat(alice.getCartSize(), equalTo(1));
    }

}
