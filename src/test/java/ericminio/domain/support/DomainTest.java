package ericminio.domain.support;

import ericminio.Gate;
import ericminio.Scope;

public class DomainTest implements Scope {

    @Override
    public Gate gate() {
        return new DomainGate();
    }
}
