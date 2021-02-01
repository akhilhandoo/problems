package datadef.prac;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Sheep {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    int numCases = scanner.nextInt();

    for (int caseNo = 1; caseNo <= numCases; caseNo++) {
      long result = Sheep.lastNamed(scanner.nextInt());
      System.out.print("Case #" + caseNo + ": ");
      if (result == -1) {
        System.out.println("INSOMNIA");
      } else {
        System.out.println(result);
      }
    }
  }

  public static long lastNamed (int seed) {
    Set<Integer> toCheckOff = IntStream.range(0, 10).boxed().collect(Collectors.toSet());
    int index = 1;
    Set<Long> seen = new HashSet<>();
    while (true) {
      long number = seed * index;
      if (seen.contains(number)) {
        return -1;
      } else {
        seen.add(number);
        toCheckOff.removeAll(getDigits(number));
        if (toCheckOff.size() == 0) {
          return number;
        }
        index++;
      }
    }
  }

  public static Set<Integer> getDigits(long number) {
    Set<Integer> toReturn = new HashSet<>();
    long proxy = number;
    while (proxy != 0) {
      int lastDigit = (int)(proxy % 10);
      toReturn.add(lastDigit);
      proxy = proxy / 10;
    }
    return toReturn;
  }
}
