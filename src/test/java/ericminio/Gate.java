package ericminio;

import ericminio.domain.Customer;

public interface Gate {
    void save(Customer customer);

    Customer find(String name);

    void acceptChoice(Customer alice, String label);
}
