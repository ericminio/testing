package ericminio.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ericminio.domain.Customer;
import ericminio.domain.StorageFacade;
import ericminio.http.support.JsonToMapsParser;
import ericminio.http.support.Stringify;

import java.io.IOException;
import java.util.Map;

public class AddToCart implements HttpHandler {
    private StorageFacade storageFacade;

    public AddToCart(StorageFacade storageFacade) {
        this.storageFacade = storageFacade;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String json = new Stringify().inputStream(exchange.getRequestBody());
            Map<String, Object> fields = (Map<String, Object>) new JsonToMapsParser().parse(json);
            String name = (String) fields.get("name");
            String label = (String) fields.get("label");
            Customer customer = storageFacade.find(name);
            customer.chooses(label);
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
