package ericminio.http.adapters;

import com.sun.net.httpserver.HttpExchange;
import ericminio.domain.Customer;
import ericminio.domain.Repository;
import ericminio.http.mapping.JsonToCustomer;
import ericminio.http.support.Stringify;

import java.io.IOException;

public class Save extends SafeHandler {

    public Save(Repository repository) {
        super(repository);
    }

    @Override
    public void handleSafely(HttpExchange exchange) throws IOException {
        String json = new Stringify().inputStream(exchange.getRequestBody());
        Customer customer = new JsonToCustomer().please(json);
        repository.save(customer);
        String body = "{\"outcome\":\"success\"}";
        exchange.getResponseHeaders().add("content-type", "application/json");
        exchange.sendResponseHeaders(200, body.getBytes().length);
        exchange.getResponseBody().write(body.getBytes());
        exchange.close();
    }
}
