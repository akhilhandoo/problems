package algo;

import java.util.*;

public class MinJumps {

  public static void main(String[] args) {
    int[] input = new int[]{3, 1, 3, -1, 2, 0};
    System.out.println(new MinJumps().minJumps(input));
  }

  //  [3, 4, 3, -1, 2, 0]
  public int minJumps(int[] input) {
    int[] results = new int[input.length];
    Map<Integer, List<Integer>> options = new HashMap<>();
    //  No moves for 0
    //  Single move to reach 1 step.
    results[0] = 0;
    results[1] = 1;


    //  options[3] = [1]
    //  options[5] = [2, ]
    for (int index = 0; index < input.length; index++) {
      if (index >= 2) {
        results[index] = processResults(options, index, results[index - 1] + 1);
      }
      registerOption(options, index + input[index], results[index] + 1);
    }
    return results[input.length - 1];
  }

  private void registerOption(Map<Integer, List<Integer>> options, int indexToRegister, int value) {
    List<Integer> possibilities = options.get(indexToRegister);
    if (null == possibilities) {
      possibilities = new ArrayList<>();
    }
    possibilities.add(value);
    options.put(indexToRegister, possibilities);
  }

  private int processResults(Map<Integer, List<Integer>> options, int indexToProcess, int newValue) {
    List<Integer> possibilities = options.get(indexToProcess);
    if (null == possibilities) {
      possibilities = new ArrayList<>();
    }
    possibilities.add(newValue);
    return Collections.min(possibilities);
  }
}
