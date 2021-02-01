package algo.famous;

public class Kadane {

  public static void main(String[] args) {
    int[] input = new int[]{3, 5, -9, 1, 3, -2, 3, 4, 7, 2, -9, 6, 3, 1, -5, 4};
//    int[] input = new int[]{-3, -5, -9, -1, -3, -2, -3, -4, -7, -2, -9, -6, -3, -1, -5, -4};
    System.out.println(Kadane.kadanesAlternateAlgorithm(input));
  }

  public static int kadanesAlgorithm(int[] array) {
    int maxSum = 0;
    int sumEndingHere = 0;
    boolean allNegative = true;
    int leastNegative = Integer.MIN_VALUE;
    for (int integer: array) {
      allNegative = allNegative && (integer < 0);
      if (integer < 0 && integer > leastNegative) {
        leastNegative = integer;
      }
      sumEndingHere += integer;
      if (sumEndingHere > maxSum) {
        maxSum = sumEndingHere;
      }
      if (sumEndingHere < 0) {
        sumEndingHere = 0;
      }
    }
    if (allNegative) {
      return leastNegative;
    } else {
      return maxSum;
    }
  }

  public static int kadanesAlternateAlgorithm(int[] array) {
    int maxSoFar = array[0];
    int maxEndingHere = array[0];
    for (int index = 1; index < array.length; index++) {
      maxEndingHere = Math.max(array[index], maxEndingHere + array[index]);
      maxSoFar = Math.max(maxSoFar, maxEndingHere);
    }
    return maxSoFar;
  }
}
