package ericminio.domain;

import ericminio.ports.CartRepository;
import ericminio.ports.CustomerRepository;

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
        customerRepository.create(customer);
        cartRepository.save(customer);
    }

    public Customer findByName(String name) {
        Customer customer = customerRepository.findOneByName(name);
        ShoppingCart shoppingCart = cartRepository.findByCustomer(customer);
        customer.setShoppingCart(shoppingCart);

        return customer;
    }
}
