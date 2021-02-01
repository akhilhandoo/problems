package algo.dp;

import java.util.ArrayList;
import java.util.List;

public class LongestCommonSubsequence {

  public static void main(String[] args) {
    List<Character> result = longestCommonSubsequence("ZXVVYZW", "XKYKZPW");
    StringBuilder builder = new StringBuilder();
    for (Character c: result) {
      builder.append(c);
    }
    System.out.println(builder.toString());
  }

  public static List<Character> longestCommonSubsequence(String str1, String str2) {
    if (null == str1 || str1.length() == 0 || null == str2 || str2.length() == 0) {
      return new ArrayList<Character>();
    }
    String[][] data = new String[str1.length() + 1][str2.length() + 1];
    for (int i = 1; i <= str1.length(); i++) {
      for (int j = 1; j <= str2.length(); j++) {
        if (str1.charAt(i-1) == str2.charAt(j-1)) {
          if (null == data[i-1][j-1]) {
            data[i][j] = "" + str1.charAt(i-1);
          } else {
            data[i][j] = data[i-1][j-1] + str1.charAt(i-1);
          }
        } else {
          if (strlen(data[i-1][j]) > strlen(data[i][j-1])) {
            data[i][j] = data[i-1][j];
          } else {
            if (null != data[i][j-1]) {
              data[i][j] = data[i][j - 1];
            } else {
              data[i][j] = "";
            }
          }
        }
      }
    }
    List<Character> toReturn = new ArrayList<>();
    for (char ch: data[str1.length()][str2.length()].toCharArray()) {
      toReturn.add(ch);
    }
    return toReturn;
  }

  private static int strlen(String s) {
    if (null == s) {
      return 0;
    } else {
      return s.length();
    }
  }
}
