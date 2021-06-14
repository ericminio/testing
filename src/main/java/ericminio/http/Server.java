package ericminio.http;

import com.sun.net.httpserver.HttpServer;
import ericminio.domain.Repository;

import java.net.InetSocketAddress;

public class Server {
    private int port;
    private HttpServer httpServer;
    private Find find;
    private Save save;
    private AddToCart addToCart;

    public Server(int port, Repository repository) {
        this.port = port;
        this.find = new Find(repository);
        this.save = new Save(repository);
        this.addToCart = new AddToCart(repository);
    }

    public void start() {
        try {
            httpServer = HttpServer.create(new InetSocketAddress(port), 0);
            httpServer.createContext("/customers/find", find);
            httpServer.createContext("/customers/save", save);
            httpServer.createContext("/customers/add-to-cart", addToCart);
            httpServer.start();
        } catch (Exception raised) {
            throw new RuntimeException(raised);
        }
    }
}
