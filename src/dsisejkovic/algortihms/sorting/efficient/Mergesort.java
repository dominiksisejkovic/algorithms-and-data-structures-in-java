package dsisejkovic.algortihms.sorting.efficient;

import dsisejkovic.algortihms.sorting.SortingUtils;
import dsisejkovic.algortihms.sorting.simple.InsertionSort;

/**
 * Created by dsisejkovic on 19.7.2015..
 */
public class Mergesort {
    private InsertionSort insertionSort = new InsertionSort();
    private int[] tmp;

    public void execute(int[] array) {
        if (array.length > 10) {
            this.tmp = new int[array.length];
            mergesort(array, 0, array.length - 1);
        } else {
            insertionSort.execute(array);
        }
    }


    private void mergesort(int[] array, int low, int high) {
        if (low < high) {
            int middle = low + (high - low) / 2;
            mergesort(array, low, middle);
            mergesort(array, middle + 1, high);
            merge(array, low, middle, high);
        }
    }

    private void merge(int[] array, int low, int middle, int high) {
        // copy both parts into the tmp array
//        System.arraycopy(array, low, tmp, low, high + 1 - low);

        for (int i = low; i <= high; i++) {
            tmp[i] = array[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;

        // copy the smallest values from either the left or the right side back to original array
        while (i <= middle && j <= high) {
            if (tmp[i] <= tmp[j]) {
                array[k] = tmp[i];
                i++;
            } else {
                array[k] = tmp[j];
                j++;
            }
            k++;
        }

        // copy the rest
        // there is no chance of remaining parts in the right subarray
//        System.arraycopy(tmp, i, array, k, middle - i + 1);
        while (i <= middle) {
            array[k] = tmp[i];
            k++;
            i++;
        }

    }

    public static void main(String[] args) {
        int[] array = SortingUtils.getRandomArray(10000000);
        new Mergesort().execute(array);
//        SortingUtils.print(array);
        System.out.println(SortingUtils.checkIfSortedAsc(array));
    }
}
