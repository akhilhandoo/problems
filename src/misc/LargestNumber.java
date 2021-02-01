package misc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;

public class LargestNumber {

  public static void main(String[] args) {
    int[] input = new int[]{0, 0};
    System.out.println(LargestNumber.largestNumber(input));
  }

  public static String largestNumber (int[] nums) {
    Integer[] input = new Integer[nums.length];
    for (int index = 0; index < nums.length; index++) {
      input[index] = nums[index];
    }
    Arrays.sort(input, new FarziComparator());
    StringBuilder largeNumberBuilder = new StringBuilder();
    for (int index = input.length - 1; index >= 0; index--) {
      largeNumberBuilder.append(input[index]);
    }
    return new BigDecimal(largeNumberBuilder.toString()).toString();
  }
}

class FarziComparator implements Comparator<Integer> {
  public int compare (Integer first, Integer second) {
    String one = "" + first;
    String two = "" + second;
    int index1 = 0;
    int index2 = 0;
    boolean firstOverflow = false;
    boolean secondOverflow = false;
    while (index1 < one.length() && index2 < two.length()) {
      if (Integer.parseInt("" + one.charAt(index1)) < Integer.parseInt("" + two.charAt(index2))) {
        return -1;
      } else if (Integer.parseInt("" + one.charAt(index1)) > Integer.parseInt("" + two.charAt(index2))) {
        return 1;
      } else {
        if (firstOverflow && secondOverflow) {
          return 0;
        }
        if ((index1 + 1) < one.length()) {
          index1++;
        } else {
          index1 = 0;
          firstOverflow = true;
        }
        if ((index2 + 1) < two.length()) {
          index2++;
        } else {
          index2 = 0;
          secondOverflow = true;
        }
      }
    }
    return 0;
  }
}