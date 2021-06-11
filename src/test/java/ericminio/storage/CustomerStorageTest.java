package ericminio.storage;

import ericminio.Scope;
import ericminio.CustomerTest;
import ericminio.storage.support.StorageTest;

public class CustomerStorageTest extends CustomerTest {

    @Override
    protected Scope scoped() {
        return new StorageTest();
    }
}
