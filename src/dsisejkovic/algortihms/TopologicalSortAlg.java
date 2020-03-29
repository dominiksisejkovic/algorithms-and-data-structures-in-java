package dsisejkovic.algortihms;

import java.util.*;

/**
 * Created by dsisejkovic on 17.7.2015..
 */
public class TopologicalSortAlg {
    private Graph graph;


    public TopologicalSortAlg(Graph graph) {
        this.graph = graph;
    }

    public void execute() {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visitedSet = new HashSet<>();

        for (int i = 0; i < graph.getNumOfVertices(); i++) {
            if (!visitedSet.contains(i)) {
                topologicalSort(i, visitedSet, stack);
            }
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    // DFS
    private void topologicalSort(int vertex, Set<Integer> visitedSet, Stack<Integer> stack) {
        visitedSet.add(vertex);

        for (int adjVertex : graph.getAdjacencyList(vertex)) {
            if (!visitedSet.contains(adjVertex)) {
                topologicalSort(adjVertex, visitedSet, stack);
            }
        }

        stack.push(vertex);
    }

    private static class Graph {
        private Map<Integer, List<Integer>> adjMap;
        private int numOfVertices;

        public Graph(int numOfVertices) {
            this.adjMap = new HashMap<>();
            this.numOfVertices = numOfVertices;
        }

        public int getNumOfVertices() {
            return this.numOfVertices;
        }

        public void addEdge(int u, int w) {
            if (!adjMap.containsKey(u)) {
                adjMap.put(u, new ArrayList<>());
            }

            adjMap.get(u).add(w);
        }

        public List<Integer> getAdjacencyList(int u) {
            return adjMap.containsKey(u) ? adjMap.get(u) : new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        new TopologicalSortAlg(g).execute();
    }
}
