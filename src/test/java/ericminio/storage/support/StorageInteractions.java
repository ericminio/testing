package ericminio.storage.support;

import ericminio.domain.StorageFacade;
import ericminio.domain.support.DomainInteractions;
import ericminio.storage.adapters.Database;
import ericminio.storage.adapters.RepositoryUsingDatabase;

public class StorageInteractions extends DomainInteractions {

    public StorageInteractions(Database database) {
        StorageFacade storageFacade = new StorageFacade();
        storageFacade.setRepository(new RepositoryUsingDatabase(database));
        this.setStorageFacade(storageFacade);
    }
}
