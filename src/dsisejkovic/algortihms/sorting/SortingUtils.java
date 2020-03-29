package dsisejkovic.algortihms.sorting;

import java.util.Random;

/**
 * Created by dsisejkovic on 18.7.2015..
 */
public class SortingUtils {

    public static void swap(int[] array, int index1, int index2) {
        int tmp = array[index2];
        array[index2] = array[index1];
        array[index1] = tmp;
    }

    public static void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }

        System.out.println();
    }

    public static int[] getTestArray() {
        return new int[]{3, 4, 5, 2, 6, -2, 5, -6, 9, 6, 1, -6};
    }

    public static boolean checkIfSortedAsc(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }

        return true;
    }

    public static int[] getRandomArray(int len) {
        int[] array = new int[len];
        Random rnd = new Random();

        for (int i = 0; i < len; i++) {
            array[i] = rnd.nextInt(10000 + 10000) - 10000;
        }

        return array;
    }
}
