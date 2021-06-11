package ericminio.ports;

import ericminio.domain.Customer;
import ericminio.domain.Cart;

public interface CartRepository {
    void save(Customer customer);

    Cart find(Customer customer);
}
