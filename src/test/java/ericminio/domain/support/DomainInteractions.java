package ericminio.domain.support;

import ericminio.Interactions;
import ericminio.domain.Customer;
import ericminio.domain.StorageFacade;
import ericminio.storage.adapters.RepositoryUsingMap;

public class DomainInteractions implements Interactions {

    protected StorageFacade storageFacade;

    public DomainInteractions() {
        StorageFacade storageFacade = new StorageFacade();
        storageFacade.setRepository(new RepositoryUsingMap());
        this.setStorageFacade(storageFacade);
    }

    public void setStorageFacade(StorageFacade storageFacade) {
        this.storageFacade = storageFacade;
    }
    public StorageFacade getStorageFacade() {
        return this.storageFacade;
    }

    @Override
    public void save(Customer customer) {
        storageFacade.save(customer);
    }

    @Override
    public Customer find(String name) {
        return storageFacade.find(name);
    }

    @Override
    public void recordChoice(Customer customer, String label) {
        customer.chooses(label);
    }
}
