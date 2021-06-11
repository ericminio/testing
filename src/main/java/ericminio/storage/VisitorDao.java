package ericminio.storage;

import ericminio.ports.Visitor;

public class VisitorDao implements Visitor {

    private Database database;
    private int id;
    private String name;

    public VisitorDao(Database database) {
        this.database = database;
    }

    @Override
    public int getCartSize() {
        return new CartDao(database).belongingTo(this).size();
    }

    @Override
    public void chooses(String label) {
        new CartDao(database).belongingTo(this).add(label);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
