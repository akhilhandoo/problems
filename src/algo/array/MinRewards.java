package algo.array;

public class MinRewards {

  public static void main(String[] args) {
                          // 1, 3, 2, 1, 2
    int[] scores = new int[]{0, 4, 2, 1, 3};
    System.out.println(minRewards(scores));
  }



  //  00, 01, 02, 03, 04, 05, 06, 07, 08, 09
  //  35, 27, 12, 17, 19, 22, 06, 31, 38, 40
  //  03, 02, 01, 02, 03, 04, 01, 02, 03, 04


  //  00, 01, 02, 03, 04, 05, 06, 07, 08, 09
  //  35, 27, 12, 10, 08, 05, 04, 03, 02, 06
  //  09, 08, 07, 06, 05, 04, 03, 02, 01, 02

  //  00, 01, 02, 03, 04, 05, 06, 07, 08, 09
  //  35, 36, 37, 38, 39, 40, 41, 42, 43, 44
  //  01, 02, 03, 04, 05, 06, 07, 08, 09, 10


  //  0, 1, 2, 3, 4, 5, 6, 7, 8
  //  8, 4, 2, 1, 3, 6, 7, 9, 5
  //  4, 3, 2, 1, 2, 3, 4, 5, 1
  public static int minRewards(int[] scores) {
    //int current = scores[0];
    int lastLocalMinimaAt = -1;
    int rewardsForLastLocalMinima = 0;
    int totalrewards = 0;
    int index = 1;
    while (index < scores.length) {
      if (scores[index] > scores[index - 1]) {
        int numElementsExceptFirst = index - lastLocalMinimaAt - 2;
        if (numElementsExceptFirst > 0) {
          totalrewards += ((numElementsExceptFirst * (numElementsExceptFirst + 1)) / 2);
        }
        if (rewardsForLastLocalMinima > 0) {
          totalrewards += Math.max(rewardsForLastLocalMinima, numElementsExceptFirst) + 1;
        } else {
          totalrewards += numElementsExceptFirst + 1;
        }
        if (lastLocalMinimaAt + 1 == index - 1) {
          rewardsForLastLocalMinima += 1;
        } else {
          rewardsForLastLocalMinima = 1;
        }
        lastLocalMinimaAt = index - 1;
      }
      index++;
    }
    if (lastLocalMinimaAt >= 0) {
      if (scores[scores.length - 1] > scores[scores.length - 2]) {
        totalrewards += rewardsForLastLocalMinima + 1;
      } else {
        //  Scores taper off in decreasing fashion towards end.
        int reverseIndex = scores.length - 1;
        int rewards = 1;
        while (reverseIndex > lastLocalMinimaAt + 1) {
          totalrewards += rewards;
          rewards++;
          reverseIndex--;
        }
        totalrewards += rewardsForLastLocalMinima + 1;
        //totalrewards += 1;
      }
    } else {
      totalrewards = (scores.length * (scores.length + 1)) / 2;
    }
    return totalrewards;
  }
}
