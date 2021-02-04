package algo.bst;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinHeightBST {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(new Integer[]{1, 2, 5, 7, 10, 13, 14, 15, 22});
        BST root = minHeightBst(list);
        System.out.println(root);
    }

    public static BST minHeightBst(List<Integer> array) {
        return electRootAndMakeTreeFrom(array, 0, array.size() - 1);
    }

    public static BST electRootAndMakeTreeFrom(List<Integer> list, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            return null;
        } else if (startIndex == endIndex) {
            return new BST(list.get(startIndex));
        } else {
            int indexOfRoot = startIndex + (endIndex - startIndex) / 2;
            BST toReturn = new BST(list.get(indexOfRoot));
            toReturn.left = electRootAndMakeTreeFrom(list, startIndex, indexOfRoot - 1);
            toReturn.right = electRootAndMakeTreeFrom(list, indexOfRoot + 1, endIndex);
            return toReturn;
        }
    }

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return "BST{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }

        public void insert(int value) {
            if (value < this.value) {
                if (left == null) {
                    left = new BST(value);
                } else {
                    left.insert(value);
                }
            } else {
                if (right == null) {
                    right = new BST(value);
                } else {
                    right.insert(value);
                }
            }
        }
    }
}
