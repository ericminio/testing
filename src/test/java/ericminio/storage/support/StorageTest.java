package ericminio.storage.support;

import ericminio.Interactions;
import ericminio.Scope;
import ericminio.domain.StorageFacade;
import ericminio.storage.adapters.Database;
import ericminio.storage.migrations.CreateAll;

import java.sql.Connection;
import java.sql.DriverManager;

public class StorageTest implements Scope {

    private StorageInteractions storageGate;
    private Database database;

    public StorageTest() {
        DropAll.now(inMemoryDatabase());
        CreateAll.now(inMemoryDatabase());
        this.storageGate = new StorageInteractions(inMemoryDatabase());
    }

    @Override
    public Interactions interactions() {
        return storageGate;
    }

    protected StorageFacade getStorageFacade() {
        return this.storageGate.getStorageFacade();
    }

    protected Database inMemoryDatabase() {
        if (this.database == null) {
            try {
                Class.forName("org.hsqldb.jdbcDriver");
                Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "sa", "sa");
                this.database = new Database(connection);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return database;
    }

}
