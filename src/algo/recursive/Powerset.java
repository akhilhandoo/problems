package algo.recursive;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Powerset {

  public static void main(String[] args) {
    List<Integer> input = new ArrayList<>();
    input.add(5);
    input.add(6);
    input.add(7);
    List<List<Integer>> powerSet = powerset(input);
    System.out.println(powerSet);
  }

  public static List<List<Integer>> powerset(List<Integer> array) {
    Set<Set<Integer>> accumulator = new HashSet<>();
    powerset(array, accumulator);
    List<List<Integer>> toReturn = new ArrayList<>();
    for (Set<Integer> set: accumulator) {
      toReturn.add(new ArrayList<>(set));
    }
    return toReturn;
  }

  private static void powerset(List<Integer> remaining, Set<Set<Integer>> accumulator) {
    if (remaining.isEmpty() || remaining.size() == 0) {
      accumulator.add(new HashSet<>());
      return;
    } else if (remaining.size() == 1) {
      Set<Integer> last = new HashSet<>();
      last.add(remaining.get(0));
      accumulator.add(last);
      accumulator.add(new HashSet<>());
      return;
    } else {
      Set<Integer> exactSubset = new HashSet<>(remaining);
      accumulator.add(exactSubset);
      for (int index = 0; index < remaining.size(); index++) {
        Set<Integer> individual = new HashSet<>();
        individual.add(remaining.get(index));
        accumulator.add(individual);
        List<Integer> reduced = getReduced(remaining, index);
        powerset(reduced, accumulator);
      }
    }
  }

  private static List<Integer> getReduced(List<Integer> list, int indexToRemove) {
    List<Integer> toReturn = new ArrayList<>();
    for (int index = 0; index < list.size(); index++) {
      if (index != indexToRemove) {
        toReturn.add(list.get(index));
      }
    }
    return toReturn;
  }
}
