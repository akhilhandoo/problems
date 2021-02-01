package algo.strings;

public class InterweavingStrings {

  public static void main(String[] args) {
//    String one = "algoexpert";
//    String two = "your-dream-job";
//    String three = "your-algodream-expertjob";

//    String one = "algoexpert";
//    String two = "your-dream-job";
//    String three = "ayloguore-xdpreeratm-job";

    String one = "aacaaaa";
    String two = "aaabaaa";
    String three = "aaaacabaaaaaaa";

    System.out.println(interweavingStrings(one, two, three));
  }

  public static boolean interweavingStrings(String one, String two, String three) {
    int onePointer = 0;
    int twoPointer = 0;
    int threePointer = 0;
    boolean mismatch = false;
    boolean session = false;
    int virtualCheckPointOne = 0;
    int virtualCheckPointTwo = 0;
    while (onePointer < one.length() && twoPointer < two.length()) {
      if (three.charAt(threePointer) == one.charAt(onePointer) && three.charAt(threePointer) == two.charAt(twoPointer)) {
        if (!session) {
          session = true;
          virtualCheckPointOne = onePointer;
          virtualCheckPointTwo = twoPointer;
        }
        onePointer++;
        twoPointer++;
      } else if (three.charAt(threePointer) == one.charAt(onePointer)) {
        onePointer++;
        if (session) {
          twoPointer = virtualCheckPointTwo;
          session = false;
        }
      } else if (three.charAt(threePointer) == two.charAt(twoPointer)) {
        twoPointer++;
        if (session) {
          onePointer = virtualCheckPointOne;
          session = false;
        }
      } else {
        if (onePointer != twoPointer) {
          mismatch = true;
          break;
        } else {
          session = false;
        }
      }
      threePointer++;
    }
    if (mismatch) {
      return false;
    } else if(onePointer == one.length()) {
      while (twoPointer < two.length()) {
        if (three.charAt(threePointer) == two.charAt(twoPointer)) {
          twoPointer++;
        } else {
          mismatch = true;
          break;
        }
        threePointer++;
      }
    } else {
      while (onePointer < one.length()) {
        if (three.charAt(threePointer) == one.charAt(onePointer)) {
          onePointer++;
        } else {
          mismatch = true;
          break;
        }
        threePointer++;
      }
    }
    return !mismatch;
  }
}
