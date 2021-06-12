package ericminio.storage.support;

import ericminio.Interactions;
import ericminio.domain.Customer;
import ericminio.storage.adapters.Database;
import ericminio.storage.adapters.RepositoryUsingDatabase;

public class StorageInteractions implements Interactions {
    private final RepositoryUsingDatabase repositoryUsingDatabase;

    public StorageInteractions(Database database) {
        this.repositoryUsingDatabase = new RepositoryUsingDatabase(database);
    }

    @Override
    public void save(Customer customer) {
        repositoryUsingDatabase.save(customer);
    }

    @Override
    public Customer find(String name) {
        return repositoryUsingDatabase.find(name);
    }

    @Override
    public void recordChoice(Customer customer, String label) {
        customer.getCart().addItem(label);
        repositoryUsingDatabase.save(customer);
    }
}
