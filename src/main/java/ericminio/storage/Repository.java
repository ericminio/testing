package ericminio.storage;

import ericminio.domain.Customer;

public interface Repository {
    void save(Customer customer);

    Customer find(String name);
}
