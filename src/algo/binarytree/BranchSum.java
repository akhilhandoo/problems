package algo.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class BranchSum {

  public static void main(String[] args) {

  }


  public static class BinaryTree {
    int value;
    BinaryTree left;
    BinaryTree right;

    BinaryTree(int value) {
      this.value = value;
      this.left = null;
      this.right = null;
    }
  }

  public static List<Integer> branchSums(BinaryTree root) {
    Queue<Integer> recorder = new ArrayDeque<>();
    recordBranchSum(root, 0, recorder);
    return recorder.stream().collect(Collectors.toList());
  }

  private static void recordBranchSum(BinaryTree node, int currentSum, Queue<Integer> recorder) {
    int totalTillNow = currentSum + node.value;
    if (null == node.left && null == node.right) {
      recorder.add(totalTillNow);
    } else {
      if (null != node.left) {
        recordBranchSum(node.left, totalTillNow, recorder);
      }
      if (null != node.right) {
        recordBranchSum(node.right, totalTillNow, recorder);
      }
    }
  }
}
