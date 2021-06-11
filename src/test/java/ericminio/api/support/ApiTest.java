package ericminio.api.support;

import ericminio.Interactions;
import ericminio.http.Server;
import ericminio.storage.support.StorageTest;

public class ApiTest extends StorageTest {

    private static Server server;
    int port = 8005;

    public ApiTest() {
        if (server == null) {
            server = new Server(port, getStorageFacade());
            server.start();
        }
    }

    @Override
    public Interactions interactions() {
        return new ApiInteractions(port);
    }
}
