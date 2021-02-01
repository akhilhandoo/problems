package algo.array;

public class Monotonic {

  public static void main(String[] args) {
    int[] input = new int[]{6, 5, 5, 6, 7, 7, 8, 9};
    System.out.println(isMonotonic(input));
  }

  public static boolean isMonotonic(int[] array) {
    int direction = 0;
    boolean anomalyDetected = false;
    for (int index = 1; index < array.length; index++) {
      if (array[index] == array[index - 1]) {
        continue;
      } else if (array[index] > array[index - 1]) {
        if (direction == 0) {
          direction = 1;
        } else if (direction == 1) {
          continue;
        } else {
          anomalyDetected = true;
          break;
        }
      } else {
        if (direction == 0) {
          direction = -1;
        } else if (direction == -1) {
          continue;
        } else {
          anomalyDetected = true;
          break;
        }
      }
    }
    if (anomalyDetected) {
      return false;
    } else {
      return true;
    }
  }
}
