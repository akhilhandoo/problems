package algo.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LargestRange {

  public static void main(String[] args) {
//    int[] input = new int[]{1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6};
    int[] input = new int[]{-1, 0, 1};
    int[] response = largestRange(input);
    System.out.println("[" + response[0] + ", " + response[1] + "]");
  }

  public static int[] largestRange(int[] array) {
    if (array.length == 0) {
      return new int[]{};
    } else if (array.length == 1) {
      return new int[]{array[0], array[0]};
    }
    Map<Integer, List<Integer>> lookup = new HashMap<>();
    for (int item: array) {
      lookup.put(item, null);
    }
    int largestRange = 1;
    int itemWithLargestRange = array[0];
    for (int item: array) {
      int itemRange = 0;
      if (null == lookup.get(item)) {
        List<Integer> range = new ArrayList<>();
        range.add(item);
        lookup.put(item, range);
        int left = item - 1;
        itemRange++;
        while (lookup.containsKey(left)) {
          range.add(left);
          lookup.put(left, range);
          left--;
          itemRange++;
        }
        int right = item + 1;
        while (lookup.containsKey(right)) {
          range.add(right);
          lookup.put(right, range);
          right++;
          itemRange++;
        }
      }
      if (itemRange > largestRange) {
        largestRange = itemRange;
        itemWithLargestRange = item;
      }
    }
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int item: lookup.get(itemWithLargestRange)) {
      if (item < min) {
        min = item;
      }
      if (item > max) {
        max = item;
      }
    }
    return new int[]{min, max};
  }
}
