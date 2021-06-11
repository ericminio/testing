package ericminio.storage.support;

import ericminio.domain.StorageFacade;
import ericminio.domain.support.DomainGate;

public class StorageGate extends DomainGate {

    public StorageGate(StorageFacade storageFacade) {
        this.storageFacade = storageFacade;
    }
}
