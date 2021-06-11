package ericminio.api.support;

import ericminio.Gate;
import ericminio.http.Server;
import ericminio.storage.support.StorageTest;

public class ApiTest extends StorageTest {

    private static Server server;
    int port = 8005;

    public ApiTest() {
        if (server == null) {
            server = new Server(port, storageFacade());
            server.start();
        }
    }

    @Override
    public Gate gate() {
        return new ApiGate(port, storageFacade());
    }
}
