package ericminio.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ericminio.domain.Customer;
import ericminio.domain.StorageFacade;
import ericminio.http.mapping.CustomerToJson;

import java.io.IOException;

public class Find implements HttpHandler {

    private StorageFacade storageFacade;

    public Find(StorageFacade storageFacade) {
        this.storageFacade = storageFacade;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String query = exchange.getRequestURI().getQuery();
            String name = query.substring(query.indexOf("name=")+"name=".length());
            Customer customer = storageFacade.find(name);
            String body = new CustomerToJson().please(customer);
            exchange.getResponseHeaders().add("content-type", "application/json");
            exchange.sendResponseHeaders(200, body.getBytes().length);
            exchange.getResponseBody().write(body.getBytes());
            exchange.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            exchange.getResponseHeaders().add("content-type", "text/plain");
            exchange.sendResponseHeaders(500, e.getMessage().length());
            exchange.getResponseBody().write(e.getMessage().getBytes());
            exchange.close();
        }
    }
}
