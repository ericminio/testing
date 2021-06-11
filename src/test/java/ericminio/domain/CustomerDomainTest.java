package ericminio.domain;

import ericminio.Scope;
import ericminio.CustomerTest;

public class CustomerDomainTest extends CustomerTest {

    @Override
    protected Scope scoped() {
        return new DomainTest();
    }
}
