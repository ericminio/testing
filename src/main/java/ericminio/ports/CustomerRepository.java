package ericminio.ports;

import ericminio.domain.Customer;

public interface CustomerRepository {
    void create(Customer customer);

    Customer findOneByName(String name);
}
