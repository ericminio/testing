package ericminio.domain;

public class Customer {
    private String name;
    private Cart cart;

    public Customer(String name) {
        this.name = name;
        this.cart = new Cart();
    }

    public String getName() {
        return name;
    }

    public Cart getCart() {
        return this.cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getCartSize() {
        return this.cart.size();
    }

    public void chooses(String label) {
        this.cart.add(label);
    }
}
