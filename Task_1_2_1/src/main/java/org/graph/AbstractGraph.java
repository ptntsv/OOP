package org.graph;

import java.util.ArrayList;

/**
 * Abstract class that represents integer graph.
 */
public abstract class AbstractGraph<T> implements IGraph<T> {

    protected VerticesMapsPair<T> maps = new VerticesMapsPair<>();

    public AbstractGraph() {
    }
}
