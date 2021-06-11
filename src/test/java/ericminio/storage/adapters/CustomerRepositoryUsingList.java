package ericminio.storage.adapters;

import ericminio.domain.Customer;
import ericminio.storage.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class CustomerRepositoryUsingList implements CustomerRepository {
    private List<Customer> customers;

    public CustomerRepositoryUsingList() {
        customers = new ArrayList<>();
    }

    @Override
    public void save(Customer customer) {
        customers.add(customer);
    }

    @Override
    public Customer find(String name) {
        for (Customer candidate:customers) {
            if (name.equalsIgnoreCase(candidate.getName())) {
                return candidate;
            }
        }
        throw new RuntimeException(format("Customer not found among %d: %s", customers.size(), name));
    }
}
