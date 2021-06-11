package ericminio.storage;

import ericminio.ports.Cart;

public class CartDao implements Cart {

    private final CartRepository cartRepository;
    private VisitorDao visitor;

    public CartDao(Database database) {
        this.cartRepository = new CartRepository(database);
    }

    public CartDao belongingTo(VisitorDao visitorDao) {
        this.visitor = visitorDao;
        return this;
    }

    public int size() {
        return this.cartRepository.selectCount(visitor.getId());
    }

    @Override
    public void add(String label) {
        this.cartRepository.createNew(visitor.getId(), label);
    }
}
