package ericminio.ports;

import ericminio.domain.Customer;

public interface CustomerRepository {
    void save(Customer customer);

    Customer find(String name);
}
