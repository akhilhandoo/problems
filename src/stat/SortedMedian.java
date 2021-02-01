package stat;

public class SortedMedian {

  public static void main(String[] args) {
    int nums1[] = new int[]{1, 2};
    int nums2[] = new int[]{3, 4};

    System.out.println(SortedMedian.findMedianSortedArrays(nums1, nums2));
  }

  public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int[] merged = new int[nums1.length + nums2.length];

    int pointer1 = 0;
    int pointer2 = 0;
    int index = 0;

    while (pointer1 < nums1.length || pointer2 < nums2.length) {
      if (pointer1 >= nums1.length) {
        merged[index++] = nums2[pointer2++];
      } else if (pointer2 >= nums2.length) {
        merged[index++] = nums1[pointer1++];
      } else {
        if (nums1[pointer1] <= nums2[pointer2]) {
          merged[index++] = nums1[pointer1++];
        } else {
          merged[index++] = nums2[pointer2++];
        }
      }
    }

    if (merged.length % 2 == 0) {
      return (merged[(merged.length / 2) - 1] + merged[merged.length / 2]) / 2.0;
    } else {
      return merged[merged.length / 2];
    }
  }
}
