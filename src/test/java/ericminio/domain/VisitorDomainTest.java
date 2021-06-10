package ericminio.domain;

import ericminio.TestContext;
import ericminio.VisitorTest;

public class VisitorDomainTest extends VisitorTest {

    @Override
    protected TestContext getContext() {
        return new DomainTest();
    }
}
