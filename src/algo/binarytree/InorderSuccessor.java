package algo.binarytree;

public class InorderSuccessor {

    public static void main(String[] args) {

//        BinaryTree six = new BinaryTree(6);
//        BinaryTree five = new BinaryTree(5);
//
//        BinaryTree four = new BinaryTree(4);
//        four.left = six;
//        six.parent = four;
//
//        BinaryTree two = new BinaryTree(2);
//        two.left = four;
//        four.parent = two;
//        two.right = five;
//        five.parent = two;

        BinaryTree one = new BinaryTree(1);
//        one.left = two;
//        two.parent = one;

//        BinaryTree three = new BinaryTree(3);
//        one.right = three;
//        three.parent = one;

//        BinaryTree successor = new InorderSuccessor().findSuccessor(one, six);
//        System.out.println(successor);
        new InorderSuccessor().printInorderSuccessor(one, one);
    }

    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;
        public BinaryTree parent = null;

        public BinaryTree(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "BinaryTree{" +
                    "value=" + value +
                    '}';
        }
    }

    static class NodeHolder {
        public BinaryTree nodeToReturn = null;
    }

    public void printInorderSuccessor(BinaryTree root, BinaryTree node) {
        if (null == node) {
            return;
        } else {
            printInorderSuccessor(root, node.left);
            System.out.println(node + " => " + findSuccessor(root, node));
            printInorderSuccessor(root, node.right);
        }
    }

    public BinaryTree findSuccessor(BinaryTree tree, BinaryTree node) {
        NodeHolder holder = new NodeHolder();
        findSuccessorHelper(tree, node, holder, null);
        return holder.nodeToReturn;
    }

    public BinaryTree getFirstInOrder(BinaryTree root) {
        if (null != root.left) {
            return getFirstInOrder(root.left);
        } else {
            return root;
        }
    }

    public void findSuccessorHelper(BinaryTree currentNode, BinaryTree nodeToLookFor, NodeHolder holder, BinaryTree tookRightFrom) {
        if (null == currentNode) {
            return;
        } else if (currentNode == nodeToLookFor) {
            if (null != currentNode.right) {
                holder.nodeToReturn = getFirstInOrder(currentNode.right);
            } else if (null != currentNode.parent) {
                if (currentNode == currentNode.parent.left) {
                    holder.nodeToReturn = currentNode.parent;
                } else {
                    if (null != tookRightFrom) {
                        holder.nodeToReturn = tookRightFrom.parent;
                    } else {
                        holder.nodeToReturn = null;
                    }
                }
            }
        } else {
            findSuccessorHelper(currentNode.left, nodeToLookFor, holder, tookRightFrom);
            findSuccessorHelper(currentNode.right, nodeToLookFor, holder, currentNode);
        }
    }
}
