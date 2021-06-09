package com.company;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch<V> extends Search<V> {
    public BreadthFirstSearch(WeightedGraph<V> graph, V source) {
        super(source);
        bfs(graph, source);
    }

    private void bfs(WeightedGraph<V> graph, V current) {
        visited.add(current);
        Queue<V> queue = new LinkedList<>();
        queue.add(current);
        while (!queue.isEmpty()) {
            V v = queue.remove();
            for (Vertex<V> vertex : graph.adjacencyList(v)) {
                if (!visited.contains(vertex.getData())) {
                    visited.add(vertex.getData());
                    edgeTo.put(vertex.getData(), v);
                    queue.add(vertex.getData());
                }
            }
        }
    }
}
