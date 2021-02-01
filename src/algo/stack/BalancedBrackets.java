package algo.stack;

import java.util.*;

public class BalancedBrackets {

  public static void main(String[] args) {
    String input = "hello([india{what(is)hell}])(){}(())world({Q[{phi}]word}laila)isnice)";
    System.out.println(balancedBrackets(input));
  }

  private static Map<String, String> bracketsMap = null;
  private static Set<String> possibleClosingBrackets = null;

  static {
    bracketsMap = new HashMap<>();
    bracketsMap.put("[", "]");
    bracketsMap.put("(", ")");
    bracketsMap.put("{", "}");

    possibleClosingBrackets = new HashSet<>();
    possibleClosingBrackets.add("]");
    possibleClosingBrackets.add(")");
    possibleClosingBrackets.add("}");
  }

  public static boolean balancedBrackets(String str) {
    ArrayDeque<String> stack = new ArrayDeque<>();
    for (char ch: str.toCharArray()) {
      if (bracketsMap.containsKey("" + ch)) {
        stack.push(bracketsMap.get("" + ch));
      } else if (possibleClosingBrackets.contains("" + ch)) {
        if (stack.isEmpty()) {
          return false;
        }
        if (stack.pop().equals("" + ch)) {
          continue;
        } else {
          return false;
        }
      }
    }
    if (stack.isEmpty()) {
      return true;
    } else {
      return false;
    }
  }
}
