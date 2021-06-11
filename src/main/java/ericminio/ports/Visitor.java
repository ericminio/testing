package ericminio.ports;

public interface Visitor {

    String getName();

    int getCartSize();

    void chooses(String label);
}
