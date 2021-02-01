package algo.binarytree;

public class AllKindsOfNodeDepths {

  public static void main(String[] args) {

    BinaryTree eight = new BinaryTree(8);
    BinaryTree nine = new BinaryTree(9);

    BinaryTree four = new BinaryTree(4);
    four.left = eight;
    four.right = nine;

    BinaryTree five = new BinaryTree(5);

    BinaryTree six = new BinaryTree(6);
    BinaryTree seven = new BinaryTree(7);

    BinaryTree two = new BinaryTree(2);
    two.left = four;
    two.right = five;

    BinaryTree three = new BinaryTree(3);
    three.left = six;
    three.right = seven;

    BinaryTree root = new BinaryTree(1);
    root.left = two;
    root.right = three;

    System.out.println(AllKindsOfNodeDepths.allKindsOfNodeDepths(root));
  }

  public static int allKindsOfNodeDepths(BinaryTree root) {
    return powerNode(root);
  }

  private static int powerNode(BinaryTree node) {
    int total = sumDepth(node, 0);
    if (null != node.left) {
      total += powerNode(node.left);
    }
    if (null != node.right) {
      total += powerNode(node.right);
    }
    return total;
  }

  private static int sumDepth(BinaryTree node, int depth) {
    if (null == node) {
      return 0;
    } else {
      return depth + sumDepth(node.left, depth + 1) + sumDepth(node.right, depth + 1);
    }
  }

  static class BinaryTree {
    int value;
    BinaryTree left;
    BinaryTree right;

    public BinaryTree(int value) {
      this.value = value;
      left = null;
      right = null;
    }
  }
}
