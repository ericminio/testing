package ericminio.domain;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    List<String> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public int size() {
        return this.items.size();
    }

    public String get(int i) {
        return items.get(i);
    }

    public void addItem(String label) {
        if (size() == 3) {
            throw new CartLimitReached();
        }
        items.add(label);
    }
}
