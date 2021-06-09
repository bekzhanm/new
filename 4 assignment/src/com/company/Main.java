package com.company;

public class Main {

    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();
        graph.addEdge("Astana", "Almaty", 0.5);
        graph.addEdge("Astana", "Kyzylorda", 1.5);
        graph.addEdge("Kyzylorda", "Shymkent", 1.5);
        graph.addEdge("Almaty", "Shymkent", 0);
        graph.addEdge("Shymkent", "Taraz", 0.5);
        graph.addEdge("Taraz", "Some", 0.5);
        BreadthFirstSearch<String> breadthFirstSearch = new BreadthFirstSearch<>(graph, "Astana");
        for (String vertex : breadthFirstSearch.pathTo("Some")) {
            System.out.print(vertex + "->");
        }
        System.out.println("It was found");
        DijkstraSearch<String> dijkstraSearch = new DijkstraSearch<>(graph, "Astana");
        for (String vertex : dijkstraSearch.pathTo("Some")) {
            System.out.print(vertex + "->");
        }
        System.out.println("It was found with distance = " + dijkstraSearch.getDistanceTo("Some"));
    }
}
