package dsisejkovic.algortihms.sorting.efficient;

import dsisejkovic.algortihms.sorting.SortingUtils;

/**
 * Created by dsisejkovic on 18.7.2015..
 */
public class HeapSort {

    // based on bottom-up heap construction
    public void execute(int[] array) {
        // create heap
        // Floyd algorithm - O(n)
        // 0-based array

        // heapify()
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            moveDown(array, i, array.length - 1);
        }

        // sort()
        // O(n*log(n))
        for (int i = array.length - 1; i > 0; i--) {
            SortingUtils.swap(array, 0, i);
            moveDown(array, 0, i - 1);
        }

    }


    private void moveDown(int[] array, int first, int last) {
        int parent = first;
        int child = first * 2 + 1;
        while (child <= last) {
            if (child < last && array[child] < array[child + 1]) {
                child++;
            }

            if (array[parent] < array[child]) {
                SortingUtils.swap(array, child, parent);
                parent = child;
                child = parent * 2 + 1;
            } else {
                break;
            }
        }
    }


    public static void main(String[] args) {
        int[] array = SortingUtils.getRandomArray(200);

        new HeapSort().execute(array);
//        SortingUtils.print(array);

        System.out.println(SortingUtils.checkIfSortedAsc(array));
    }
}
