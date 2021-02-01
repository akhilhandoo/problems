package algo.dp;

public class MinJumps {

  public static void main(String[] args) {
//    int[] array = new int[]{3, 4, 2, 1, 2, 3, 7, 1, 1, 1, 3};
    int[] array = new int[]{5};
    System.out.println(minNumberOfJumpsAlt(array));
  }

  public static int minNumberOfJumps(int[] array) {
    int[] jumps = new int[array.length];
    jumps[0] = 0;
    for (int index = 1; index < array.length; index++) {
      int minJumps = Integer.MAX_VALUE;
      for (int j = 0; j < index; j++) {
        if (index - j <= array[j]) {
          if (jumps[j] < minJumps) {
            minJumps = jumps[j];
          }
        }
      }
      jumps[index] = minJumps + 1;
    }
    return jumps[array.length - 1];
  }

  public static int minNumberOfJumpsAlt(int[] array) {
    if (array.length == 1) {
      return 0;
    }
    int jumps = 0;
    int steps = array[0];
    int maxReach = steps;
    for (int index = 1; index < array.length - 1; index++) {
      maxReach = Math.max(maxReach, index + array[index]);
      steps--;
      if (steps == 0) {
        jumps++;
        steps = maxReach - index;
      }
    }
    return jumps + 1;
  }
}
