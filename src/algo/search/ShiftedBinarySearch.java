package algo.search;

public class ShiftedBinarySearch {

  public static void main(String[] args) {
    int[] input = new int[]{45, 61, 71, 72, 73, 0, 1, 21, 33, 37};
    System.out.println(ShiftedBinarySearch.shiftedBinarySearch(input, 62));
  }

  public static int shiftedBinarySearch(int[] array, int target) {
    return modifiedBinarySearch(array, 0, array.length - 1, target);
  }

  private static int modifiedBinarySearch(int[] input, int low, int high, int target) {
    if (low > high) {
      return -1;
    }
    int middle = (low + high) / 2;
    if (input[middle] == target) {
      return middle;
    } else {
      if (input[low] < input[middle]) {
        if (input[low] <= target && target < input[middle]) {
          return modifiedBinarySearch(input, low, middle - 1, target);
        } else {
          return modifiedBinarySearch(input, middle + 1, high, target);
        }
      } else {
        if (input[middle] < target && target <= input[high]) {
          return modifiedBinarySearch(input, middle + 1, high, target);
        } else {
          return modifiedBinarySearch(input, low, middle - 1, target);
        }
      }
    }
  }
}
