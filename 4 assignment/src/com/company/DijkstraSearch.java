package com.company;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DijkstraSearch<V> extends Search<V> {
    private final Set<V> unsettledNodes;
    private final Map<V, Double> distances;
    private final WeightedGraph<V> graph;

    public DijkstraSearch(WeightedGraph<V> graph, V source) {
        super(source);
        unsettledNodes = new HashSet<>();
        distances = new HashMap<>();
        this.graph = graph;
        dijkstra();
    }

    public void dijkstra() {
        distances.put(source, 0D);
        unsettledNodes.add(source);

        while (unsettledNodes.size() > 0) {
            V node = getVertexWithMinimumWeight(unsettledNodes);
            visited.add(node);
            unsettledNodes.remove(node);
            for (Vertex<V> target : graph.adjacencyList(node)) {
                if (getShortestDistance(target.getData()) > getShortestDistance(node)
                        + getDistance(node, target.getData())) {
                    distances.put(target.getData(), getShortestDistance(node)
                            + getDistance(node, target.getData()));
                    edgeTo.put(target.getData(), node);
                    unsettledNodes.add(target.getData());
                }
            }
        }
    }

    private double getDistance(V node, V target) {
        Vertex<V> vertex = graph.getVertex(node);
        Map<Vertex<V>, Double> adjacentVertex = vertex.getAdjacentVertices();
        for (Vertex<V> adjacent : adjacentVertex.keySet()) {
            if (adjacent.getData() == target) {
                return adjacentVertex.get(adjacent);
            }
        }
        throw new RuntimeException("Not found!");
    }

    private V getVertexWithMinimumWeight(Set<V> vertices) {
        V minimum = null;
        for (V vertex : vertices) {
            if (minimum == null)
                minimum = vertex;
            else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum))
                    minimum = vertex;
            }
        }
        return minimum;
    }

    private double getShortestDistance(V destination) {
        Double d = distances.get(destination);
        return d == null ? Double.MAX_VALUE : d;
    }

    public Double getDistanceTo(V vertex) {
        return distances.get(vertex);
    }
}
