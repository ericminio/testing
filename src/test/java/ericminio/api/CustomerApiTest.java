package ericminio.api;

import ericminio.Gate;
import ericminio.Scope;
import ericminio.api.support.ApiTest;
import ericminio.domain.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CustomerApiTest {

    protected Scope scoped() {
        return new ApiTest();
    }
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
}
