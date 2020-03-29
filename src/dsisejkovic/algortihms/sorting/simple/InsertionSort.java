package dsisejkovic.algortihms.sorting.simple;

import dsisejkovic.algortihms.sorting.SortingUtils;

/**
 * Created by dsisejkovic on 18.7.2015..
 */
public class InsertionSort {

    public void execute(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int tmp = array[i];
            int j;
            for (j = i; j > 0 && tmp < array[j - 1]; j--) {
                array[j] = array[j - 1];
            }
            array[j] = tmp;
        }
    }


    public static void main(String[] args) {
        int[] array = SortingUtils.getTestArray();
        new InsertionSort().execute(array);
        SortingUtils.print(array);
    }

}
