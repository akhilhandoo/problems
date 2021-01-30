package algo.sort;

public class VariousSort {

    public static void main(String[] args) {
        int[] array = new int[]{8, 5, 2, 9, 5, 6, 3};
//        array = bubbleSort(array);
//        array = insertionSort(array);
        array = selectionSort(array);
        for (int item: array) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public static int[] bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i+1; j < array.length; j++) {
                if (array[j] < array[i]) {
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }
        return array;
    }

    public static int[] insertionSort(int[] array) {
        int sortedIndex = 0;
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = sortedIndex;
            while (j >= 0) {
                if (array[j] < temp) {
                    break;
                } else {
                    array[j+1] = array[j];
                }
                j--;
            }
            array[j+1] = temp;
            sortedIndex++;
        }
        return array;
    }

    public static int[] selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i+1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }
}
