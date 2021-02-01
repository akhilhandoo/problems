package algo.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SingleCycleCheck {

  public static void main(String[] args) {
    int[] input = new int[]{2, 3, 1, -4, -4, 2};
    System.out.println(SingleCycleCheck.hasSingleCycle(input));
  }

  public static boolean hasSingleCycle(int[] array) {
    int count = 0;
    int index = 0;
    Set<Integer> toCover = IntStream.range(0, array.length).boxed().collect(Collectors.toSet());
    while (count < array.length) {
      index = wrappedJump(index, array.length - 1, array[index]);
      toCover.remove(index);
      count++;
    }
    if (index == 0 && toCover.size() == 0) {
      return true;
    } else {
      return false;
    }
  }

  private static int wrappedJump(int current, int maxIndex, int moveBy) {
    int reducedMoveBy = Math.abs(moveBy) % (maxIndex + 1);
    if (moveBy < 0) {
      reducedMoveBy = reducedMoveBy * -1;
    }
    int newIndex = current + reducedMoveBy;
    if (newIndex > maxIndex) {
      newIndex = newIndex - maxIndex - 1;
    } else if (newIndex < 0) {
      newIndex = (maxIndex + 1) - Math.abs(newIndex);
    }
    return newIndex;
  }
}
