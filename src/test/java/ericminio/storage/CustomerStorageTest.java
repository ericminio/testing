package ericminio.storage;

import ericminio.Scope;
import ericminio.CustomerTest;

public class CustomerStorageTest extends CustomerTest {

    @Override
    protected Scope scoped() {
        return new StorageTest();
    }
}
