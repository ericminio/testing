package ericminio.storage;

import java.sql.SQLException;

import static java.lang.String.format;

public class CartRepository {

    private Database database;

    public CartRepository(Database database) {
        this.database = database;
    }

    public int createNew(int customer_id, String label) {
        try {
            int cart_id = database.selectInt("call next value for cart_id_sequence");
            database.execute(format("insert into cart(id, customer_id, label) values(%d, %d, '%s')", cart_id, customer_id, label));
            return cart_id;
        } catch (SQLException e) {
            throw new RuntimeException(format("creation failed for values %d, %s", customer_id, label), e);
        }
    }

    public int selectCount(int customer_id) {
        try {
            return database.selectInt(format("select count(1) from cart where customer_id = %d", customer_id));
        } catch (SQLException e) {
            throw new RuntimeException(format("request failed for values %d", customer_id));
        }
    }
}
