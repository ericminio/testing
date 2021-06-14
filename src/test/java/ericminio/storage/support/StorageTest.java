package ericminio.storage.support;

import ericminio.Interactions;
import ericminio.Scope;
import ericminio.storage.Database;
import ericminio.storage.migrations.CreateAll;

import java.sql.Connection;
import java.sql.DriverManager;

public class StorageTest implements Scope {

    private Database database;

    public StorageTest() {
        DropAll.now(inMemoryDatabase());
        CreateAll.now(inMemoryDatabase());
    }

    @Override
    public Interactions interactions() {
        return new StorageInteractions(inMemoryDatabase());
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
