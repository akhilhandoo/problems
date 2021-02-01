package algo.binarytree;

public class MirrorImage {

  public static void main(String[] args) {
    BinaryTree root = new BinaryTree(1);
    BinaryTree two = new BinaryTree(2);
    BinaryTree three = new BinaryTree(3);
    BinaryTree four = new BinaryTree(4);
    BinaryTree five = new BinaryTree(5);
    BinaryTree six = new BinaryTree(6);
    BinaryTree seven = new BinaryTree(7);
    BinaryTree eight = new BinaryTree(8);
    BinaryTree nine = new BinaryTree(9);

//    four.left = eight;
//    four.right = nine;

    three.left = seven;
    three.right = four;

    seven.left = eight;

    eight.left = nine;

    four.right = five;

    five.right = six;

//    two.left = four;
//    two.right = five;

    root.left = three;
    root.right = two;

    //invertBinaryTree(root);
    System.out.println(binaryTreeDiameter(root));
  }

  public static void invertBinaryTree(BinaryTree tree) {
    if (null == tree) {
      return;
    } else {
      BinaryTree temp = tree.left;
      tree.left = tree.right;
      tree.right = temp;
      invertBinaryTree(tree.left);
      invertBinaryTree(tree.right);
    }
  }

  public static int binaryTreeDiameter(BinaryTree tree) {
    MaxDiameter maxDiameter = new MaxDiameter();
    binaryTreeDiameterHelper(tree, maxDiameter);
    return maxDiameter.get();
  }

  public static int binaryTreeDiameterHelper(BinaryTree binaryTree, MaxDiameter maxDiameter) {
    if (null == binaryTree) {
      return -1;
    } else {
      int leftPathLength = 1 + binaryTreeDiameterHelper(binaryTree.left, maxDiameter);
      int rightPathLength = 1 + binaryTreeDiameterHelper(binaryTree.right, maxDiameter);
      maxDiameter.setIfExceeds(leftPathLength + rightPathLength);
      if (leftPathLength > rightPathLength) {
        return leftPathLength;
      } else {
        return rightPathLength;
      }
    }
  }

  static class MaxDiameter {
    private int record = 0;

    public void setIfExceeds(int value) {
      if (value > record) {
        record = value;
      }
    }

    public int get() {
      return record;
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
}