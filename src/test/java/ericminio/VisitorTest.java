package ericminio;

import ericminio.ports.Visitor;
import ericminio.support.Given;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public abstract class VisitorTest {

    protected Context context;

    @Test
    public void startsWithEmptyCart() {
        Visitor alice = Given.newVisitor(context);
        assertThat(alice.getCartSize(), equalTo(0));
    }

    @Test
    public void canAddToCart() {
        Visitor alice = Given.newVisitor(context);
        alice.chooses("this-item");
        assertThat(alice.getCartSize(), equalTo(1));
    }
}
