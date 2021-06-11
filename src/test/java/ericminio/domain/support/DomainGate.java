package ericminio.domain.support;

import ericminio.Gate;
import ericminio.domain.Customer;
import ericminio.domain.StorageFacade;

public class DomainGate implements Gate {

    private StorageFacade storageFacade;

    public DomainGate(StorageFacade storageFacade) {
        this.storageFacade = storageFacade;
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
    public void acceptChoice(Customer customer, String label) {
        customer.chooses(label);
    }
}
