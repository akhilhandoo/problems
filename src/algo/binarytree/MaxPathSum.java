package algo.binarytree;

public class MaxPathSum {

  public static void main(String[] args) {
//    BinaryTree root = new BinaryTree(1);
//
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
//    BinaryTree six = new BinaryTree(10);
//    BinaryTree seven = new BinaryTree(9);
//    three.left = six;
//    three.right = seven;
//
//    root.right = three;

    BinaryTree root = new BinaryTree(1);

    BinaryTree two = new BinaryTree(2);
    BinaryTree minusOne = new BinaryTree(-1);
    root.left = two;
    root.right = minusOne;

    System.out.println(maxPathSum(root));
  }

  public static int maxPathSum(BinaryTree tree) {
    MaxSum recorder = new MaxSum();
    traveller(tree, recorder);
    return recorder.getMaxSum();
  }

  public static int traveller (BinaryTree node, MaxSum recorder) {
    if (null == node) {
      return 0;
    } else {
      int leftPathMax = traveller(node.left, recorder);
      int rightPathMax = traveller(node.right, recorder);
      recorder.submitMaxSum(leftPathMax + node.value);
      recorder.submitMaxSum(rightPathMax + node.value);
      recorder.submitMaxSum(leftPathMax + rightPathMax + node.value);
      return Math.max(leftPathMax + node.value, rightPathMax + node.value);
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
    private int maxSum = Integer.MIN_VALUE;
    private boolean _submissionMade = false;

    public void submitMaxSum(int possibleCandidate) {
      if (possibleCandidate > maxSum) {
        maxSum = possibleCandidate;
      }
      _submissionMade = true;
    }

    public int getMaxSum() {
      if (_submissionMade) {
        return maxSum;
      } else {
        return 0;
      }
    }
  }
}
