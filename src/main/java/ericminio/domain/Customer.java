package ericminio.domain;

import ericminio.ports.Visitor;

public class Customer implements Visitor {

    private ShoppingCart shoppingCart;
    private String name;

    public Customer(String name) {
        this.name = name;
        this.shoppingCart = new ShoppingCart();
    }

    @Override
    public int getCartSize() {
        return this.shoppingCart.size();
    }

    @Override
    public void chooses(String label) {
        this.shoppingCart.add(label);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
