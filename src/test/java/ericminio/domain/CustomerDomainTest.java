package ericminio.domain;

import ericminio.Scope;
import ericminio.CustomerTest;
import ericminio.domain.support.DomainTest;

public class CustomerDomainTest extends CustomerTest {

    @Override
    protected Scope scoped() {
        return new DomainTest();
    }
}
