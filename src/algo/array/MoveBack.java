package algo.array;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class MoveBack {

  public static void main(String[] args) {
    List<Integer> input = new ArrayList<>();
    input.add(2);
    input.add(1);
    input.add(2);
    input.add(2);
    input.add(2);
    input.add(3);
    input.add(4);
    input.add(2);
    System.out.println(input);
    moveElementToEnd(input, 2);
    System.out.println(input);
  }

  public static List<Integer> moveElementToEnd(List<Integer> array, int toMove) {
    int first = 0;
    int last = array.size() - 1;
    while (first < last) {
      first = findNext(array, first, toMove);
      last = findPrevious(array, last, toMove);
      if (first < array.size() - 1 && last > 0 && first < last) {
        exchangeInPlace(array, first, last);
      }
    }
    return array;
  }

  private static void exchangeInPlace(List<Integer> array, int sourceIndex, int targetIndex) {
    int valueAtSourceIndex = array.get(sourceIndex);
    array.set(sourceIndex, array.get(targetIndex));
    array.set(targetIndex, valueAtSourceIndex);
  }

  private static int findNext(List<Integer> array, int currentIndex, int lookingFor) {
    for (int index = currentIndex; index < array.size(); index++) {
      if (array.get(index).equals(lookingFor)) {
        return index;
      }
    }
    return array.size();
  }

  private static int findPrevious(List<Integer> array, int currentIndex, int notLookingFor) {
    for (int index = currentIndex; index >= 0; index--) {
      if (!array.get(index).equals(notLookingFor)) {
        return index;
      }
    }
    return -1;
  }
}
