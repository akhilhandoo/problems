package algo.bst;

public class BSTValidation {

  public static boolean validateBst(BST tree) {
    return validateRecursively(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static boolean validateRecursively(BST node, int min, int max) {
    if (null == node) {
      return true;
    } else {
      return node.value >= min && node.value < max &&
              validateRecursively(node.left, min, node.value) &&
              validateRecursively(node.right, node.value, max);
    }
  }

  static class BST {
    public int value;
    public BST left;
    public BST right;

    public BST(int value) {
      this.value = value;
    }
  }
}
