package algo.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SameBST {

    public static void main(String[] args) {
        int[] intOne = new int[]{10, 15, 8, 12, 94, 81, 5, 2, -1, 100, 45, 12, 8, -1, 8, 2, -34};
        int[] intTwo = new int[]{10, 8, 5, 15, 2, 12, 94, 81, -1, -1, -34, 8, 2, 8, 12, 45, 100};
        List<Integer> listOne = new ArrayList<>();
        for (int item: intOne) {
            listOne.add(item);
        }
        List<Integer> listTwo = new ArrayList<>();
        for (int item: intTwo) {
            listTwo.add(item);
        }
        System.out.println(SameBST.sameBsts(listOne, listTwo));
    }

    public static boolean sameBsts(List<Integer> arrayOne, List<Integer> arrayTwo) {
        if ((null == arrayOne && null == arrayTwo) || (arrayOne.size() == 0 && arrayTwo.size() == 0)) {
            return true;
        } else if (null != arrayOne && arrayOne.size() > 0 && null != arrayTwo && arrayTwo.size() > 0) {
            if (arrayOne.size() == arrayTwo.size()) {
                Integer one = arrayOne.remove(0);
                Integer two = arrayTwo.remove(0);
                if (one.equals(two)) {
                    return SameBST.sameBsts(
                            getLeft(arrayOne, one),
                            getLeft(arrayTwo, two))
                            &&
                            SameBST.sameBsts(
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
        return list.stream().filter(x -> x >= root).collect(Collectors.toList());
    }
}
