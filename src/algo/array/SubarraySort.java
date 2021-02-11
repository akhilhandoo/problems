package algo.array;

import java.util.Arrays;

public class SubarraySort {

    public static void main(String[] args) {
        int[] source = new int[]{1, 2, 4, 7, 10, 11, 7, 12, 7, 7, 16, 18, 19};
        //int[] source = new int[]{2, 1};
//        int[] source = new int[]{1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
        int[] answer = subarraySort(source);
        System.out.println(answer[0] + ", " + answer[1]);
    }

    public static int[] subarraySort(int[] array) {
        int[] indexAndValueLeft = getIndexAndValueBreakingSortedOrderFromLeft(array);
        int[] indexAndValueRight = getIndexAndValueBreakingSortedOrderFromRight(array);
        if (indexAndValueLeft.length == 0 || indexAndValueRight.length == 0) {
            return new int[]{-1, -1};
        }
        return new int[] {
                getCorrectPositionFromLeftFor(array, indexAndValueLeft[0], getValueAfter(array, indexAndValueLeft[0] + 1)),
                getCorrectPositionFromRightFor(array, indexAndValueRight[0], getValueBefore(array, indexAndValueRight[0] - 1))
        };
    }

    public static int getValueAfter(int[] input, int index) {
        int ceiling = input[index];
        int leastSoFar = ceiling;
        for (int id = index + 1; id < input.length; id++) {
            if (input[id] < leastSoFar) {
                leastSoFar = input[id];
            }
        }
        return Math.min(leastSoFar, ceiling);
    }

    public static int getValueBefore(int[] input, int index) {
        int floor = input[index];
        int maxSoFar = floor;
        for (int id = index - 1; id >= 0; id--) {
            if (input[id] > maxSoFar) {
                maxSoFar = input[id];
            }
        }
        return Math.max(maxSoFar, floor);
    }

    public static int getCorrectPositionFromLeftFor(int[] input, int endIndex, int value) {
        for (int index = 0; index <= endIndex; index++) {
            if (value < input[index]) {
                return index;
            }
        }
        return -1;
    }

    public static int getCorrectPositionFromRightFor(int[] input, int beginIndex, int value) {
        for (int index = input.length - 1; index >= beginIndex; index--) {
            if (value >= input[index]) {
                return index;
            }
        }
        return -1;
    }

    public static int[] getIndexAndValueBreakingSortedOrderFromLeft(int[] input) {
        for (int index = 1; index < input.length; index++) {
            if (input[index] < input[index - 1]) {
                return new int[]{index - 1, input[index]};
            }
        }
        return new int[]{};
    }

    public static int[] getIndexAndValueBreakingSortedOrderFromRight(int[] input) {
        for (int index = input.length - 2; index >= 0; index--) {
            if (input[index] > input[index + 1]) {
                return new int[]{index + 1, input[index]};
            }
        }
        return new int[]{};
    }
}
