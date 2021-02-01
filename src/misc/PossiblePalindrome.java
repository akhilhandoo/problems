package misc;

import java.util.ArrayList;
import java.util.List;

public class PossiblePalindrome {

  public boolean validPalindrome(String s) {

    int left = 0;
    int right = s.length() - 1;
    List<MismatchLocation> mismatches = new ArrayList<>();

    while (left < right) {
      if (s.charAt(left) != s.charAt(right)) {
        mismatches.add(new MismatchLocation(left, right));
      }
      left++;
      right--;
    }

    if (s.length() % 2 == 0) {

    } else {

    }



    if (mismatches.size() > 1) {
      return false;
    } else {
      MismatchLocation mismatchLocation = mismatches.get(0);
      if (s.length() % 2 == 0) {
        if (mismatchLocation.getLeftIndex() + 1 == mismatchLocation.getRightIndex()) {
          return true;
        } else {
          return false;
        }
      }
    }
    return true;
  }
}

class MismatchLocation {
  private int leftIndex;
  private int rightIndex;

  public MismatchLocation(int x, int y) {
    this.leftIndex = x;
    this.rightIndex = y;
  }

  public int getLeftIndex() {
    return leftIndex;
  }

  public int getRightIndex() {
    return rightIndex;
  }
}
