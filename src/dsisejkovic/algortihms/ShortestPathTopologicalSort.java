package dsisejkovic.algortihms;

import java.util.*;

/**
 * Created by dsisejkovic on 17.7.2015..
 */
public class ShortestPathTopologicalSort {
    private Graph graph;

    public ShortestPathTopologicalSort(Graph graph) {
        this.graph = graph;
    }

    public void topologicalSortShortestPath(int source) {
        // perform topological sort
        boolean[] visited = new boolean[graph.getNumOfVertices()];
        Stack<Integer> stack = new Stack<>();

        // O(|E|+|V|)
        for (int i = 0; i < graph.getNumOfVertices(); i++) {
            if (!visited[i]) {
                topologicalSortUtils(i, stack, visited);
            }
        }

        // perform shortest path search!
        int[] distance = new int[graph.getNumOfVertices()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        // pop in topological order
        while (!stack.isEmpty()) {
            int u = stack.pop();

            if (distance[u] != Integer.MAX_VALUE && graph.hasAdjList(u)) {
                for (EdgeNode adjNode : graph.getAdjList(u)) {
                    if (distance[adjNode.getId()] > distance[u] + adjNode.getWeight()) {
                        distance[adjNode.getId()] = distance[u] + adjNode.getWeight();
                    }
                }
            }
        }

        for (int i = 0; i < graph.getNumOfVertices(); i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                System.out.print("INF ");
            } else {
                System.out.println(distance[i] + " ");
            }
        }
    }

    private void topologicalSortUtils(int vertex, Stack<Integer> stack, boolean[] visited) {
        visited[vertex] = true;

        if (graph.hasAdjList(vertex)) {
            for (EdgeNode adjNode : graph.getAdjList(vertex)) {
                if (!visited[adjNode.getId()]) {
                    topologicalSortUtils(adjNode.getId(), stack, visited);
                }
            }
        }

        stack.push(vertex);
    }


    private static class Graph {
        private Map<Integer, List<EdgeNode>> adjMap;
        private int numOfVertices;

        public Graph(int numOfVertices) {
            this.numOfVertices = numOfVertices;
            this.adjMap = new HashMap<>();
        }

        public int getNumOfVertices() {
            return this.numOfVertices;
        }

        public void addEdge(int u, int v, int weight) {
            if (!adjMap.containsKey(u)) {
                adjMap.put(u, new LinkedList<>());
            }

            adjMap.get(u).add(new EdgeNode(v, weight));
        }

        public List<EdgeNode> getAdjList(int u) {
            return adjMap.get(u);
        }
        
        public boolean hasAdjList(int u) {
            return adjMap.containsKey(u);
        }
    }

    public static class EdgeNode {
        private int id;
        private int weight;

        public EdgeNode(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }

        public int getId() {
            return this.id;
        }

        public int getWeight() {
            return this.weight;
        }
    }


    public static void main(String[] args) {
        Graph g = new Graph(6);
        g.addEdge(0, 1, 5);
        g.addEdge(0, 1, 5);
        g.addEdge(0, 2, 3);
        g.addEdge(1, 3, 6);
        g.addEdge(1, 2, 2);
        g.addEdge(2, 4, 4);
        g.addEdge(2, 5, 2);
        g.addEdge(2, 3, 7);
        g.addEdge(3, 4, -1);
        g.addEdge(4, 5, -2);
        
        int source = 1;
        
        ShortestPathTopologicalSort alg = new ShortestPathTopologicalSort(g);
        alg.topologicalSortShortestPath(source);
    }

}
