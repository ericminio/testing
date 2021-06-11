package ericminio.storage.adapters;

import ericminio.domain.Customer;
import ericminio.storage.Repository;

import java.util.HashMap;
import java.util.Map;

public class RepositoryUsingMap implements Repository {

    private Map<String, Customer> data;

    public RepositoryUsingMap() {
        data = new HashMap<>();
    }

    @Override
    public void save(Customer customer) {
        data.put(customer.getName(), customer);
    }

    @Override
    public Customer find(String name) {
        return data.get(name);
    }
}
