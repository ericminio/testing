package ericminio.http.mapping;

import ericminio.domain.Cart;
import ericminio.domain.Customer;
import ericminio.http.support.JsonToMapsParser;

import java.util.List;
import java.util.Map;

public class JsonToCustomer {
    public Customer please(String json) {
        Map<String, Object> tree = (Map<String, Object>) new JsonToMapsParser().parse(json);
        String name = (String) tree.get("name");
        Customer customer = new Customer(name);
        Cart cart = new Cart();
        customer.setCart(cart);
        List<Map> items = (List<Map>) tree.get("cart");
        if (items != null) {
            for (Map item : items) {
                cart.addItem((String) item.get("label"));
            }
        }
        return customer;
    }
}
