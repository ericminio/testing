package ericminio.storage;

public class Drop {
    private Database database;

    public Drop(Database database) {
        this.database = database;
    }

    public void now() {
        database.executeIgnoringErrors("drop table customer");
        database.executeIgnoringErrors("drop table cart");
        database.executeIgnoringErrors("drop sequence customer_id_sequence");
        database.executeIgnoringErrors("drop sequence cart_id_sequence");
    }
}
