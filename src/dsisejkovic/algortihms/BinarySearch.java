package dsisejkovic.algortihms;

import java.util.Arrays;

/**
 * Created by dsisejkovic on 19.7.2015..
 */
public class BinarySearch {

    public boolean execute(int[] array, int element) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int middle = low + (high - low) / 2; //avoids overflow
            if (element < array[middle]) {
                high = middle - 1;
            } else if (element > array[middle]) {
                low = middle + 1;
            } else {
                return true;
            }
        }

        return false;
    }

    public boolean execureRecursive(int[] array, int element) {
        return executeRec(array, element, 0, array.length - 1);
    }

    public boolean executeRec(int[] array, int element, int low, int high) {
        if (low > high) {
            return false;
        }

        int middle = low + (high - low) / 2;
        if (element < array[middle]) {
            return executeRec(array, element, low, middle - 1);
        } else if (element > array[middle]) {
            return executeRec(array, element, middle + 1, high);
        } else {
            return true;
        }
    }


    public static void main(String[] args) {
        int[] array = new int[]{2, 4, 5, 2, 6, 7, 9, 9, 6, 6, 6, 73, 3, 5, 54, 31};

        Arrays.sort(array);

        BinarySearch search = new BinarySearch();
        System.out.println("2: " + search.execute(array, 2));
        System.out.println("5: " + search.execute(array, 5));
        System.out.println("22: " + search.execute(array, 22));
        System.out.println("224: " + search.execute(array, 224));
        System.out.println("73: " + search.execute(array, 73));
        System.out.println("31: " + search.execute(array, 31));


        System.out.println("rec:");
        System.out.println("2: " + search.execureRecursive(array, 2));
        System.out.println("5: " + search.execureRecursive(array, 5));
        System.out.println("22: " + search.execureRecursive(array, 22));
        System.out.println("224: " + search.execureRecursive(array, 224));
        System.out.println("73: " + search.execureRecursive(array, 73));
        System.out.println("31: " + search.execureRecursive(array, 31));

    }
}
