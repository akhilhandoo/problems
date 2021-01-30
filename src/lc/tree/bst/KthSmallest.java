package lc.tree.bst;

import java.util.ArrayList;
import java.util.List;

public class KthSmallest {


    public static int kthSmallest(TreeNode root, int k) {
        List<Integer> collector = new ArrayList<>();
        collectInOrder(root, collector);
        return collector.get(k - 1);
    }

    public static void collectInOrder (TreeNode node, List<Integer> collector) {
        if (null == node) {
            return;
        } else {
            collectInOrder(node.left, collector);
            collector.add(node.val);
            collectInOrder(node.right, collector);
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {
        }
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
