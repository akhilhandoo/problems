package algo.dp;

public class MaxSubsetSumNoAdjacent {

  public static void main(String[] args) {
    int[] array = new int[]{75, 105, 120, 75, 90, 135};
//    int[] array = new int[]{75, 105};
    System.out.println(maxSubsetSumNoAdjacent(array));
  }

  public static int maxSubsetSumNoAdjacent(int[] array) {
    if (array.length > 0) {
      int maxSumPrevious = array[0];
      if (array.length > 1) {
        int maxSum = Math.max(maxSumPrevious, array[1]);
        for (int index = 2; index < array.length; index++) {
          int temp = maxSum;
          maxSum = Math.max(maxSumPrevious + array[index], maxSum);
          maxSumPrevious = temp;
        }
        return maxSum;
      } else {
        return maxSumPrevious;
      }
    } else {
      return 0;
    }
  }
}
