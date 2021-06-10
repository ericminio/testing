package ericminio.storage;

import ericminio.ports.Visitor;

import java.sql.SQLException;

import static java.lang.String.format;

public class VisitorDao implements Visitor {

    private Database database;
    private int id;
    private String name;

    public VisitorDao(Database database) {
        this.database = database;
    }

    public Visitor create() {
        try {
            this.id = database.selectInt("call next value for customer_id_sequence");
            this.name = "name-" + id;
            database.execute(format("insert into customer(id, name) values(%d, '%s')", id, name));
            return this;
        } catch (SQLException e) {
            throw new RuntimeException("creation failed", e);
        }
    }

    @Override
    public int getCartSize() {
        return new CartDao(database).of(id).size();
    }

    @Override
    public void chooses(String label) {
        new CartDao(database).of(id).add(label);
    }
}
