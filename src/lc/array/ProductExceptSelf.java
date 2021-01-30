package lc.array;

public class ProductExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length + 1];
        for (int index = 0; index < left.length; index++) {
            left[index] = 1;
        }
        int[] right = new int[nums.length + 1];
        for (int index = 0; index < right.length; index++) {
            right[index] = 1;
        }

        for (int index = 1; index <= nums.length; index++) {
            left[index] = left[index - 1] * nums[index - 1];
        }

        for (int index = nums.length - 1; index >= 0; index--) {
            right[index] = right[index + 1] * nums[index];
        }

        int[] toReturn = new int[nums.length];
        for (int index = 0; index < toReturn.length; index++) {
            toReturn[index] = left[index] * right[index + 1];
        }

        return toReturn;
    }
}
