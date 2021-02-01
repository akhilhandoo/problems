package algo.array;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SpiralTraverse {


  public static void main(String[] args) {
//    String line = "1|AAAAAAAABAAAAAAA|1998-01-01|||2450952|NY Metro|large|23|13087|8AM-4PM|Bob Belcher|6|More than other authori|Shared others could not count fully dollars. New members ca|Julius Tran|3|pri|6|cally|730|Ash Hill|Boulevard|Suite 0|Midway|Williamson County|TN|31904|United States|-5|0.11";
//    String[] tokens = line.split("\\|");
//    System.out.println(tokens);

//    Scanner scanner = new Scanner(System.in);
//    System.out.println(" Hello, What's your name ?");
//    String name = scanner.nextLine();
//    long nextLong = 0;
//    do {
//      System.out.println(" Choose a number - ");
//      nextLong = scanner.nextLong();
//      if (nextLong != 0) {
//        long startTime = System.currentTimeMillis();
//        System.out.println(factorial(BigInteger.valueOf(nextLong)).toString());
//        long endTime = System.currentTimeMillis();
//        System.out.println("It took " + (endTime - startTime) + " millis.");
//      }
//    } while (nextLong != 0);
//    System.out.println(" Bye...");

    int[][] array = new int[][] {
            {1, 2, 3, 4, 5, 6},
            {16, 17, 18, 19, 20, 7},
            {15, 24, 23, 22, 21, 8},
            {14, 13, 12, 11, 10, 9}
    };

//    int[][] array = new int[][] {
//            {1, 2, 3, 4},
//            {12, 13, 14, 5},
//            {11, 16, 15, 6},
//            {10, 9, 8, 7}
//    };
    List<Integer> spiralIteration = spiralTraverse(array);
    System.out.println(spiralIteration);
  }

  public static BigInteger factorial(BigInteger number) {
    if (number.equals(BigInteger.ONE)) {
      return BigInteger.ONE;
    } else {
      return number.multiply(factorial(number.subtract(BigInteger.ONE)));
    }
  }

  public static List<Integer> spiralTraverse(int[][] array) {
    List<Integer> toReturn = new ArrayList<>();

    int leftBoundary = 0;
    int rightBoundary = array[0].length - 1;
    int downBoundary = array.length - 1;
    int upBoundary = 1;
    int strength = array.length * array[0].length;
    int x = 0;
    int y = 0;
    int count = 0;
    Direction currentDirection = Direction.RIGHT;
    while (count < strength) {
      toReturn.add(array[x][y]);
      count++;
      if (x == downBoundary && currentDirection == Direction.DOWN) {
        downBoundary = downBoundary - 1;
        currentDirection = currentDirection.getNextDirection();
      } else if (x == upBoundary && currentDirection == Direction.UP) {
        upBoundary = upBoundary + 1;
        currentDirection = currentDirection.getNextDirection();
      } else if (y == rightBoundary && currentDirection == Direction.RIGHT) {
        rightBoundary = rightBoundary - 1;
        currentDirection = currentDirection.getNextDirection();
      } else if (y == leftBoundary && currentDirection == Direction.LEFT) {
        leftBoundary = leftBoundary + 1;
        currentDirection = currentDirection.getNextDirection();
      }
      Cell nextCell = currentDirection.getNextCellGenerator().getNextCell(x, y);
      x = nextCell.getX();
      y = nextCell.getY();
    }
    return toReturn;
  }

  enum Direction {

    RIGHT(new RightNextCellGenerator()),
    DOWN(new DownNextCellGenerator()),
    LEFT(new LeftNextCellGenerator()),
    UP(new UpNextCellGenerator());

    private Direction nextDirection;
    private NextCellGenerator nextCellGenerator;

    Direction(NextCellGenerator nextCellGenerator) {
      this.nextCellGenerator = nextCellGenerator;
    }

    static {
      Direction.RIGHT.nextDirection = Direction.DOWN;
      Direction.DOWN.nextDirection = Direction.LEFT;
      Direction.LEFT.nextDirection = Direction.UP;
      Direction.UP.nextDirection = Direction.RIGHT;
    }

    public Direction getNextDirection() {
      return nextDirection;
    }
    public NextCellGenerator getNextCellGenerator() {
      return this.nextCellGenerator;
    }
  }
}

interface NextCellGenerator {
  public Cell getNextCell(int x, int y);
}

final class RightNextCellGenerator implements NextCellGenerator {
  @Override
  public Cell getNextCell(int x, int y) {
    return new Cell(x, y+1);
  }
}

final class DownNextCellGenerator implements NextCellGenerator {
  @Override
  public Cell getNextCell(int x, int y) {
    return new Cell(x+1, y);
  }
}

final class LeftNextCellGenerator implements NextCellGenerator {
  @Override
  public Cell getNextCell(int x, int y) {
    return new Cell(x, y-1);
  }
}

final class UpNextCellGenerator implements NextCellGenerator {
  @Override
  public Cell getNextCell(int x, int y) {
    return new Cell(x-1, y);
  }
}

final class Cell {
  private int x;
  private int y;

  public Cell (int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}