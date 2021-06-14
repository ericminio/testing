package ericminio.domain;

public interface Repository {
    void save(Customer customer);

    Customer find(String name);
}
