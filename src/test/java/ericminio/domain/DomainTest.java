package ericminio.domain;

import ericminio.TestContext;
import ericminio.ports.Visitor;

public class DomainTest implements TestContext {

    @Override
    public Visitor newVisitor() {
        return new Customer();
    }
}
