package dsisejkovic.algortihms.sorting;

/**
 * Created by dsisejkovic on 5.8.2015..
 */
public class SortTest {

    void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int tmp = array[i];
            int j;
            for (j = i; j > 0 && tmp < array[j - 1]; j--) {
                array[j] = array[j - 1];
            }
            array[j] = tmp;
        }
    }


    public void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            if (i != min) {
                SortingUtils.swap(array, i, min);
            }
        }
    }

    public void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j + 1] < array[j]) {
                    SortingUtils.swap(array, j + 1, j);
                }
            }
        }
    }

    public void shellSort(int[] array) {
        for (int step = array.length / 2; step > 0; step /= 2) {
            // modified insertion sort
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

    public void heapSort(int[] array) {
        // use Floyd algorithm to heapify array
        // array is 0-based

        heapify(array);
        sortHeap(array);
    }

    private void heapify(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            moveDown(array, i, array.length - 1);
        }
    }

    private void sortHeap(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            SortingUtils.swap(array, 0, i);
            moveDown(array, 0, i - 1);
        }
    }

    private void moveDown(int[] array, int first, int last) {
        int parent = first;
        int child = first * 2 + 1;
        while (child <= last) {
            // child < last -> ensures that the right child exists (child +1)
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


    private int[] tmpMergeArray;

    public void mergeSort(int[] array) {
        tmpMergeArray = new int[array.length];
        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(int[] array, int low, int high) {
        if (low < high) {
            int middle = low + (high - low) / 2; // prevents overflow
            mergeSort(array, low, middle);
            mergeSort(array, middle + 1, high);
            merge(array, low, middle, high);
        }
    }

    private void merge(int[] array, int low, int middle, int high) {
        for (int i = low; i <= high; i++) {
            tmpMergeArray[i] = array[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        while (i <= middle && j <= high) {
            if (tmpMergeArray[i] <= tmpMergeArray[j]) {
                array[k] = tmpMergeArray[i];
                i++;
            } else {
                array[k] = tmpMergeArray[j];
                j++;
            }
            k++;
        }

        while (i <= middle) {
            array[k] = tmpMergeArray[i];
            i++;
            k++;
        }
    }

    public void quicksort(int[] array) {
        quicksort(array, 0, array.length - 1);
    }

    private void quicksort(int[] array, int low, int high) {
        if (array.length < 2) {
            return;
        }

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
        SortTest st = new SortTest();
        int arraySize = 12345;

        System.out.println("### Initial test - start###");

        int[] array = SortingUtils.getRandomArray(arraySize);
        st.insertionSort(array);
        System.out.println("Insertion sort: " + SortingUtils.checkIfSortedAsc(array));

        array = SortingUtils.getRandomArray(arraySize);
        st.selectionSort(array);
        System.out.println("Selection sort: " + SortingUtils.checkIfSortedAsc(array));

        array = SortingUtils.getRandomArray(arraySize);
        st.bubbleSort(array);
        System.out.println("Bubble sort: " + SortingUtils.checkIfSortedAsc(array));

        array = SortingUtils.getRandomArray(arraySize);
        st.shellSort(array);
        System.out.println("Shell sort: " + SortingUtils.checkIfSortedAsc(array));

        array = SortingUtils.getRandomArray(arraySize);
        st.heapSort(array);
        System.out.println("Heap sort: " + SortingUtils.checkIfSortedAsc(array));

        array = SortingUtils.getRandomArray(arraySize);
        st.mergeSort(array);
        System.out.println("Merge sort: " + SortingUtils.checkIfSortedAsc(array));

        array = SortingUtils.getRandomArray(arraySize);
        st.quicksort(array);
        System.out.println("Quick sort: " + SortingUtils.checkIfSortedAsc(array));
        System.out.println("### Initial test - end ###");

        System.out.println("### Advanced test - start ###");

        boolean[] checkArray = new boolean[7];
        boolean overallResult = true;
        for (int i = 0; i <= 10000; i++) {
            array = SortingUtils.getRandomArray(i);
            st.insertionSort(array);
            checkArray[0] = SortingUtils.checkIfSortedAsc(array);

            array = SortingUtils.getRandomArray(i);
            st.selectionSort(array);
            checkArray[1] = SortingUtils.checkIfSortedAsc(array);

            array = SortingUtils.getRandomArray(i);
            st.bubbleSort(array);
            checkArray[2] = SortingUtils.checkIfSortedAsc(array);

            array = SortingUtils.getRandomArray(i);
            st.shellSort(array);
            checkArray[3] = SortingUtils.checkIfSortedAsc(array);

            array = SortingUtils.getRandomArray(i);
            st.heapSort(array);
            checkArray[4] = SortingUtils.checkIfSortedAsc(array);

            array = SortingUtils.getRandomArray(i);
            st.mergeSort(array);
            checkArray[5] = SortingUtils.checkIfSortedAsc(array);

            array = SortingUtils.getRandomArray(i);
            st.quicksort(array);
            checkArray[6] = SortingUtils.checkIfSortedAsc(array);

            if (!passed(checkArray)) {
                System.out.println("[" + i + "] - failed");
                overallResult = false;
            } else {
                System.out.println("[" + i + "] - passed");
            }
        }

        System.out.println("### Advanced test - end ###");
        System.out.println("Overal: " + overallResult);
    }

    private static boolean passed(boolean[] checkArray) {
        boolean passed = true;
        for (int i = 0; i < checkArray.length; i++) {
            if (!checkArray[i]) {
                passed = false;
            }
            checkArray[i] = false;
        }

        return passed;
    }
}
