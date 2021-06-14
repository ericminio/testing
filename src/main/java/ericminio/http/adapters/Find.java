package ericminio.http.adapters;

import com.sun.net.httpserver.HttpExchange;
import ericminio.domain.Customer;
import ericminio.domain.Repository;
import ericminio.http.mapping.CustomerToJson;
import ericminio.http.support.SafeHandler;

import java.io.IOException;

public class Find extends SafeHandler {

    public Find(Repository repository) {
        super(repository);
    }

    @Override
    protected void handleSafely(HttpExchange exchange) throws IOException {
        String query = exchange.getRequestURI().getQuery();
        String name = query.substring(query.indexOf("name=")+"name=".length());
        Customer customer = repository.find(name);
        String body = new CustomerToJson().please(customer);
        exchange.getResponseHeaders().add("content-type", "application/json");
        exchange.sendResponseHeaders(200, body.getBytes().length);
        exchange.getResponseBody().write(body.getBytes());
        exchange.close();
    }
}
