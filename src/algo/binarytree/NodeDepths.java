package algo.binarytree;

public class NodeDepths {

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

  public static int nodeDepths(BinaryTree root) {
    return sumDepth(root, 0);
  }

  private static int sumDepth(BinaryTree node, int depth) {
    if (null == node) {
      return 0;
    } else {
      return depth + sumDepth(node.left, depth + 1) + sumDepth(node.right, depth + 1);
    }
  }

}
