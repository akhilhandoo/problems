package algo.binarytree;

public class MaxPathSum {

  public static void main(String[] args) {
    //BinaryTree root = new BinaryTree(1);

//    BinaryTree two = new BinaryTree(2);
//    BinaryTree four = new BinaryTree(4);
//    BinaryTree five = new BinaryTree(5);
//    two.left = four;
//    two.right = five;
//
//    root.left = two;
//
//    BinaryTree three = new BinaryTree(3);
//
//    BinaryTree six = new BinaryTree(6);
//    BinaryTree seven = new BinaryTree(7);
//    three.left = six;
//    three.right = seven;
//
//    root.right = three;

    System.out.println(maxPathSum(null));
  }

  public static int maxPathSum(BinaryTree tree) {
    if (null == tree) {
      return 0;
    } else {
      MaxSum leftMax = new MaxSum();
      if (null != tree.left) {
        maxPathSumHelper(tree.left, 0, leftMax);
      }
      MaxSum rightMax = new MaxSum();
      if (null != tree.right) {
        maxPathSumHelper(tree.right, 0, rightMax);
      }
      return leftMax.getMaxSum() + rightMax.getMaxSum() + tree.value;
    }
  }

  public static void maxPathSumHelper(BinaryTree node, int sumTillNow, MaxSum resultHolder) {
    int total = sumTillNow + node.value;
    boolean childPresent = false;
    if (null != node.left) {
      maxPathSumHelper(node.left, total, resultHolder);
      childPresent = true;
    }
    if (null != node.right) {
      maxPathSumHelper(node.right, total, resultHolder);
      childPresent = true;
    }
    if (!childPresent) {
      resultHolder.submitMaxSum(total);
    }
  }

  static class BinaryTree {
    public int value;
    public BinaryTree left;
    public BinaryTree right;

    public BinaryTree(int value) {
      this.value = value;
    }
  }

  static class MaxSum {
    private int maxSum;

    public void submitMaxSum(int possibleCandidate) {
      if (possibleCandidate > maxSum) {
        maxSum = possibleCandidate;
      }
    }

    public int getMaxSum() {
      return maxSum;
    }
  }
}
