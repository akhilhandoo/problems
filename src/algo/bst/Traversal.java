package algo.bst;

import java.util.ArrayList;
import java.util.List;

public class Traversal {
    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }
    }

    public static List<Integer> inOrderTraverse(BST tree, List<Integer> array) {
        List<Integer> toReturn = new ArrayList<>();
        inOrderHelper(tree, toReturn);
        return toReturn;
    }

    public static void inOrderHelper(BST node, List<Integer> accumulator) {
        if (null == node) {
            return;
        } else {
            inOrderHelper(node.left, accumulator);
            accumulator.add(node.value);
            inOrderHelper(node.right, accumulator);
        }
    }

    public static List<Integer> preOrderTraverse(BST tree, List<Integer> array) {
        List<Integer> toReturn = new ArrayList<>();
        preOrderHelper(tree, toReturn);
        return toReturn;
    }

    public static void preOrderHelper(BST node, List<Integer> accumulator) {
        if (null == node) {
            return;
        } else {
            accumulator.add(node.value);
            preOrderHelper(node.left, accumulator);
            preOrderHelper(node.right, accumulator);
        }
    }

    public static List<Integer> postOrderTraverse(BST tree, List<Integer> array) {
        List<Integer> toReturn = new ArrayList<>();
        postOrderHelper(tree, toReturn);
        return toReturn;
    }

    public static void postOrderHelper(BST node, List<Integer> accumulator) {
        if (null == node) {
            return;
        } else {
            postOrderHelper(node.left, accumulator);
            postOrderHelper(node.right, accumulator);
            accumulator.add(node.value);
        }
    }
}
