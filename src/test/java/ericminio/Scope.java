package ericminio;

import ericminio.domain.StorageFacade;

public interface Scope {

    StorageFacade storageFacade();

    Gate gate();
}
