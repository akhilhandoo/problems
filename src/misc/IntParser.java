package misc;

import java.util.regex.Pattern;

public class IntParser {

  public static void main(String[] args) {
//    int number = IntParser.myAtoi("-91283472332");
//    System.out.println(" Number => " + number);

    Pattern pattern = Pattern.compile("^[\\w]+[\\w-]*");

    if (pattern.matcher("").matches()) {
      System.out.println("Matches");
    } else {
      System.out.println("Sorry");
    }

  }

  public static int myAtoi(String str) {
    String sanitized = str.trim();
    int toReturn = 0;
    boolean firstChar = true;
    boolean isNegative = false;
    boolean error = false;
    boolean integralNumberFound = false;
    boolean overFlowDetected = false;
    for (char ch: sanitized.toCharArray()) {
      if (firstChar) {
        if (ch == '-') {
          isNegative = true;
        } else if (ch == '+') {
          isNegative = false;
        } else if (ch >= '0' && ch <= '9') {
          toReturn = (toReturn * 10) + (((int)ch) - 48);
          integralNumberFound = true;
        } else {
          error = true;
          break;
        }
        firstChar = false;
        continue;
      } else {
        if (ch >= '0' && ch <= '9') {
          try {
            int promoted = Math.multiplyExact(toReturn, 10);
            toReturn = Math.addExact(promoted, (((int) ch) - 48));
            integralNumberFound = true;
          } catch (ArithmeticException ae) {
            overFlowDetected = true;
            break;
          }
        } else {
          if (!integralNumberFound) {
            error = true;
          }
          break;
        }
      }
    }
    if (overFlowDetected) {
      return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    } else if (error) {
      return 0;
    } else {
      return isNegative ? -1 * toReturn : toReturn;
    }
  }
}
