package ericminio.api;

import ericminio.CustomerTest;
import ericminio.Scope;
import ericminio.api.support.ApiTest;

public class CustomerApiTest extends CustomerTest {

    protected Scope scoped() {
        return new ApiTest();
    }

}
