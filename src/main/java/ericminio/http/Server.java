package ericminio.http;

import com.sun.net.httpserver.HttpServer;
import ericminio.domain.StorageFacade;

import java.net.InetSocketAddress;

public class Server {
    private int port;
    private HttpServer httpServer;
    private Find find;
    private Save save;
    private AddToCart addToCart;

    public Server(int port, StorageFacade storageFacade) {
        this.port = port;
        this.find = new Find(storageFacade);
        this.save = new Save(storageFacade);
        this.addToCart = new AddToCart(storageFacade);
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
