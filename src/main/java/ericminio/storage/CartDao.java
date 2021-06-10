package ericminio.storage;

import ericminio.ports.Cart;

import java.sql.SQLException;

import static java.lang.String.format;

public class CartDao implements Cart {

    private Database database;
    private int customer_id;

    public CartDao(Database database) {
        this.database = database;
    }

    public CartDao of(int customerId) {
        this.customer_id = customerId;
        return this;
    }

    public int size() {
        try {
            return database.selectInt(format("select count(1) from cart where customer_id = %d", customer_id));
        } catch (SQLException e) {
            throw new RuntimeException(format("request failed for values %d", customer_id));
        }
    }

    @Override
    public void add(String label) {
        try {
            int cart_id = database.selectInt("call next value for cart_id_sequence");
            database.execute(format("insert into cart(id, customer_id, label) values(%d, %d, '%s')", cart_id, customer_id, label));
        } catch (SQLException e) {
            throw new RuntimeException(format("creation failed for values %d, %s", customer_id, label), e);
        }
    }
}
