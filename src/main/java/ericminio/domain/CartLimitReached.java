package ericminio.domain;

public class CartLimitReached extends RuntimeException {

    public CartLimitReached() {
        super("cart size limit reached");
    }
}
