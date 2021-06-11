package ericminio.domain;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    List<String> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void add(String label) {
        this.items.add(label);
    }

    public int size() {
        return this.items.size();
    }

    public String get(int i) {
        return items.get(i);
    }
}
