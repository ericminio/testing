package ericminio.domain;

import ericminio.storage.CartRepository;
import ericminio.storage.CustomerRepository;

public class Customers {
    private CustomerRepository customerRepository;
    private CartRepository cartRepository;

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
        cartRepository.save(customer);
    }

    public Customer find(String name) {
        Customer customer = customerRepository.find(name);
        Cart cart = cartRepository.find(customer);
        customer.setCart(cart);
        return customer;
    }
}
