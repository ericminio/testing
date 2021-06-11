package ericminio.storage.support;

import ericminio.storage.adapters.Database;

public class DropAll {

    public static void now(Database database) {
        database.executeIgnoringErrors("drop table customer");
        database.executeIgnoringErrors("drop table cart");
        database.executeIgnoringErrors("drop sequence customer_id_sequence");
        database.executeIgnoringErrors("drop sequence cart_id_sequence");
    }
}
