package algo.dp;

import java.util.Arrays;

public class ChangeMinCoins {

  public static void main(String[] args) {

    int[] denomination = new int[]{8, 2, 3, 10, 1, 5};

//    int[] denomination = new int[]{3, 5};

    System.out.println(ChangeMinCoins.minNumberOfCoinsForChange(16, denomination));
  }

  public static int minNumberOfCoinsForChange(int n, int[] denoms) {
    int[] denominations = new int[denoms.length];
    System.arraycopy(denoms, 0, denominations, 0, denoms.length);
    Arrays.sort(denominations);

    int[] results = new int[n+1];
    boolean firstDenomination = true;

    for (int denomination: denominations) {
      if (denomination > n) {
        break;
      }
      results[denomination] = 1;
      for (int index = denomination + 1; index <= n; index++) {
        if (!firstDenomination) {
          int remaining = results[index - denomination];
          if (remaining >= 1) {
            if (results[index] >= 1) {
              results[index] = Math.min(results[index], results[index - denomination] + 1);
            } else {
              results[index] = remaining + 1;
            }
          }
        } else {
          int remaining = results[index - denomination];
          if (remaining <= 0) {
            results[index] = -1;
          } else {
            results[index] = remaining + 1;
          }
        }
      }
      firstDenomination = false;
    }
    return results[n];
  }
}
