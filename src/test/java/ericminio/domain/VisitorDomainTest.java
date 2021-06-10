package ericminio.domain;

import ericminio.VisitorTest;
import ericminio.support.Given;
import org.junit.Before;

import static ericminio.support.Targets.DOMAIN;

public class VisitorDomainTest extends VisitorTest {

    @Before
    public void target() {
        Given.target(DOMAIN);
    }
}
