package ericminio.storage;

import ericminio.domain.Cart;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Carts {
    private Database database;

    public Carts(Database database) {
        this.database = database;
    }

    public void recreate(Cart cart, int customer_id) throws SQLException {
        database.execute("delete from cart where customer_id = ?", new Object[] {customer_id});
        for (int i = 0; i< cart.size(); i++) {
            String label = cart.get(i);
            int cart_id = database.selectInt("call next value for cart_id_sequence", new Object[] {});
            database.execute("insert into cart(id, customer_id, label) values(?, ?, ?)", new Object[] {cart_id, customer_id, label});
        }
    }

    public Cart find(int customer_id) throws SQLException {
        Cart cart = new Cart();
        ResultSet resultSet = database.selectRows("select label from cart where customer_id = ?", new Object[] {customer_id});
        while (resultSet.next()) {
            cart.addItem(resultSet.getString(1));
        }
        return cart;
    }
}
