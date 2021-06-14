package ericminio.http.adapters;

import com.sun.net.httpserver.HttpExchange;
import ericminio.domain.Customer;
import ericminio.domain.Repository;
import ericminio.http.support.JsonToMapsParser;
import ericminio.http.support.Stringify;

import java.io.IOException;
import java.util.Map;

public class AddToCart extends SafeHandler {

    public AddToCart(Repository repository) {
        super(repository);
    }

    @Override
    public void handleSafely(HttpExchange exchange) throws IOException {
        String json = new Stringify().inputStream(exchange.getRequestBody());
        Map<String, Object> fields = (Map<String, Object>) new JsonToMapsParser().parse(json);
        String name = (String) fields.get("name");
        String label = (String) fields.get("label");
        Customer customer = repository.find(name);
        customer.chooses(label);
        repository.save(customer);
        String body = "{\"outcome\":\"success\"}";
        exchange.getResponseHeaders().add("content-type", "application/json");
        exchange.sendResponseHeaders(200, body.getBytes().length);
        exchange.getResponseBody().write(body.getBytes());
        exchange.close();
    }
}
