package ericminio.ports;

import ericminio.domain.Customer;
import ericminio.domain.ShoppingCart;

public interface CartRepository {
    void save(Customer customer);

    ShoppingCart find(Customer customer);
}
