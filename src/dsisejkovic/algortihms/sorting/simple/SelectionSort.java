package dsisejkovic.algortihms.sorting.simple;

import dsisejkovic.algortihms.sorting.SortingUtils;

/**
 * Created by dsisejkovic on 18.7.2015..
 */
public class SelectionSort {

    public void execute(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }

            SortingUtils.swap(array, i, min);
        }
    }

    public static void main(String[] args) {
        int[] array = SortingUtils.getTestArray();
        new SelectionSort().execute(array);
        SortingUtils.print(array);
    }

}
