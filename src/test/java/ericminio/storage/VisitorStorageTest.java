package ericminio.storage;

import ericminio.VisitorTest;
import ericminio.support.Given;
import org.junit.Before;

import static ericminio.support.Targets.STORAGE;

public class VisitorStorageTest extends VisitorTest {

    @Before
    public void target() {
        Given.target(STORAGE);
    }

    @Before
    public void clean() throws Exception {
        StorageContext storageContext = new StorageContext();
        storageContext.clean();
        this.context = storageContext;
    }
}
