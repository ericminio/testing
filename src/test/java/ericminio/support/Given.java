package ericminio.support;

import ericminio.Context;
import ericminio.domain.Customer;
import ericminio.ports.Visitor;
import ericminio.storage.StorageContext;
import ericminio.storage.VisitorDao;

public class Given {

    private static int target;

    public static Visitor newVisitor(Context context) {
        if (target == Targets.DOMAIN) {
            return new Customer();
        }
        if (target == Targets.STORAGE) {
            StorageContext storageContext = (StorageContext) context;
            VisitorDao visitorDao = new VisitorDao(storageContext.getDatabase());
            return visitorDao.create();
        }
        throw new RuntimeException("unknown target");
    }

    public static void target(int target) {
        Given.target = target;
    }
}
