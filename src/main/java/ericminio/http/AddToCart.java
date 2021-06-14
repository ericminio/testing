package ericminio.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ericminio.domain.Customer;
import ericminio.domain.Repository;
import ericminio.http.support.JsonToMapsParser;
import ericminio.http.support.Stringify;

import java.io.IOException;
import java.util.Map;

public class AddToCart implements HttpHandler {
    private Repository repository;

    public AddToCart(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
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
        catch (Exception e) {
            exchange.getResponseHeaders().add("content-type", "text/plain");
            exchange.sendResponseHeaders(500, e.getMessage().length());
            exchange.getResponseBody().write(e.getMessage().getBytes());
            exchange.close();
        }
    }
}
