package ericminio.domain;

import ericminio.TestContext;

public class DomainTest implements TestContext {

    @Override
    public Customers getCustomers() {
        Customers customers = new Customers();
        customers.setCustomerRepository(new CustomerRepositoryUsingList());
        customers.setCartRepository(new CartRepositoryUsingMap());
        return customers;
    }
}
