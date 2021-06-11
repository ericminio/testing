package ericminio.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ericminio.domain.Customer;
import ericminio.domain.StorageFacade;
import ericminio.http.mapping.JsonToCustomer;
import ericminio.http.support.Stringify;

import java.io.IOException;

public class Save implements HttpHandler {
    private StorageFacade storageFacade;

    public Save(StorageFacade storageFacade) {
        this.storageFacade = storageFacade;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String json = new Stringify().inputStream(exchange.getRequestBody());
            Customer customer = new JsonToCustomer().please(json);
            storageFacade.save(customer);
            String body = "{\"outcome\":\"success\"}";
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
