package ericminio.domain;

import ericminio.ports.Visitor;

public class Customer implements Visitor {

    private ShoppingCart shoppingCart;

    public Customer() {
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
}
