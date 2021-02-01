package algo.bst;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SameBST {

  public static void main(String[] args) {
//    List<Integer> listOne = new LinkedList<>(Arrays.asList(10, 15, 8, 12, 94, 81, 5, 2, 11));
//    List<Integer> listTwo = new LinkedList<>(Arrays.asList(10, 8, 5, 15, 2, 12, 11, 94, 81));

    List<Integer> listOne = new LinkedList<>(Arrays.asList(25, 31, 9, 12, 29, 11, 40, 17));
    List<Integer> listTwo = new LinkedList<>(Arrays.asList(25, 12, 9, 31, 17, 29, 40, 11));

    System.out.println(SameBST.sameBsts(listOne, listTwo));
  }

  public static boolean sameBsts(List<Integer> arrayOne, List<Integer> arrayTwo) {
    if (arrayOne.size() == arrayOne.stream().collect(Collectors.toSet()).size()
            && arrayTwo.size() == arrayTwo.stream().collect(Collectors.toSet()).size()) {
      return sameBstsHelper(arrayOne, arrayTwo);
    } else {
      return false;
    }
  }

  public static boolean sameBstsHelper(List<Integer> arrayOne, List<Integer> arrayTwo) {
    if ((null == arrayOne && null == arrayTwo) || (arrayOne.size() == 0 && arrayTwo.size() == 0)) {
      return true;
    } else if (null != arrayOne && arrayOne.size() > 0 && null != arrayTwo && arrayTwo.size() > 0) {
      if (arrayOne.size() == arrayTwo.size()) {
        Integer one = arrayOne.remove(0);
        Integer two = arrayTwo.remove(0);
        if (one.equals(two)) {
          return SameBST.sameBstsHelper(
                  getLeft(arrayOne, one),
                  getLeft(arrayTwo, two))
                  &&
                  SameBST.sameBstsHelper(
                          getRight(arrayOne, one),
                          getRight(arrayTwo, two));
        } else {
          return false;
        }
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  private static List<Integer> getLeft(List<Integer> list, Integer root) {
    return list.stream().filter(x -> x < root).collect(Collectors.toList());
  }

  private static List<Integer> getRight(List<Integer> list, Integer root) {
    return list.stream().filter(x -> x > root).collect(Collectors.toList());
  }
}
