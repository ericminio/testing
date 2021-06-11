package ericminio.domain;

import ericminio.TestContext;
import ericminio.ports.Visitor;

public class DomainTest implements TestContext {

    @Override
    public Visitor newVisitor(String name) {
        return new Customer(name);
    }
}
