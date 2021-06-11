package ericminio.domain;

public class Customer {
    private ShoppingCart shoppingCart;
    private String name;

    public Customer(String name) {
        this.name = name;
        this.shoppingCart = new ShoppingCart();
    }

    public ShoppingCart getShoppingCart() {
        return this.shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public int getCartSize() {
        return this.shoppingCart.size();
    }

    public void chooses(String label) {
        this.shoppingCart.add(label);
    }

    public String getName() {
        return name;
    }
}
