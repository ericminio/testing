package ericminio.domain;

import ericminio.ports.CartRepository;

import java.util.HashMap;
import java.util.Map;

public class CartRepositoryUsingMap implements CartRepository {
    private Map<Customer, ShoppingCart> carts;

    public CartRepositoryUsingMap() {
        carts = new HashMap<>();
    }

    @Override
    public void save(Customer customer) {
        carts.put(customer, customer.getShoppingCart());
    }

    @Override
    public ShoppingCart findByCustomer(Customer customer) {
        return carts.get(customer);
    }
}
