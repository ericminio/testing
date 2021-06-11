package ericminio;

import ericminio.domain.CartLimitReached;
import ericminio.domain.Customer;
import ericminio.domain.StorageFacade;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public abstract class CustomerTest {
    protected abstract Scope scoped();
    private StorageFacade storageFacade;

    @Before
    public void newCustomers() {
        storageFacade = scoped().storageFacade();
        storageFacade.save(new Customer("alice"));
    }

    @Test
    public void startWithEmptyCart() {
        Customer alice = storageFacade.find("alice");

        assertThat(alice.getCartSize(), equalTo(0));
    }

    @Test
    public void canAddItemsToTheirCart() {
        Customer alice = storageFacade.find("alice");
        alice.chooses("this-item");
        alice.chooses("this-other-item");
        alice = storageFacade.find("alice");

        assertThat(alice.getCartSize(), equalTo(2));
    }

    @Test
    public void cartIsLimitedToThreeItems() {
        Customer alice = storageFacade.find("alice");
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
