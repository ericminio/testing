package ericminio.domain;

public class Customer {
    private String name;
    private Cart cart;
    private StorageFacade storageFacade;

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
        if (getCartSize() == 3) {
            throw new CartLimitReached();
        }
        this.cart.add(label);
        if (this.storageFacade != null) {
            this.storageFacade.save(this);
        }
    }

    public void setStorageFacade(StorageFacade storageFacade) {
        this.storageFacade = storageFacade;
    }
}
