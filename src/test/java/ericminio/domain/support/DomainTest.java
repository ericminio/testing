package ericminio.domain.support;

import ericminio.Scope;
import ericminio.domain.StorageFacade;
import ericminio.storage.adapters.RepositoryUsingMap;

public class DomainTest implements Scope {

    @Override
    public StorageFacade storageFacade() {
        StorageFacade storageFacade = new StorageFacade();
        storageFacade.setRepository(new RepositoryUsingMap());
        return storageFacade;
    }
}
