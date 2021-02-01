package algo.dp;

import java.util.ArrayList;
import java.util.List;

public class LevenshtienDistance {

  public static void main(String[] args) {
    String s1 = "biting";
    String s2 = "mitten";
    System.out.println("Edit distance => " + editDistance(s1, s2));
  }

  public static int editDistance(String str1, String str2) {
    List<List<Integer>> data = new ArrayList<>();
    for (int i = 0; i <= str1.length(); i++) {
      List<Integer> row = new ArrayList<>();
      for (int j = 0; j <= str2.length(); j++) {
        Integer dataToPut = null;
        if (i == 0 && j == 0) {
          dataToPut = new Integer(0);
        } else if (i == 0) {
          dataToPut = new Integer(row.get(j - 1) + 1);
        } else if (j == 0) {
          dataToPut = new Integer(data.get(i - 1).get(j) + 1);
        } else {
          int min = Math.min(Math.min(data.get(i - 1).get(j), data.get(i - 1).get(j - 1)), row.get(j - 1));
          if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
            dataToPut = new Integer(data.get(i - 1).get(j - 1));
          } else {
            dataToPut = new Integer(min + 1);
          }
        }
        row.add(dataToPut);
      }
      data.add(row);
    }
    return data.get(str1.length()).get(str2.length());
  }
}
