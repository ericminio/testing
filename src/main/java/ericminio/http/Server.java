package ericminio.http;

import com.sun.net.httpserver.HttpServer;
import ericminio.domain.ports.Repository;
import ericminio.http.api.AddToCart;
import ericminio.http.api.Find;
import ericminio.http.api.Save;

import java.net.InetSocketAddress;

public class Server {
    private Repository repository;
    private HttpServer httpServer;
    private int port;

    public Server(int port, Repository repository) {
        this.repository = repository;
        this.port = port;
    }

    public void start() {
        try {
            httpServer = HttpServer.create(new InetSocketAddress(port), 0);
            httpServer.createContext("/customers/find", new Find(repository));
            httpServer.createContext("/customers/save", new Save(repository));
            httpServer.createContext("/customers/add-to-cart", new AddToCart(repository));
            httpServer.start();
        } catch (Exception raised) {
            throw new RuntimeException(raised);
        }
    }
}
