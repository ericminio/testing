package ericminio.ports;

public interface Visitor {

    int getCartSize();

    void chooses(String label);
}
