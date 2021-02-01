package search;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MatrixWordSearch {

  public static void main(String[] args) {
    char[][] board = new char[][]{
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
    };
    MatrixWordSearch wordSearch = new MatrixWordSearch();
    System.out.println("Board has the word `ABCCED` => " + wordSearch.exist(board, "ABCCED"));
    System.out.println("Board has the word `SEE` => " + wordSearch.exist(board, "SEE"));
    System.out.println("Board has the word `ABCB` => " + wordSearch.exist(board, "ABCB"));
  }

  public boolean exist(char[][] board, String word) {
    List<Point> possibleStartingPoints = matches(board, word.charAt(0));
    for (Point possibleStartingPoint: possibleStartingPoints) {
      Set<Point> accounted = new HashSet<>();
      accounted.add(possibleStartingPoint);
      if (hasThisNeighbour(board, possibleStartingPoint.x, possibleStartingPoint.y, word, accounted, 1)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasThisNeighbour(char[][] board, int x, int y, String word, Set<Point> included, int k) {
    if (k >= word.length()) {
      return true;
    } else {
      boolean up = false;
      if (isValidPosition(x - 1, y, board.length, board[0].length)) {
        Point p = new Point(x - 1, y);
        if (!included.contains(p) && board[x - 1][y] == word.charAt(k)) {
          Set<Point> copied = new HashSet<>(included);
          copied.add(p);
          up = hasThisNeighbour(board, x - 1, y, word, copied, k + 1);
        }
      }

      if (!up) {
        boolean left = false;
        if (isValidPosition(x, y - 1, board.length, board[0].length)) {
          Point p = new Point(x, y - 1);
          if (!included.contains(p) && board[x][y - 1] == word.charAt(k)) {
            Set<Point> copied = new HashSet<>(included);
            copied.add(p);
            left = hasThisNeighbour(board, x, y - 1, word, copied, k + 1);
          }
        }

        if (!left) {
          boolean right = false;
          if (isValidPosition(x, y + 1, board.length, board[0].length)) {
            Point p = new Point(x, y + 1);
            if (!included.contains(p) && board[x][y + 1] == word.charAt(k)) {
              Set<Point> copied = new HashSet<>(included);
              copied.add(p);
              right = hasThisNeighbour(board, x, y + 1, word, copied, k + 1);
            }
          }

          if (!right) {
            boolean down = false;
            if (isValidPosition(x + 1, y, board.length, board[0].length)) {
              Point p = new Point(x + 1, y);
              if (!included.contains(p) && board[x + 1][y] == word.charAt(k)) {
                Set<Point> copied = new HashSet<>(included);
                copied.add(p);
                down = hasThisNeighbour(board, x + 1, y, word, copied, k + 1);
              }
            }
            if (down) {
              return true;
            } else {
              return false;
            }
          } else {
            return true;
          }
        } else {
          return true;
        }
      } else {
        return true;
      }
    }
  }

  public boolean isValidPosition(int x, int y, int boardHeight, int boardWidth) {
    return x >= 0 && x < boardHeight && y >= 0 && y < boardWidth;
  }

  public List<Point> matches(char[][] board, char first) {
    List<Point> toReturn = new ArrayList<>();
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[0].length; col++) {
        if (board[row][col] == first) {
          toReturn.add(new Point(row, col));
        }
      }
    }
    return toReturn;
  }
}
