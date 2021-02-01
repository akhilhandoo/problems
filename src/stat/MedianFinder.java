package stat;

import util.Heap;

public class MedianFinder {


  public static void main(String[] args) {
//    int[] input = new int[] {20, 15, 30, 9, 19, 3, 27, 40, 10};
    /*int[] input = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    MedianFinder medianFinder = new MedianFinder();
    for (int num: input) {
      System.out.println(" Inserting `" + num + "`");
      medianFinder.addNum(num);
      System.out.println("Done.");
      System.out.println("Median => " + medianFinder.findMedian());
    }*/

    StringBuilder builder = new StringBuilder();
    builder.append("some string " + getNextInt() + " another string");
    System.out.println(builder.toString());
  }

  public static Integer getNextInt() {
    return null;
  }

  private Heap<Integer> topHalf;
  private Heap<Integer> bottomHalf;

  public MedianFinder() {
    topHalf = new Heap<>(Integer.class, Heap.HeapType.MIN);
    bottomHalf = new Heap<>(Integer.class, Heap.HeapType.MAX);
  }

  public void addNum(int num) {
    if (topHalf.getSize() == 0) {
//      System.out.println("Inserting " + num + " in topHalf.");
      topHalf.insert(num);
//      System.out.println("topHalf => " + topHalf.toString());
    } else {
      if (num > topHalf.peek()) {
        if (topHalf.getSize() > bottomHalf.getSize()) {
          int toTransfer = topHalf.remove();
//          System.out.println("Removed " + toTransfer + " from topHalf.");
//          System.out.println("topHalf => " + topHalf.toString());
          bottomHalf.insert(toTransfer);
        }
//        System.out.println("Inserting " + num + " in topHalf.");
        topHalf.insert(num);
//        System.out.println("topHalf => " + topHalf.toString());
      } else if (null != bottomHalf.peek() && num < bottomHalf.peek()) {
        if (bottomHalf.getSize() > topHalf.getSize()) {
          int toTransfer = bottomHalf.remove();
//          System.out.println("Inserting " + toTransfer + " in topHalf.");
          topHalf.insert(toTransfer);
//          System.out.println("topHalf => " + topHalf.toString());
        }
        bottomHalf.insert(num);
      } else if (null == bottomHalf.peek()) {
        bottomHalf.insert(num);
      } else {
        if (topHalf.getSize() > bottomHalf.getSize()) {
          bottomHalf.insert(num);
        } else {
//          System.out.println("Inserting " + num + " in topHalf.");
          topHalf.insert(num);
//          System.out.println("topHalf => " + topHalf.toString());
        }
      }
    }
  }

  public double findMedian() {
    if (topHalf.getSize() > bottomHalf.getSize()) {
      return (double)topHalf.peek();
    } else if (bottomHalf.getSize() > topHalf.getSize()) {
      return (double)bottomHalf.peek();
    } else {
      return (double)(topHalf.peek() + bottomHalf.peek()) / 2.0;
    }
  }
}
