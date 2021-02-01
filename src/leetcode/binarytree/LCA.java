package leetcode.binarytree;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;

public class LCA {


  public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    ArrayDeque<TreeNode> pStack = new ArrayDeque<>();
    ArrayDeque<TreeNode> qStack = new ArrayDeque<>();
    traceStack(root, p, pStack);
    traceStack(root, q, qStack);
    TreeNode saved = null;
    while (true) {
      try {
        TreeNode a = pStack.pop();
        TreeNode b = qStack.pop();
        if (a.val == b.val) {
          saved = a;
        } else {
          break;
        }
      } catch (NoSuchElementException noSuchElementException) {
        break;
      }
    }
    return saved;
  }

  public static boolean traceStack(TreeNode currentNode, TreeNode toFind, ArrayDeque<TreeNode> stack) {
    if (null == currentNode) {
      return false;
    } else if (currentNode.val == toFind.val) {
      stack.push(currentNode);
      return true;
    } else {
      boolean foundInLeftSubtree = false || traceStack(currentNode.left, toFind, stack);
      boolean foundInRightSubtree = false || traceStack(currentNode.right, toFind, stack);
      if (foundInLeftSubtree || foundInRightSubtree) {
        stack.push(currentNode);
        return true;
      } else {
        return false;
      }
    }
  }

}

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}