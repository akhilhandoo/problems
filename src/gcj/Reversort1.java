package gcj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Reversort1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int[] input = new int[n];
            for (int index = 0; index < n; index++) {
                input[index] = in.nextInt();
            }
            System.out.println("Case #" + i + ": " + costOfSort(input, 0));
        }
//        System.out.println(costOfSort(new int[]{4, 2, 1, 3}, 0));
//        System.out.println(costOfSort(new int[]{1, 2}, 0));
//        System.out.println(costOfSort(new int[]{7, 6, 5, 4, 3, 2, 1}, 0));
    }

    public static int costOfSort(int[] input, int currentIndex) {
        if (currentIndex == input.length - 1) {
            return 0;
        } else {
            int index = getIndexOfSmallestNumber(input, currentIndex);
            reverse(input, currentIndex, index);
            return (index - currentIndex + 1) + costOfSort(input, currentIndex + 1);
        }
    }

    public static void reverse(int[] input, int beginIndex, int endIndex) {
        if (beginIndex >= endIndex) {
            return;
        } else {
            int temp = input[beginIndex];
            input[beginIndex] = input[endIndex];
            input[endIndex] = temp;
            reverse(input, beginIndex + 1, endIndex - 1);
        }
    }

    public static int getIndexOfSmallestNumber(int[] input, int beginIndex) {
        int smallest = input[beginIndex];
        int smallestIndex = beginIndex;
        for (int index = beginIndex + 1; index < input.length; index++) {
            if (input[index] < smallest) {
                smallest = input[index];
                smallestIndex = index;
            }
        }
        return smallestIndex;
    }
}
