package datadef.prac;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class MaxProfitK {

  public static void main(String[] args) {
//    int[] input = new int[] {5, 11, 3, 50, 60, 90};
//    int[] input = new int[] {3, 7, 5, 2, 4, 1, 9, 8, 9}; // 4, 2, 8, 1
    int[] input = new int[] {1, 25, 24, 23, 12, 36, 14, 40, 31, 41, 5}; //  24, 24, 36, 10
    System.out.println(MaxProfitK.maxProfitWithKTransactions(input, 2));
  }

  public static int maxProfitWithKTransactions(int[] prices, int k) {
    LinkedHashMap<AbstractMap.SimpleEntry<Integer, Integer>, Integer> results = new LinkedHashMap<>();
    int beginIndex = -1;
    int total = 0;
    for (int index = 1; index < prices.length; index++) {
      if (prices[index] > prices[index - 1]) {
        if (beginIndex == -1) {
          beginIndex = index - 1;
        }
        total += (prices[index] - prices[index - 1]);
      } else {
        if (beginIndex != -1) {
          results.put(new AbstractMap.SimpleEntry<Integer, Integer>(beginIndex, index - 1), total);
          total = 0;
          beginIndex = -1;
        }
      }
    }
    if (beginIndex >= 0) {
      results.put(new AbstractMap.SimpleEntry<Integer, Integer>(beginIndex, prices.length - 1), total);
    }
    List<Integer> profits = results.entrySet().stream().map(x -> x.getValue()).collect(Collectors.toList());
    Collections.sort(profits, Collections.reverseOrder());
    int maxProfit = 0;
    for (int index = 0; index < k && index < profits.size(); index++) {
      maxProfit += profits.get(index);
    }
    return maxProfit;
  }
}
