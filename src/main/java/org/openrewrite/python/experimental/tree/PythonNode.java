package org.openrewrite.python.experimental.tree;

import org.openrewrite.python.experimental.PythonVisitor;

public interface PythonNode {
    void accept(PythonVisitor visitor);
}
