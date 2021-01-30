package lc.tree.bst;

import java.util.ArrayList;
import java.util.List;

public class InorderTraversal {

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> collector = new ArrayList<>();
        inorderTravellerCollector(root, collector);
        return collector;
    }

    public static void inorderTravellerCollector(TreeNode node, List<Integer> collector) {
        if (null == node) {
            return;
        } else {
            inorderTravellerCollector(node.left, collector);
            collector.add(node.val);
            inorderTravellerCollector(node.right, collector);
        }
    }


    public class TreeNode {
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
}
