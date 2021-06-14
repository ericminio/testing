package ericminio.http.support;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ericminio.domain.ports.Repository;

import java.io.IOException;

public abstract class SafeHandler implements HttpHandler {
    protected Repository repository;

    public SafeHandler(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            handleSafely(exchange);
        }
        catch (Exception e) {
            exchange.getResponseHeaders().add("content-type", "text/plain");
            exchange.sendResponseHeaders(500, e.getMessage().length());
            exchange.getResponseBody().write(e.getMessage().getBytes());
            exchange.close();
        }
    }

    protected abstract void handleSafely(HttpExchange exchange) throws IOException;
}
