package algo.array;

import java.util.*;

public class ThreeNumberSum {

  public static void main(String[] args) {
    int[] input = new int[]{12, 3, 1, 2, -6, 5, -8, 6};
    List<Integer[]> answer = ThreeNumberSum.threeNumberSumAlternate(input, 0);
    for (Integer[] triplet: answer) {
      System.out.println("[" + triplet[0] + ", " + triplet[1] + ", " + triplet[2] + "]");
    }
  }

  public static List<Integer[]> threeNumberSum(int[] array, int targetSum) {
    List<Integer[]> toReturn = new ArrayList<>();
    Set<Integer> lookup = new HashSet<>();
    for (int i: array) {
      lookup.add(i);
    }
    Set<Integer> alreadyFormed = new HashSet<>();
    for (int i = 0; i < array.length; i++) {
      for (int j = i + 1; j < array.length; j++) {
        int toLookup = targetSum - (array[i] + array[j]);
        if (toLookup != array[i] && toLookup != array[j]) {
          if (lookup.contains(toLookup)) {
            Integer[] tempArray = new Integer[]{array[i], array[j], toLookup};
            Arrays.sort(tempArray);
            if (!alreadyFormed.contains(Arrays.hashCode(tempArray))) {
              toReturn.add(tempArray);
              alreadyFormed.add(Arrays.hashCode(tempArray));
            }
          }
        }
      }
    }
    Collections.sort(toReturn, new Comparator<Integer[]>() {
      @Override
      public int compare(Integer[] o1, Integer[] o2) {
        for (int index = 0; index < o1.length; index++) {
          if (o1[index] < o2[index]) {
            return -1;
          } else if (o2[index] < o1[index]) {
            return +1;
          }
        }
        return 0;
      }
    });
    return toReturn;
  }

  public static List<Integer[]> threeNumberSumAlternate(int[] input, int targetSum) {
    int[] array = new int[input.length];
    System.arraycopy(input, 0, array, 0, input.length);
    Arrays.sort(array);
    List<Integer[]> toReturn = new ArrayList<>();
    for (int i = 0; i < array.length - 2; i++) {
      int left = i + 1;
      int right = array.length - 1;
      while (left < right) {
        int currentSum = array[i] + array[left] + array[right];
        if (currentSum == targetSum) {
          Integer[] tempArray = new Integer[]{array[i], array[left], array[right]};
          toReturn.add(tempArray);
          left++;
          right--;
        } else if (currentSum < targetSum) {
          left++;
        } else {
          right--;
        }
      }
    }
    return toReturn;
  }
}
