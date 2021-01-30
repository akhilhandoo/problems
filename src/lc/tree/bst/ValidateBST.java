package lc.tree.bst;

public class ValidateBST {


    public boolean isValidBST(TreeNode root) {
        return validateBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean validateBST(TreeNode node, long min, long max) {
        if (null == node) {
            return true;
        } else return node.val > min && node.val < max &&
                validateBST(node.left, min, node.val) &&
                validateBST(node.right, node.val, max);
    }
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }