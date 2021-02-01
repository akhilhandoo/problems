package algo.bst;

public class ClosestNode {

  public static void main(String[] agrs) {

    BST root = new BST(10);
    BST node1 = new BST(5);
    BST node2 = new BST(15);
    root.left = node1;
    root.right = node2;
    BST node3 = new BST(2);
    BST node4 = new BST(6);
    node1.left = node3;
    node1.right = node4;
    BST node5 = new BST(13);
    BST node6 = new BST(22);
    node2.left= node5;
    node2.right = node6;
    BST node7 = new BST(1);
    node3.left = node7;
    BST node8 = new BST(14);
    node5.right=node8;

    System.out.println(ClosestNode.findClosestValueInBst(root, 12));


  }

  public static int findClosestValueInBst(BST tree, int target) {
    return closest(tree, target, Integer.MAX_VALUE);
  }

  private static int closest(BST node, int target, int closestTillNow) {
    if (null == node) {
      return closestTillNow;
    }
    int differenceTillNow = Math.abs(target - closestTillNow);
    int currentNodeDifference = Math.abs(target - node.value);
    boolean currentIsCloser = currentNodeDifference < differenceTillNow;
    BST nodeToProceed = null;
    if (target < node.value) {
      nodeToProceed = node.left;
    } else if (target > node.value) {
      nodeToProceed = node.right;
    } else {
      return target;
    }
    if (currentIsCloser) {
      return closest(nodeToProceed, target, node.value);
    } else {
      return closest(nodeToProceed, target, closestTillNow);
    }
  }
}

class BST {
  public int value;
  public BST left;
  public BST right;

  public BST(int value) {
    this.value = value;
  }
}
