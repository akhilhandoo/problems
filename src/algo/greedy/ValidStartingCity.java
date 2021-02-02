package algo.greedy;

import java.util.Arrays;

public class ValidStartingCity {

  public static void main(String[] args) {
    int[] distances = new int[]{1, 3, 10, 6, 7, 7, 2, 4};
    int[] fuel = new int[]{1, 1, 1, 1, 1, 1, 1, 1};
    System.out.println(validStartingCity(distances, fuel, 5));
  }

  public static int validStartingCity(int[] distances, int[] fuel, int mpg) {
    int minFuelAtBeginning = Integer.MAX_VALUE;
    int indexToNote = -1;
    int runningTotal = 0;
    for (int index = 0; index < distances.length; index++) {
      if (runningTotal < minFuelAtBeginning) {
        minFuelAtBeginning = runningTotal;
        indexToNote = index;
      }
      runningTotal = runningTotal + (mpg * fuel[index]) - distances[index];
    }
    return indexToNote;
  }
}
