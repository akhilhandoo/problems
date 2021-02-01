package simple;

import java.util.stream.IntStream;

public class FizzBuzz {

  public static void main(String[] args) {
    fizzBuzz(20);
  }

  public static void fizzBuzz(int n) {
    IntStream.range(1, n+1).forEach(x -> {
      if (x % 3 == 0 && x % 5 == 0) {
        System.out.println("FizzBuzz");
      } else if (x % 3 == 0) {
        System.out.println("Fizz");
      } else if (x % 5 == 0) {
        System.out.println("Buzz");
      } else {
        System.out.println(x);
      }
    }
    );
  }
}
