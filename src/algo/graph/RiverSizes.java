package algo.graph;

import java.util.*;

public class RiverSizes {

  public static void main(String[] args) {
//    int[][] array = new int[][] {
//            {1, 0, 0, 1, 0},
//            {1, 0, 1, 0, 0},
//            {0, 0, 1, 0, 1},
//            {1, 0, 1, 0, 1},
//            {1, 0, 1, 1, 0}
//    };

//    int[][] array = new int[][] {
//            {1}
//    };
//    System.out.println(riverSizes(array));

    String test = "sdff|sdfsd|dasfdf";
    String[] tokens = test.split("\\|");
    for (String token: tokens) {
      System.out.println("Token - " + token);
    }
  }

  public static List<Integer> riverSizes(int[][] matrix) {
    List<Integer> toReturn = new ArrayList<>();
    Set<Map.Entry<Integer, Integer>> seen = new HashSet<>();
    for (int row = 0; row < matrix.length; row++) {
      for (int column = 0; column < matrix[row].length; column++) {
        int size = recordRiverSizeAt(matrix, row, column, 1, seen);
        if (size > 0) {
          toReturn.add(size);
        }
      }
    }
    return toReturn;
  }

  public static int recordRiverSizeAt(int[][] matrix, int i, int j, int sizeTillNow, Set<Map.Entry<Integer, Integer>> alreadyRecorded) {
    if (alreadyRecorded.contains(new AbstractMap.SimpleImmutableEntry<>(i, j))) {
      return sizeTillNow - 1;
    } else {
      if (matrix[i][j] == 0) {
        alreadyRecorded.add(new AbstractMap.SimpleImmutableEntry<>(i, j));
        return sizeTillNow - 1;
      } else {
        alreadyRecorded.add(new AbstractMap.SimpleImmutableEntry<>(i, j));
        int right = isValid(matrix, i, j + 1) ? recordRiverSizeAt(matrix, i, j + 1, sizeTillNow + 1, alreadyRecorded) : sizeTillNow;
        int down = isValid(matrix, i + 1, j) ? recordRiverSizeAt(matrix, i + 1, j, sizeTillNow + 1, alreadyRecorded) : sizeTillNow;
        return right > down ? right : down;
      }
    }
  }

  public static boolean isValid(int[][] matrix, int i, int j) {
    return i >= 0 && i < matrix.length &&
            j >= 0 && j < matrix[i].length;
  }

}
