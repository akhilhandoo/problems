package algo.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TwoSum {

  public static int[] twoNumberSum(int[] array, int targetSum) {
    int[] inputCopy = new int[array.length];
    System.arraycopy(array, 0, inputCopy, 0, array.length);
    Arrays.sort(inputCopy);
    int left = 0;
    int right = array.length - 1;
    while (left < right) {
      int twoSum = inputCopy[left] + inputCopy[right];
      if (twoSum == targetSum) {
        return new int[]{inputCopy[left], inputCopy[right]};
      } else if (twoSum < targetSum) {
        left++;
      } else {
        right--;
      }
    }
    return new int[]{};
  }
}
