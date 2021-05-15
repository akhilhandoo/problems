package algo.array;

import java.util.Arrays;

public class NonConstructibleChange {

    public static void main(String[] args) {
        System.out.println(nonConstructibleChange(new int[]{5, 1, 2}));
    }

    //  1 1 2 3 5 7 22
    public static int nonConstructibleChange(int[] coins) {
        Arrays.sort(coins);
        if (coins.length == 0 || coins[0] != 1) {
            return 1;
        }
        int canMake = 1;
        for (int index = 1; index < coins.length; index++) {
            if (coins[index] > canMake + 1) {
                break;
            } else {
                canMake += coins[index];
            }
        }
        return canMake + 1;
    }
}
