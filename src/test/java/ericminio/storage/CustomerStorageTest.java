package ericminio.storage;

import ericminio.CustomerTest;
import ericminio.Scope;
import ericminio.storage.support.StorageTest;

public class CustomerStorageTest extends CustomerTest {

    protected Scope scoped() {
        return new StorageTest();
    }
}
