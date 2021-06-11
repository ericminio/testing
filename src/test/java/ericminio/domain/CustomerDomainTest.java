package ericminio.domain;

import ericminio.CustomerTest;
import ericminio.Scope;
import ericminio.domain.support.DomainTest;

public class CustomerDomainTest extends CustomerTest {

    protected Scope scoped() {
        return new DomainTest();
    }
}
