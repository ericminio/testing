package ericminio.domain.support;

import ericminio.Gate;
import ericminio.domain.Customer;
import ericminio.domain.StorageFacade;
import ericminio.storage.adapters.RepositoryUsingMap;

public class DomainGate implements Gate {

    protected StorageFacade storageFacade;

    public DomainGate() {
        this.storageFacade = new StorageFacade();
        storageFacade.setRepository(new RepositoryUsingMap());
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
