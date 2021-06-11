package ericminio.http.mapping;

import ericminio.domain.Customer;
import ericminio.http.support.MapsToJsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerToJson {

    public String please(Customer customer) {
        Map<String, Object> tree = new HashMap<>();
        tree.put("name", customer.getName());
        List<Map> items = new ArrayList<>();
        for (int i=0; i<customer.getCartSize(); i++) {
            String label = customer.getCart().get(i);
            Map<String, Object> item = new HashMap<>();
            item.put("label", label);
            items.add(item);
        }
        tree.put("cart", items);
        String json = MapsToJsonParser.stringify(tree);

        return json;
    }
}
