package ericminio;

import ericminio.domain.Whatever;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReadyTest {

    @Test
    public void assertions() {
        assertThat(1+1, equalTo(2));
    }

    @Test
    public void domain() {
        assertThat(new Whatever(), not(nullValue()));
    }
}
