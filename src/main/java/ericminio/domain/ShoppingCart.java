package ericminio.domain;

import ericminio.ports.Cart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements Cart {

    List<String> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    @Override
    public void add(String label) {
        this.items.add(label);
    }

    public int size() {
        return this.items.size();
    }
}
