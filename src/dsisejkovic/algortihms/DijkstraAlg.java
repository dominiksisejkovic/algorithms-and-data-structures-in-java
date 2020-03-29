package dsisejkovic.algortihms;

import java.util.*;

/**
 * Dijkstra's shortest path algorithm.
 * Can not work with negative weights.
 * O(|V|^2)
 * Created by dsisejkovic on 17.7.2015..
 */
public class DijkstraAlg {
    private int[][] weightMatrix;
    private int numOfVertices;

    public DijkstraAlg(int[][] weightMatrix) {
        this.weightMatrix = weightMatrix;
        this.numOfVertices = weightMatrix.length;
    }

    public void execute(int source) {
        // init distance array
        int[] distance = new int[numOfVertices];
        Arrays.fill(distance, Integer.MAX_VALUE);

        // init predecessor array
        int[] pred = new int[numOfVertices];
        Arrays.fill(pred, -1);

        // used to mark visited nodes
        boolean[] visited = new boolean[numOfVertices];

        // set source distance to 0
        distance[source] = 0;

        // repeat numOfVertices times
        // can be replaced with toBeChecked set (add all to toBeChecked initially)
        for (int i = 0; i < numOfVertices - 1; i++) {
            int u = getMin(distance, visited);
            visited[u] = true;

            for (int j = 0; j < numOfVertices; j++) {
                if (!visited[j] && weightMatrix[u][j] != 0 && distance[u] != Integer.MAX_VALUE
                        && (distance[u] + weightMatrix[u][j] < distance[j])) {
                    distance[j] = distance[u] + weightMatrix[u][j];
                    pred[j] = u;
                }
            }
        }

        printDistance(distance, pred);
    }

    // takes O(|V|) time.
    private int getMin(int[] distance, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int vertex = 0;

        for (int i = 1; i < distance.length; i++) {
            if (!visited[i] && distance[i] < min) {
                min = distance[i];
                vertex = i;
            }
        }

        return vertex;
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

        while (position > 0) {
            builder.append(pred[position]).append("<-");
            position = pred[position];
        }

        builder.setLength(builder.length() - 2);
        System.out.println(builder.toString());
    }


    public static void main(String[] args) {
        int[][] graph = {{0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 0, 10, 0, 2, 0, 0},
                {0, 0, 0, 14, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        new DijkstraAlg(graph).execute(0);
    }
}
