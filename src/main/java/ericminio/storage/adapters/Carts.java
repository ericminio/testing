package ericminio.storage.adapters;

import ericminio.domain.Cart;

import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.String.format;

public class Carts {
    private Database database;

    public Carts(Database database) {
        this.database = database;
    }

    public void recreate(Cart cart, int customer_id) throws SQLException {
        database.execute(format("delete from cart where customer_id = %d", customer_id));
        for (int i = 0; i< cart.size(); i++) {
            String label = cart.get(i);
            int cart_id = database.selectInt("call next value for cart_id_sequence");
            database.execute(format("insert into cart(id, customer_id, label) values(%d, %d, '%s')", cart_id, customer_id, label));
        }
    }

    public Cart find(int customer_id) throws SQLException {
        Cart cart = new Cart();
        ResultSet resultSet = database.selectRows(format("select label from cart where customer_id = %d", customer_id));
        while (resultSet.next()) {
            cart.addItem(resultSet.getString(1));
        }
        return cart;
    }
}
