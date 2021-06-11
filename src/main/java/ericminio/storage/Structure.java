package ericminio.storage;

public class Structure {
    private Database database;

    public Structure(Database database) {
        this.database = database;
    }

    public void now() {
        try {
            database.execute("create table customer(id int, name varchar(15))");
            database.execute("create sequence customer_id_sequence");

            database.execute("create table cart(id int, customer_id int, label varchar(15))");
            database.execute("create sequence cart_id_sequence");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
