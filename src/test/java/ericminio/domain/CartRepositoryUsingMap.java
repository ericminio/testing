package ericminio.domain;

import ericminio.ports.CartRepository;

import java.util.HashMap;
import java.util.Map;

public class CartRepositoryUsingMap implements CartRepository {
    private Map<Customer, Cart> carts;

    public CartRepositoryUsingMap() {
        carts = new HashMap<>();
    }

    @Override
    public void save(Customer customer) {
        carts.put(customer, customer.getCart());
    }

    @Override
    public Cart find(Customer customer) {
        return carts.get(customer);
    }
}
