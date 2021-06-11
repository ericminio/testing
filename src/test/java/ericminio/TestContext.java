package ericminio;

import ericminio.ports.Visitor;

public interface TestContext {

    Visitor newVisitor(String name);
}
