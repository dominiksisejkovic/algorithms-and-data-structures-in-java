package dsisejkovic.algortihms.sorting.simple;

import dsisejkovic.algortihms.sorting.SortingUtils;

/**
 * Created by dsisejkovic on 18.7.2015..
 */
public class BubbleSort {

    public void execute(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j + 1] < array[j]) {
                    SortingUtils.swap(array, j+1, j);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = SortingUtils.getTestArray();
        new BubbleSort().execute(array);
        SortingUtils.print(array);
    }
}
