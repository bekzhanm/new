package com.company;

import java.util.*;

public class Search<V> {
    protected Set<V> visited;
    protected Map<V, V> edgeTo;
    protected final V source;

    public Search(V source) {
        this.source = source;
        visited = new HashSet<>();
        edgeTo = new HashMap<>();
    }

    public boolean hasPathTo(V v) {
        return visited.contains(v);
    }

    public Iterable<V> pathTo(V v) {
        if (!hasPathTo(v)) return null;
        LinkedList<V> path = new LinkedList<>();
        for (V i = v; i != source; i = edgeTo.get(i))
            path.push(i);
        path.push(source);

        return path;
    }
}
