package ericminio.domain.support;

import ericminio.Scope;
import ericminio.domain.Customers;
import ericminio.storage.adapters.CartRepositoryUsingMap;
import ericminio.storage.adapters.CustomerRepositoryUsingList;

public class DomainTest implements Scope {

    @Override
    public Customers customers() {
        Customers customers = new Customers();
        customers.setCustomerRepository(new CustomerRepositoryUsingList());
        customers.setCartRepository(new CartRepositoryUsingMap());
        return customers;
    }
}
