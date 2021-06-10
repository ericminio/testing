package ericminio;

import ericminio.ports.Visitor;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public abstract class VisitorTest {

    Visitor alice;

    protected abstract TestContext getContext();

    @Before
    public void newVisitor() {
        alice = getContext().newVisitor();
    }

    @Test
    public void startsWithEmptyCart() {
        assertThat(alice.getCartSize(), equalTo(0));
    }

    @Test
    public void canAddToCart() {
        alice.chooses("this-item");
        assertThat(alice.getCartSize(), equalTo(1));
    }
}
