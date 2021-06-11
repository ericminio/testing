package ericminio.domain;

import ericminio.storage.Repository;

public class StorageFacade {
    private Repository repository;

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public void save(Customer customer) {
        repository.save(customer);
    }

    public Customer find(String name) {
        Customer customer = repository.find(name);
        customer.setStorageFacade(this);
        return customer;
    }
}
