package misc;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class Brackets {

  public static void main(String[] args) {

//    Map<String, String> validEnclosures = new HashMap<>();
//    validEnclosures.put("(", ")");
//    validEnclosures.put("{", "}");
//    validEnclosures.put("[", "]");
//
//    System.out.println(Brackets.isValid(validEnclosures, "()"));
//    System.out.println(Brackets.isValid(validEnclosures, "()[]{}"));
//    System.out.println(Brackets.isValid(validEnclosures, "(]"));
//    System.out.println(Brackets.isValid(validEnclosures, "([)]"));
//    System.out.println(Brackets.isValid(validEnclosures, "{[]}"));

//    System.out.println(Brackets.myPow(2.00000, 10));
//    System.out.println(Brackets.myPow(2.10000, 3));
//    System.out.println(Brackets.myPow(2.00000, -2));

//      System.out.println(Brackets.myPow(2.00000, 10));
//      System.out.println(Brackets.myPowR(2.10000, 3));
//      System.out.println(Brackets.myPowR(2.00000, -2));

//    long beginTime = System.currentTimeMillis();
//    System.out.println(Brackets.myPowR(2.00000, -2147483648));
//    long timeTaken = System.currentTimeMillis() - beginTime;
//    System.out.println("It took " + timeTaken + " millis.");

    long beginTime = System.currentTimeMillis();
    System.out.println(Brackets.myPowR(-1.00000, -2147483648));
    long timeTaken = System.currentTimeMillis() - beginTime;
    System.out.println("It took " + timeTaken + " millis.");

//    int xx = -2147483648;
//    int yy = Math.abs(xx);
//
//    System.out.println(" X => " + xx);
//    System.out.println(" Y => " + yy);
//    System.out.println(" MAX => " + Integer.MAX_VALUE);
//    System.out.println(" MIN => " + Integer.MIN_VALUE);

  }

  public static boolean isValid(Map<String, String> validEnclosures, String input) {
    ArrayDeque<String> structure = new ArrayDeque<>();
    for (char ch: input.toCharArray()) {
      if (validEnclosures.keySet().contains("" + ch)) {
        structure.push("" + ch);
      } else {
        String opened = structure.pop();
        if (!validEnclosures.get(opened).equals("" + ch)) {
          return false;
        }
      }
    }
    return structure.isEmpty();
  }

  public static double myPow(double x, int n) {
    if (n == 0 || x == 1.00d) {
      return 1.00d;
    } else {
      double toReturn = 1.00d;
      int exponential = n != Integer.MIN_VALUE ? Math.abs(n) : Integer.MAX_VALUE;
      for (int count = 0; count < exponential; count++) {
        toReturn = toReturn * x;
      }
      if (n > 0) {
        return toReturn;
      } else {
        return 1 / toReturn;
      }
    }
  }

  public static double power(double x, int y) {
    if (y == 0)
      return 1;
    else if (y % 2 == 0)
      return power(x, y / 2) * power(x, y / 2);
    else
      return x * power(x, y / 2) * power(x, y / 2);
  }

  public static double myPowR(double x, int n) {
    Map<Double, Map<Integer, Double>> resultsCache = new HashMap<>();
    return powHelper(x, n, resultsCache);
  }



  public static double powHelper(double x, int n, Map<Double, Map<Integer, Double>> cache) {
    if (n == 0 || x == 1.00d) {
      return 1.00d;
    } else if (null != cache.get(x) && null != cache.get(x).get(n)) {
      return cache.get(x).get(n);
    } else {
      boolean minLimit= false;
      if (n == Integer.MIN_VALUE) {
        minLimit = true;
      }
      int exponential = n > 0 ? n : (n == Integer.MIN_VALUE ? Integer.MAX_VALUE : Math.abs(n));
      if (exponential == 1) {
        return n > 0 ? x : 1 / x;
      } else {
        double result = exponential % 2 == 0 ?
           powHelper(x, exponential/2, cache) * powHelper(x, exponential/2, cache)  :
                x * powHelper(x, exponential/2, cache) * powHelper(x, exponential/2, cache);
        if (minLimit) {
          result = result * x;
        }
        Map<Integer, Double> resultsCacheForX = cache.get(x);
        if (null == resultsCacheForX) {
          resultsCacheForX = new HashMap<>();
        }
        resultsCacheForX.put(n, result);
        cache.put(x, resultsCacheForX);
        if (n > 0) {
          return result;
        } else {
          return 1 / result;
        }
      }
    }
  }
}
