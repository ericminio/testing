package ericminio.storage;

import ericminio.domain.Customer;
import ericminio.domain.ShoppingCart;
import ericminio.ports.CartRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.String.format;

public class CartRepositoryUsingDatabase implements CartRepository {
    private Database database;

    public CartRepositoryUsingDatabase(Database database) {
        this.database = database;
    }

    @Override
    public void save(Customer customer) {
        try {
            int customer_id = database.selectInt(format("select id from customer where name = '%s'", customer.getName()));
            ShoppingCart shoppingCart = customer.getShoppingCart();
            for (int i=0; i<shoppingCart.size(); i++) {
                String label = shoppingCart.get(i);
                int cart_id = database.selectInt("call next value for cart_id_sequence");
                database.execute(format("insert into cart(id, customer_id, label) values(%d, %d, '%s')", cart_id, customer_id, label));
            }
        } catch (SQLException e) {
            throw new RuntimeException("cart creation failed", e);
        }
    }

    @Override
    public ShoppingCart find(Customer customer) {
        try {
            ShoppingCart shoppingCart = new ShoppingCart();
            ResultSet resultSet = database.selectRows(format("" +
                    "select label " +
                    "from cart, customer " +
                    "where cart.customer_id = customer.id " +
                    "   and customer.name = '%s'", customer.getName()));
            while (resultSet.next()) {
                shoppingCart.add(resultSet.getString(1));
            }
            return shoppingCart;
        } catch (SQLException e) {
            throw new RuntimeException("cart select failed", e);
        }
    }
}
