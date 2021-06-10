package ericminio.storage;

import ericminio.TestContext;
import ericminio.VisitorTest;

public class VisitorStorageTest extends VisitorTest {

    @Override
    protected TestContext getContext() {
        return new StorageTest();
    }
}
