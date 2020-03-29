package dsisejkovic.algortihms.sorting.efficient;

import dsisejkovic.algortihms.sorting.SortingUtils;
import dsisejkovic.algortihms.sorting.simple.InsertionSort;

/**
 * Created by dsisejkovic on 19.7.2015..
 */
public class Quicksort {
    private InsertionSort insertionSort = new InsertionSort();

    public void execute(int[] array) {
        if (array.length > 10) {
            quicksort(array, 0, array.length - 1);
        } else {
            insertionSort.execute(array);
        }
    }


    private void quicksort(int[] array, int low, int high) {
        if (array.length < 2) {
            return;
        }

        // Get the pivot element from the middle of the list
        int i = low;
        int j = high;
        int pivot = array[low + (high - low) / 2];

        while (i <= j) {
            while (array[i] < pivot) {
                i++;
            }

            while (array[j] > pivot) {
                j--;
            }

            if (i <= j) {
                SortingUtils.swap(array, i, j);
                i++;
                j--;
            }
        }

        if (low < j) {
            quicksort(array, low, j);
        }

        if (i < high) {
            quicksort(array, i, high);
        }
    }


    public static void main(String[] args) {
        int[] array = SortingUtils.getRandomArray(20);
        new Quicksort().execute(array);
        SortingUtils.print(array);
        System.out.println(SortingUtils.checkIfSortedAsc(array));
    }
}
