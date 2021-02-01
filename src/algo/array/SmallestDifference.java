package algo.array;

import java.util.Arrays;

public class SmallestDifference {

  public static void main(String[] args) {
    int[] arrayOne = new int[]{-1, 5, 10, 20, 28, 3};
    int[] arrayTwo = new int[]{26, 134, 135, 15, 17};
    int[] result = smallestDifference(arrayOne, arrayTwo);
    System.out.println("[" + result[0] + ", " + result[1] + "]");
  }

  public static int[] smallestDifference(int[] arrayOne, int[] arrayTwo) {
    int[] inputA = new int[arrayOne.length];
    int[] inputB = new int[arrayTwo.length];
    System.arraycopy(arrayOne, 0, inputA, 0, arrayOne.length);
    System.arraycopy(arrayTwo, 0, inputB, 0, arrayTwo.length);

    Arrays.sort(inputA);
    Arrays.sort(inputB);

    int indexA = 0;
    int indexB = 0;
    int minimumDifference = Integer.MAX_VALUE;
    int indexForA = 0;
    int indexForB = 0;
    while (indexA < inputA.length && indexB < inputB.length) {
      int currentDifference = Math.abs(inputA[indexA] - inputB[indexB]);
      if (currentDifference < minimumDifference) {
        minimumDifference = currentDifference;
        indexForA = indexA;
        indexForB = indexB;
      }
      if (currentDifference == 0) {
        break;
      }
      if (inputA[indexA] < inputB[indexB]) {
        indexA++;
      } else {
        indexB++;
      }
    }
    return new int[]{inputA[indexForA], inputB[indexForB]};
  }
}
