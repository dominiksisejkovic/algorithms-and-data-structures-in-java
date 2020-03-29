package dsisejkovic.algortihms.sorting.efficient;

import dsisejkovic.algortihms.sorting.SortingUtils;

/**
 * Created by dsisejkovic on 18.7.2015..
 */
public class ShellSort {

    public void execute(int[] array) {
        for (int step = array.length / 2; step > 0; step /= 2) {
            // insertion sort:
            for (int i = step; i < array.length; i++) {
                int tmp = array[i];
                int j;
                for (j = i; j >= step && tmp < array[j - step]; j -= step) {
                    array[j] = array[j - step];
                }
                array[j] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = SortingUtils.getRandomArray(10000);
        new ShellSort().execute(array);
        SortingUtils.print(array);

        System.out.println(SortingUtils.checkIfSortedAsc(array));

    }
}
