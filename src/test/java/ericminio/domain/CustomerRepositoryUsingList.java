package ericminio.domain;

import ericminio.ports.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class CustomerRepositoryUsingList implements CustomerRepository {
    private List<Customer> customers;

    public CustomerRepositoryUsingList() {
        customers = new ArrayList<>();
    }

    @Override
    public void create(Customer customer) {
        customers.add(customer);
    }

    @Override
    public Customer findOneByName(String name) {
        for (Customer candidate:customers) {
            if (name.equalsIgnoreCase(candidate.getName())) {
                return candidate;
            }
        }
        throw new RuntimeException(format("Customer not found among %d: %s", customers.size(), name));
    }
}
