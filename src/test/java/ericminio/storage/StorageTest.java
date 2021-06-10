package ericminio.storage;

import ericminio.TestContext;
import ericminio.ports.Visitor;

import java.sql.Connection;
import java.sql.DriverManager;

public class StorageTest implements TestContext {

    private Database database;

    public StorageTest() {
        clean();
    }

    @Override
    public Visitor newVisitor() {
        VisitorDao visitorDao = new VisitorDao(this.getDatabase());
        return visitorDao.create();
    }

    private Database getDatabase() {
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

    private void clean() {
        try {
            getDatabase().executeIgnoringErrors("drop table customer");
            getDatabase().executeIgnoringErrors("drop table cart");
            getDatabase().executeIgnoringErrors("drop sequence customer_id_sequence");
            getDatabase().executeIgnoringErrors("drop sequence cart_id_sequence");

            getDatabase().execute("create table customer(id int, name varchar(15))");
            getDatabase().execute("create sequence customer_id_sequence");

            getDatabase().execute("create table cart(id int, customer_id int, label varchar(15))");
            getDatabase().execute("create sequence cart_id_sequence");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
