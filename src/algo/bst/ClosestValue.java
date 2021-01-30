package algo.bst;

public class ClosestValue {

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

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }
    }
}
