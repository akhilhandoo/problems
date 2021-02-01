package algo.dp;

public class WaterArea {

  public static void main(String[] args) {
//    int[] input = new int[]{0, 8, 0, 0, 5, 0, 0, 10, 0, 0, 1, 1, 0, 3};
    int[] input = new int[]{};
    System.out.println(waterArea(input));
  }

  public static int waterArea(int[] heights) {

    if (heights.length == 0) {
      return 0;
    }
    int[] maxLeft = new int[heights.length];
    int[] maxRight = new int[heights.length];

    int maxTillNow = heights[0];
    maxLeft[0] = maxTillNow;
    for (int index = 1; index < heights.length; index++) {
      if (heights[index] > maxTillNow) {
        maxTillNow = heights[index];
      }
      maxLeft[index] = maxTillNow;
    }

    maxTillNow = heights[heights.length - 1];
    maxRight[heights.length - 1] = maxTillNow;
    for (int index = heights.length - 2; index >= 0; index--) {
      if (heights[index] > maxTillNow) {
        maxTillNow = heights[index];
      }
      maxRight[index] = maxTillNow;
    }

    int totalArea = 0;
    for (int index = 0; index < heights.length; index++) {
      int bound = Math.min(maxLeft[index], maxRight[index]);
      totalArea += bound - heights[index];
    }
    return totalArea;
  }
}
