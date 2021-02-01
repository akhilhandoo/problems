package algo.array;

public class LongestPeak {


  public static void main(String[] args) {
    int[] array = new int[]{1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3};

//    int[] array = new int[]{5, 4, 3, 2, 1, 2, 10, 12};
    System.out.println(longestPeak(array));
  }

  public static int longestPeak(int[] array) {
    if (array.length < 3) {
      return 0;
    }
    char[] condensed = new char[array.length - 1];
    for (int index = 1; index < array.length; index++) {
      if (array[index] > array[index - 1]) {
        condensed[index - 1] = '+';
      } else if (array[index] < array[index - 1]) {
        condensed[index - 1] = '-';
      } else {
        condensed[index -1] = '=';
      }
    }
    int maxLength = -1;
    int beginAt = -1;
    String lookingFor = "+";
    for (int index = 0; index < condensed.length; index++) {
      if (lookingFor.contains("" + condensed[index])) {
        if (lookingFor.equals("+")) {
          beginAt = index;
          lookingFor = "+-";
        } else if (lookingFor.equals("+-")) {
          if (condensed[index] == '-') {
            lookingFor = "-";
            int length = index - beginAt;
            if (length > maxLength) {
              maxLength = length;
            }
          }
        } else {
          int length = index - beginAt;
          if (length > maxLength) {
            maxLength = length;
          }
        }
      } else {
        if (condensed[index] == '=' || condensed[index] == '-') {
          beginAt = -1;
          lookingFor = "+";
        } else {
          beginAt = index;
          lookingFor = "+-";
        }
      }
    }
    if (maxLength > 0) {
      return maxLength + 2;
    } else {
      return 0;
    }
  }
}
