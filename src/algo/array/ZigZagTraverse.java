package algo.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ZigZagTraverse {

  public static void main(String[] args) {
    List<List<Integer>> matrix = new ArrayList<>();
    matrix.add(new ArrayList<>());
    Collections.addAll(matrix.get(0), new Integer[]{1});
    matrix.add(new ArrayList<>());
    Collections.addAll(matrix.get(1), new Integer[]{2});
    matrix.add(new ArrayList<>());
    Collections.addAll(matrix.get(2), new Integer[]{3});
    matrix.add(new ArrayList<>());
    Collections.addAll(matrix.get(3), new Integer[]{4});

    List<Integer> flattened = ZigZagTraverse.zigzagTraverse(matrix);
    System.out.println(flattened);
  }

  public static List<Integer> zigzagTraverse(List<List<Integer>> array) {
    if (array.size() == 1) {
      return array.get(0);
    } else {
      List<Integer> toReturn = new ArrayList<>();
      toReturn.add(array.get(0).get(0));
      int count = array.size() * array.get(0).size();
      int index = 1;
      boolean leftToRight = true;
      int i = 1;
      int j = 0;
      while (index++ < count) {
        boolean directionChanged = false;
        toReturn.add(array.get(i).get(j));
        if (leftToRight) {
          i--;
          j++;
        } else {
          i++;
          j--;
        }

        if (i < 0) {
          if (j != array.get(0).size()) {
            i = 0;
          }
          if (!directionChanged) {
            leftToRight = !leftToRight;
            directionChanged = true;
          }
        } else if (i == array.size()) {
          i = array.size() - 1;
          j = j + 2;
          if (!directionChanged) {
            leftToRight = !leftToRight;
            directionChanged = true;
          }
        }

        if (j < 0) {
          j = 0;
          if (!directionChanged) {
            leftToRight = !leftToRight;
          }
        } else if (j == array.get(0).size()) {
          j = array.get(0).size() - 1;
          i = i + 2;
          if (!directionChanged) {
            leftToRight = !leftToRight;
          }
        }
      }
      return toReturn;
    }
  }
}
