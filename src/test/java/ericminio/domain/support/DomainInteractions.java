package ericminio.domain.support;

import ericminio.Interactions;
import ericminio.domain.Customer;

public class DomainInteractions implements Interactions {
    protected Customer customer;

    @Override
    public void save(Customer customer) {
        this.customer = customer;
    }

    @Override
    public Customer find(String name) {
        return customer;
    }

    @Override
    public void recordChoice(Customer customer, String label) {
        customer.chooses(label);
    }
}
