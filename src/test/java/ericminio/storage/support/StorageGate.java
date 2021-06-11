package ericminio.storage.support;

import ericminio.domain.StorageFacade;
import ericminio.domain.support.DomainGate;
import ericminio.storage.adapters.Database;
import ericminio.storage.adapters.RepositoryUsingDatabase;

public class StorageGate extends DomainGate {

    public StorageGate(Database database) {
        StorageFacade storageFacade = new StorageFacade();
        storageFacade.setRepository(new RepositoryUsingDatabase(database));
        this.setStorageFacade(storageFacade);
    }
}
