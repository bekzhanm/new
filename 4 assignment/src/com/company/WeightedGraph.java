package com.company;

import java.util.*;

public class WeightedGraph<V> {
    private final boolean undirected;
    private final List<Vertex<V>> vertices = new LinkedList<>();

    public WeightedGraph() {
        undirected = true;
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(V vertex) {
        addVertex(new Vertex<>(vertex));
    }

    private void addVertex(Vertex<V> vertex) {
        vertices.add(vertex);
    }

    public Vertex<V> getVertex(V searchVertex) {
        Vertex<V> result = null;
        for (Vertex<V> vertex : vertices)
            if (vertex.getData() == searchVertex)
                result = vertex;
        return result;
    }

    public void addEdge(V source, V dest, double weight) {
        Vertex<V> sourceVertex = getVertex(source);
        Vertex<V> destVertex = getVertex(dest);
        if (sourceVertex == null) {
            sourceVertex = new Vertex<>(source);
            addVertex(sourceVertex);
        }
        if (destVertex == null) {
            destVertex = new Vertex<>(dest);
            addVertex(destVertex);
        }
        sourceVertex.addAdjacentVertex(destVertex, weight);
        if (undirected) destVertex.addAdjacentVertex(sourceVertex, weight);
    }

    public int getVerticesCount() {
        return vertices.size();
    }

    public int getEdgesCount() {
        int count = 0;
        for (Vertex<V> vertex : vertices)
            count += vertex.getAdjacentVertices().size();
        if (undirected) count /= 2;
        return count;
    }

    public boolean hasVertex(V vertex) {
        return getVertex(vertex) != null;
    }

    public boolean hasEdge(V source, V dest) {
        Vertex<V> sourceVertex = getVertex(source);
        Vertex<V> destVertex = getVertex(dest);
        if (sourceVertex == null || destVertex == null) return false;
        return sourceVertex.getAdjacentVertices().containsKey(destVertex);
    }

    public Iterable<Vertex<V>> adjacencyList(V vertex) {
        Vertex<V> currentVertex = getVertex(vertex);
        if (currentVertex == null) return null;
        return currentVertex.getAdjacentVertices().keySet();
    }
}