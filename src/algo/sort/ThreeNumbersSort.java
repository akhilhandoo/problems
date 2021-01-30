package algo.sort;

import java.util.HashMap;
import java.util.Map;

public class ThreeNumbersSort {

    public static void main(String[] args) {
//        int[] array = new int[]{1, 0, 0, -1, -1, 0, 1, 1};
//        int[] order = new int[]{-1, 0, 1};
//        array = threeNumberSort(array, order);
//        for (int item: array) {
//            System.out.print(item + " ");
//        }
//        System.out.println();


        int[] array = new int[]{1, 0, 0, 2, 2, 0, 1, 1};
        colorSort(array);
        for (int item: array) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public static int[] threeNumberSort(int[] array, int[] order) {
        Map<Integer, Integer> ordersMap = new HashMap<>();
        for (int index = 0; index < order.length; index++) {
            ordersMap.put(order[index], index);
        }
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (isGreaterThan(array[i], array[j], ordersMap)) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    public static boolean isGreaterThan (int number1, int number2, Map<Integer, Integer> ordersMap) {
        return ordersMap.get(number1) > ordersMap.get(number2);
    }

    public static void colorSort(int[] input) {
        int czero = 0;
        int cone = 0;
        int ctwo = 0;

        for (int index = 0; index < input.length; index++) {
            if (input[index] == 0) {
                czero++;
            } else if (input[index] == 1) {
                cone++;
            } else {
                ctwo++;
            }
        }

        int index = 0;
        int offset = 0;
        while (index < czero) {
            input[index + offset] = 0;
            index++;
        }
        offset = czero;
        index = 0;
        while (index < cone) {
            input[index + offset] = 1;
            index++;
        }
        offset = czero + cone;
        index = 0;
        while (index < ctwo) {
            input[index + offset] = 2;
            index++;
        }
    }
}
