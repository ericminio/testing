package ericminio.storage;

import java.sql.SQLException;

import static java.lang.String.format;

public class VisitorRepository {

    private Database database;

    public VisitorRepository(Database database) {
        this.database = database;
    }

    public int createNew() {
        try {
            int id = database.selectInt("call next value for customer_id_sequence");
            String name = "name-" + id;
            database.execute(format("insert into customer(id, name) values(%d, '%s')", id, name));
            return id;
        } catch (SQLException e) {
            throw new RuntimeException("creation failed", e);
        }
    }

    public VisitorDao findBy(int id) {
        try {
            String name = database.selectString(format("select name from customer where id = %d", id));
            VisitorDao visitorDao = new VisitorDao(database);
            visitorDao.setId(id);
            visitorDao.setName(name);
            return visitorDao;
        } catch (SQLException e) {
            throw new RuntimeException("select failed", e);
        }
    }
}
