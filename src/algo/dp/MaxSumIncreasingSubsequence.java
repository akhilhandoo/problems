package algo.dp;

import java.util.*;

public class MaxSumIncreasingSubsequence {

  public static void main(String[] args) {
//    System.out.println(maxSumIncreasingSubsequence(new int[]{10, 70, 20, 30, 50, 11, 30}));
//    System.out.println(maxSumIncreasingSubsequence(new int[]{-1, 1}));
//    System.out.println(maxSumIncreasingSubsequence(new int[]{-5, -4, -3, -2, -1}));
    System.out.println(maxSumIncreasingSubsequence(new int[]{10, 1, 2, 3, 4, 5, 6, 7, 8, 9}));
  }

  public static List<List<Integer>> maxSumIncreasingSubsequence(int[] array) {
    List<List<Integer>> toReturn = new ArrayList<>();
    int[] maxSumSubsequenceEndingAt= new int[array.length];
    int[] resultIndices = new int[array.length];
    Arrays.fill(resultIndices, -1);
    maxSumSubsequenceEndingAt[0] = array[0];
    int winner = array[0];
    int winnerIndex = 0;
    for (int index = 1; index < array.length; index++) {
      int maxSumTillNow = Integer.MIN_VALUE;
      int winningIndex = -1;
      for (int doubleCheck = 0; doubleCheck < index; doubleCheck++) {
        if (array[doubleCheck] < array[index]) {
          if (maxSumSubsequenceEndingAt[doubleCheck] > maxSumTillNow) {
            maxSumTillNow = maxSumSubsequenceEndingAt[doubleCheck];
            winningIndex = doubleCheck;
          }
        }
      }
      if (maxSumTillNow > 0) {
        maxSumSubsequenceEndingAt[index] = array[index] + maxSumTillNow;
        resultIndices[index] = winningIndex;
      } else {
        maxSumSubsequenceEndingAt[index] = array[index];
        resultIndices[index] = -1;
      }
      if (maxSumSubsequenceEndingAt[index] > winner) {
        winner = maxSumSubsequenceEndingAt[index];
        winnerIndex = index;
      }
    }
    toReturn.add(Arrays.asList(new Integer[]{winner}));
    ArrayDeque<Integer> stack = new ArrayDeque<>();
    int index = winnerIndex;
    do {
      stack.push(array[index]);
      index = resultIndices[index];
    } while (index != -1);
    List<Integer> subsequenceConstituents = new ArrayList<>(stack.size());
    while (!stack.isEmpty()) {
      subsequenceConstituents.add(stack.pop());
    }
    toReturn.add(subsequenceConstituents);
    return toReturn;
  }
}
