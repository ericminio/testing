package ericminio.domain;

import ericminio.Scope;

public class DomainTest implements Scope {

    @Override
    public Customers customers() {
        Customers customers = new Customers();
        customers.setCustomerRepository(new CustomerRepositoryUsingList());
        customers.setCartRepository(new CartRepositoryUsingMap());
        return customers;
    }
}
