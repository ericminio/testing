package ericminio;

import ericminio.domain.Customer;

public interface Interactions {

    void save(Customer customer);

    Customer find(String name);

    void recordChoice(Customer customer, String label);
}
