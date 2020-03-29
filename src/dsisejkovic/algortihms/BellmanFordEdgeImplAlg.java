package dsisejkovic.algortihms;

import dsisejkovic.structures.utils.IllegalOperationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Bellman-Ford algorithm for finding shortest path.
 * Time complexity: O(|V|*|E|).
 * Accepts negative weights.
 * Can not work with negative-weight cycles.
 * This implementation uses only edges and only calculates the path length!
 * Created by dsisejkovic on 17.7.2015..
 */
public class BellmanFordEdgeImplAlg {
    private Graph graph;

    public BellmanFordEdgeImplAlg(Graph graph) {
        this.graph = graph;
    }

    public void execute(int source) {
        int[] distance = new int[graph.getNumOfVertices()];
        int[] pred = new int[graph.getNumOfVertices()];

        // indicate initial prev are none (-1)
        Arrays.fill(pred, -1);

        // set distance to all other vertices to INF
        Arrays.fill(distance, Integer.MAX_VALUE);

        // set source vertex distance to 0
        distance[source] = 0;

        // relax all edges |V|-1 times.
        // A simple shortest path from srr to any other vertex can have at-most |V| - 1 edges.
        for (int i = 1; i < graph.getNumOfVertices() - 1; i++) {
            for (int j = 0; j < graph.getNumOfEdges(); j++) {
                int u = graph.getEdge(j).getSource();
                int v = graph.getEdge(j).getDestination();
                int weight = graph.getEdge(j).getWeight();

                if (distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                    pred[v] = u;
                }
            }
        }


        // check for negative cycles
        for (int i = 0; i < graph.getNumOfEdges(); i++) {
            int u = graph.getEdge(i).getSource();
            int v = graph.getEdge(i).getDestination();
            int weight = graph.getEdge(i).getWeight();

            if (distance[u] + weight < distance[v]) {
                throw new IllegalOperationException("Graph contains negative-weighted cycle.");
            }
        }

        printDistance(distance, pred);
    }

    private void printDistance(int[] distance, int[] pred) {
        for (int i = 0; i < distance.length; i++) {
            System.out.format("%d \t\t %d\n", i, distance[i]);
        }

        System.out.println("------------");

        // print out path (reversed)
        int position = pred.length - 1;
        StringBuilder builder = new StringBuilder();
        builder.append(pred.length - 1).append("<-");

        while (position != 0) {
            builder.append(pred[position]).append("<-");
            position = pred[position];
        }

        builder.setLength(builder.length() - 2);
        System.out.println(builder.toString());
    }


    private static class Edge {
        private int source;
        private int destination;
        private int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        public int getSource() {
            return source;
        }

        public int getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }
    }

    private static class Graph {
        private int numOfEdges;
        private int numOfVertices;
        private List<Edge> edgeList;

        public Graph(int numOfEdges, int numOfVertices) {
            this.numOfEdges = numOfEdges;
            this.numOfVertices = numOfVertices;
            this.edgeList = new ArrayList<>();
        }

        public void addEdge(int source, int destination, int weight) {
            Edge edge = new Edge(source, destination, weight);
            edgeList.add(edge);
        }

        public Edge getEdge(int index) {
            return this.edgeList.get(index);
        }

        public int getNumOfEdges() {
            return numOfEdges;
        }

        public int getNumOfVertices() {
            return numOfVertices;
        }

    }


    public static void main(String[] args) {
        Graph graph = new Graph(8, 5);

        graph.addEdge(0, 1, -1);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 4, 2);
        graph.addEdge(3, 2, 5);
        graph.addEdge(3, 1, 1);
        graph.addEdge(4, 3, -3);

        new BellmanFordEdgeImplAlg(graph).execute(0);

    }

}
