package ericminio.api.mapping;

import ericminio.domain.Customer;
import ericminio.http.mapping.JsonToCustomer;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class JsonToCustomerTest {

    @Test
    public void parsesName() {
        String json = "{\"name\":\"alice\"}";
        Customer customer = new JsonToCustomer().please(json);

        assertThat(customer.getName(), equalTo("alice"));
    }

    @Test
    public void defaultsToEmptyCart() {
        String json = "{\"name\":\"alice\"}";
        Customer customer = new JsonToCustomer().please(json);

        assertThat(customer.getCartSize(), equalTo(0));
    }

    @Test
    public void resistsEmptyCart() {
        String json = "{\"name\":\"alice\",\"cart\":[]}";
        Customer customer = new JsonToCustomer().please(json);

        assertThat(customer.getCartSize(), equalTo(0));
    }

    @Test
    public void canParseCartWithOneItem() {
        String json = "{\"name\":\"alice\",\"cart\":[{\"label\":\"item-1\"}]}";
        Customer customer = new JsonToCustomer().please(json);

        assertThat(customer.getCartSize(), equalTo(1));
        assertThat(customer.getCart().get(0), equalTo("item-1"));
    }

    @Test
    public void canParseCartWithTwoItems() {
        String json = "{\"name\":\"alice\",\"cart\":[{\"label\":\"item-1\"}, {\"label\":\"item-2\"}]}";
        Customer customer = new JsonToCustomer().please(json);

        assertThat(customer.getCartSize(), equalTo(2));
        assertThat(customer.getCart().get(0), equalTo("item-1"));
        assertThat(customer.getCart().get(1), equalTo("item-2"));
    }
}
