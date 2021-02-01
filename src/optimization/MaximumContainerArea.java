package optimization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MaximumContainerArea {

  public static void main(String[] args) {
    int[] input = new int[]{3, 1, 2, 4, 5};
    ArrayList<Integer> list = (ArrayList)Arrays.stream(input).boxed().collect(Collectors.toList());
    System.out.println(" Answer => " + MaximumContainerArea.maxArea(list));
  }

  public static int maxArea(ArrayList<Integer> A) {
    int left = 0;
    int right = A.size() - 1;
    int area = 0;

    while (left < right) {
      area = Math.max(area, Math.min(A.get(left), A.get(right)) * (right - left));

      if (A.get(left) < A.get(right)) {
        left++;
      } else {
        right--;
      }
    }
    return area;
  }
}
