package algo.dp;

import java.util.Arrays;

public class MakeChange {

  public static void main(String[] args) {
    int[] denoms = new int[]{5, 1, 2};
    int target = 6;
    System.out.println(MakeChange.numberOfWaysToMakeChange(target, denoms));
  }

  public static int numberOfWaysToMakeChange(int n, int[] denoms) {
    int[] denominations = new int[denoms.length];
    System.arraycopy(denoms, 0, denominations, 0, denoms.length);
    Arrays.sort(denominations);

    int[] results = new int[n+1];
    results[0] = 1;

    for (int denom: denominations) {
      if (denom > n) {
        break;
      } else {
        for (int index = denom; index <= n; index++) {
          results[index] += results[index - denom];
        }
      }
    }
    return results[n];
  }
}
