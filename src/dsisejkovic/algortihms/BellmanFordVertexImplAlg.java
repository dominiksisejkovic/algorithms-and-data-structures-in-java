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
 * This implementation uses only vertices and can print out the path.
 * Created by dsisejkovic on 17.7.2015..
 */
public class BellmanFordVertexImplAlg {
    private int[][] weightMatrix;
    private int numOfVertices;

    public BellmanFordVertexImplAlg(int[][] weightMatrix) {
        this.weightMatrix = weightMatrix;
        this.numOfVertices = weightMatrix.length;
    }

    public void execute(int source) {
        int[] distance = new int[numOfVertices];
        int[] pred = new int[numOfVertices];

        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(pred, -1);

        distance[source] = 0;

        for (int i = 1; i < numOfVertices - 1; i++) {
            for (int j = 0; j < numOfVertices; j++) {
                for (int k : getAdjacent(j)) {
                    if (distance[j] + weightMatrix[j][k] < distance[k]) {
                        distance[k] = distance[j] + weightMatrix[j][k];
                        pred[k] = j;
                    }
                }
            }
        }

        // check negative-weighted cycles
        for (int i = 0; i < numOfVertices; i++) {
            for (int j = 0; j < numOfVertices; j++) {
                if (distance[i] + weightMatrix[i][j] < distance[j]) {
                    throw new IllegalOperationException("Negative-weighted cycle was found.");
                }
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

    private List<Integer> getAdjacent(int index) {
        List<Integer> adjacent = new ArrayList<>(numOfVertices);
        for (int i = 0; i < numOfVertices; i++) {
            if (weightMatrix[index][i] != Integer.MAX_VALUE) {
                adjacent.add(i);
            }
        }

        return adjacent;
    }


}
