package algo.array;

import java.util.List;

public class ValidateSubsequence {

  public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
    int sequenceIndex = 0;
    for (int item: array) {
      if (item == sequence.get(sequenceIndex)) {
        sequenceIndex++;
      }
      if (sequenceIndex == sequence.size()) {
        return true;
      }
    }
    return false;
  }
}
