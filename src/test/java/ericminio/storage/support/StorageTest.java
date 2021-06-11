package ericminio.storage.support;

import ericminio.Gate;
import ericminio.Scope;
import ericminio.domain.StorageFacade;
import ericminio.storage.adapters.Database;
import ericminio.storage.adapters.RepositoryUsingDatabase;
import ericminio.storage.migrations.CreateAll;

import java.sql.Connection;
import java.sql.DriverManager;

public class StorageTest implements Scope {

    private Database database;
    private StorageFacade storageFacade;

    public StorageTest() {
        DropAll.now(inMemoryDatabase());
        CreateAll.now(inMemoryDatabase());
        this.storageFacade = new StorageFacade();
        this.storageFacade.setRepository(new RepositoryUsingDatabase(inMemoryDatabase()));
    }

    @Override
    public Gate gate() {
        return new StorageGate(getStorageFacade());
    }

    protected StorageFacade getStorageFacade() {
        return this.storageFacade;
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
