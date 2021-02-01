package optimization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxProductSubarray {

  public static void main(String[] args) {
    //int[] input = new int[]{3 , 7, -2, 5, -6, 9, 4, -5, 4, 2, 1};
    //int[] input = new int[]{-3 , -7, 0, -5, -6, -9, -4, -2};
    int[] input = new int[]{-2};
    System.out.println(new MaxProductSubarray().maxProduct(input));

//    System.out.println("select key as key,name as name from default.reserved_table".replaceAll("from (.*)", "from <ACP_QUERY>"));
  }

  public int maxProduct(int[] nums) {
    return maxProSegmented(nums, 0, nums.length);
  }

  private int maxProSegmented(int[] input, int start, int end) {
    if (start >= end) {
      return 0;
    } else if (end - start == 1) {
      return input[start];
    }
    List<Integer> zeroIndices = new ArrayList<>();
    List<Integer> negativeIndices = new ArrayList<>();
    for (int index = start; index < end; index++) {
      if (input[index] == 0) {
        zeroIndices.add(index);
      }
      if (input[index] < 0) {
        negativeIndices.add(index);
      }
    }
    if (zeroIndices.size() > 0) {
      int maxPro = 0;
      int currentIndex = start;
      for (int zeroIndex: zeroIndices) {
        int size = zeroIndex - currentIndex;
        if (size > 0) {
          int segmentPro = maxProSegmented(input, currentIndex, zeroIndex);
          if (segmentPro > maxPro) {
            maxPro = segmentPro;
          }
        }
        currentIndex = zeroIndex + 1;
      }
      if (end - currentIndex > 0) {
        int lastSegmentPro = maxProSegmented(input, currentIndex, end);
        if (lastSegmentPro > maxPro) {
          maxPro = lastSegmentPro;
        }
      }
      return maxPro;
    } else if (negativeIndices.size() > 0) {
      if (negativeIndices.size() == 1) {
        int maxProForASegment = 0;
        if (negativeIndices.get(0) - start > 0) {
          maxProForASegment = maxProSegmented(input, start, negativeIndices.get(0));
        }
        if (end - negativeIndices.get(0) > 0) {
          int maxProSecondSegment =  maxProSegmented(input, negativeIndices.get(0) + 1, end);
          if (maxProSecondSegment > maxProForASegment) {
            maxProForASegment = maxProSecondSegment;
          }
        }
        return maxProForASegment;
      } else if (negativeIndices.size() % 2 == 0) {
        return productize(input, start, end);
      } else {
        int maxPro1 = negativeIndices.get(negativeIndices.size() - 1) - start > 0 ? maxProSegmented(input, start, negativeIndices.get(negativeIndices.size() - 1)) : 0;
        int maxPro2 = end - negativeIndices.get(0) + 1 > 0 ? maxProSegmented(input, negativeIndices.get(0) + 1, end) : 0;
        return maxPro1 > maxPro2 ? maxPro1 : maxPro2;
      }
    } else {
      return productize(input, start, end);
    }
  }

  private int productize(int[] input, int start, int end) {
    int maxPro = 1;
    for (int index = start; index < end; index++) {
      maxPro *= input[index];
    }
    return maxPro;
  }
}
