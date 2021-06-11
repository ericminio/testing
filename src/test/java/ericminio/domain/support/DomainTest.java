package ericminio.domain.support;

import ericminio.Interactions;
import ericminio.Scope;

public class DomainTest implements Scope {

    @Override
    public Interactions interactions() {
        return new DomainInteractions();
    }
}
