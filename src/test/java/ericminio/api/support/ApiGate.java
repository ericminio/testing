package ericminio.api.support;

import ericminio.Gate;
import ericminio.domain.Customer;
import ericminio.http.mapping.CustomerToJson;
import ericminio.http.mapping.JsonToCustomer;
import ericminio.http.support.MapsToJsonParser;

import java.util.HashMap;
import java.util.Map;

import static ericminio.api.support.GetRequest.get;
import static ericminio.api.support.PostRequest.post;
import static java.lang.String.format;

public class ApiGate implements Gate {
    private int port;

    public ApiGate(int port) {
        this.port = port;
    }

    @Override
    public void save(Customer customer) {
        try {
            String body = new CustomerToJson().please(customer);
            post(format("http://localhost:%d/customers/save", port), body);
        } catch (Exception raised) {
            throw new RuntimeException(raised);
        }
    }

    @Override
    public Customer find(String name) {
        try {
            HttpResponse httpResponse = get(format("http://localhost:%d/customers/find?name=%s", port, name));
            String json = httpResponse.getBody();
            Customer customer = new JsonToCustomer().please(json);
            return customer;
        } catch (Exception raised) {
            throw new RuntimeException(raised);
        }
    }

    @Override
    public void recordChoice(Customer customer, String label) {
        Map<String, Object> tree = new HashMap<>();
        tree.put("name", customer.getName());
        tree.put("label", label);
        String body = MapsToJsonParser.stringify(tree);
        HttpResponse response = null;
        try {
            response = post(format("http://localhost:%d/customers/add-to-cart", port), body);
        } catch (Exception raised) {
            throw new RuntimeException(raised);
        }
        if (response.getStatusCode() != 200) {
            throw new RuntimeException(response.getBody());
        }
    }
}
