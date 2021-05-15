package gcj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CodeJamal {

    public static void main(String[] args) {

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for(int i = 1; i <=t; ++i) {
            int x = in.nextInt();
            int y = in.nextInt();
            char[] input = in.nextLine().toCharArray();
            System.out.println("Case #" + i + ": " + minimumRoyalty(input, 0, x, y));
        }
//        System.out.println(minimumRoyalty(new char[]{'C', 'J', '?', 'C', 'C', '?'}, 0,2, 3));
//        System.out.println(minimumRoyalty(new char[]{'C', '?', 'J'}, 0,1, 3));
//        System.out.println(minimumRoyalty(new char[]{'?', '?', 'J', '?', '?', '?'}, 0,2, 5));
    }

    public static int minimumRoyalty (char[] input, int currentIndex, int x, int y) {
        if (currentIndex == input.length - 1) {
            if (input[currentIndex] != '?') {
                return evaluate(input, x, y);
            } else {
                return evaluate((String.valueOf(input, 0, currentIndex) + input[currentIndex]).toCharArray(), x, y);
            }
        } else {
            if (input[currentIndex] == '?') {
                int totalC = minimumRoyalty((String.valueOf(input, 0, currentIndex) + "C" + String.valueOf(input, currentIndex + 1, input.length - currentIndex - 1)).toCharArray(), currentIndex + 1, x, y);
                int totalJ = minimumRoyalty((String.valueOf(input, 0, currentIndex) + "J" + String.valueOf(input, currentIndex + 1, input.length - currentIndex - 1)).toCharArray(), currentIndex + 1, x, y);
                return Math.min(totalC, totalJ);
            } else {
                return minimumRoyalty(input, currentIndex + 1, x, y);
            }
        }
    }

    public static int evaluate (char[] input, int x, int y) {
        int total = 0;
        for (int index = 1; index < input.length; index++) {
            if (input[index - 1] == 'C' && input[index] == 'J') {
                total += x;
            } else if (input[index - 1] == 'J' && input[index] == 'C') {
                total += y;
            }
        }
        return total;
    }
}
