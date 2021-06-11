package ericminio.api.mapping;

import ericminio.domain.Cart;
import ericminio.domain.Customer;
import ericminio.http.mapping.CustomerToJson;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class CustomerToJsonTest {

    @Test
    public void includesName() {
        Customer ed = new Customer("ed");
        String json = new CustomerToJson().please(ed);

        assertThat(json, containsString("\"name\":\"ed\""));
    }

    @Test
    public void includesDefaultEmptyCart() {
        Customer ed = new Customer("ed");
        String json = new CustomerToJson().please(ed);

        assertThat(json, containsString("\"cart\":[]"));
    }

    @Test
    public void includesCartItems() {
        Customer ed = new Customer("ed");
        Cart cart = new Cart();
        cart.add("item-1");
        cart.add("item-2");
        ed.setCart(cart);
        String json = new CustomerToJson().please(ed);

        assertThat(json, containsString("\"cart\":[{\"label\":\"item-1\"},{\"label\":\"item-2\"}]"));
    }
}
