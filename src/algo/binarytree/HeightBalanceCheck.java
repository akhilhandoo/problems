package algo.binarytree;

public class HeightBalanceCheck {

    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    public boolean heightBalancedBinaryTree(BinaryTree tree) {
        return recurse(tree).isBalanced;
    }

    public NumberAndBoolean recurse(BinaryTree node) {
        if (null == node) {
            return new NumberAndBoolean(0, true);
        } else {
            NumberAndBoolean left = recurse(node.left);
            NumberAndBoolean right = recurse(node.right);
            if (left.isBalanced && right.isBalanced) {
                int heightDifference = Math.abs(left.height - right.height);
                if (heightDifference == 0 || heightDifference == 1) {
                    return new NumberAndBoolean(Math.max(left.height, right.height) + 1, true);
                } else {
                    return new NumberAndBoolean(Math.max(left.height, right.height) + 1, false);
                }
            } else {
                return new NumberAndBoolean(Math.max(left.height, right.height) + 1, false);
            }
        }
    }

    final static class NumberAndBoolean {

        private int height;
        private boolean isBalanced;

        public NumberAndBoolean(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }

        public int getHeight() {
            return height;
        }

        public boolean isBalanced() {
            return isBalanced;
        }
    }
}
