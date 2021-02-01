package misc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Permutation {

  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
//    list.add(1);
//    list.add(2);
//    list.add(3);
    System.out.println(getPermutations(list));
  }

  public static List<String> allPermutations(String s) {
    Set<String> accumulator = new HashSet<>();
    getAllPerms("", s, accumulator);
    return accumulator.stream().collect(Collectors.toList());
  }

  public static List<List<Integer>> getPermutations(List<Integer> array) {
    ArrayList<List<Integer>> accumulator = new ArrayList<>();
//    if (array.size() > 0) {
      getAllPerms(new ArrayList<Integer>(), array, accumulator);
//    }
    return accumulator;
  }


  private static void getAllPerms(List<Integer> fixed, List<Integer> variable, ArrayList<List<Integer>> accumulator) {
    if (null == variable || variable.size() == 0) {
      accumulator.add(fixed);
      return;
    } else if (variable.size() == 1) {
      List<Integer> fixedCopy = new ArrayList<>(fixed);
      fixedCopy.add(variable.get(0));
      accumulator.add(fixedCopy);
    } else if (variable.size() == 2) {
      List<Integer> fixedCopy = new ArrayList<>(fixed);
      fixedCopy.add(variable.get(0));
      fixedCopy.add(variable.get(1));
      accumulator.add(fixedCopy);
      fixedCopy = new ArrayList<>(fixed);
      fixedCopy.add(variable.get(1));
      fixedCopy.add(variable.get(0));
      accumulator.add(fixedCopy);
      return;
    } else {
      for (int index = 0; index < variable.size(); index++) {
        List<Integer> exchanged = getExchangedPermute(variable, index);
        List<Integer> fixedCopy = new ArrayList<>(fixed);
        fixedCopy.add(exchanged.get(0));
        exchanged.remove(0);
        getAllPerms(fixedCopy, exchanged, accumulator);
      }
    }
  }

  private static void getAllPerms(String fixed, String variable, Set<String> accumulator) {
    if (null == variable || variable.length() == 0) {
      accumulator.add(fixed);
      return;
    } else if (variable.length() == 1) {
      accumulator.add(fixed + variable);
    } else if (variable.length() == 2) {
      accumulator.add(fixed + variable);
      accumulator.add(fixed + new StringBuilder(variable).reverse().toString());
      return;
    } else {
      for (int index = 0; index < variable.length(); index++) {
        String exchanged = getExchangedPermute(variable, index);
        getAllPerms(fixed + exchanged.charAt(0), exchanged.substring(1), accumulator);
      }
    }
  }

  private static List<Integer> getExchangedPermute(List<Integer> source, int index) {
    if (index == 0) {
      return new ArrayList<>(source);
    } else {
      List<Integer> copy = new ArrayList<>(source);
      int newHead = copy.remove(index);
      int head = copy.remove(0);
      copy.add(index - 1, head);
      copy.add(0, newHead);
      return copy;
    }
  }

  private static String getExchangedPermute(String source, int index) {
    if (index == 0) {
      return source;
    } else {
      return source.charAt(index) + source.substring(1, index) + source.charAt(0) + source.substring(index + 1);
    }
  }
}
