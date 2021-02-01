package util;

import datadef.BinaryTreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringJoiner;

public class TreeUtils {

  public static <T> void addToCompleteBinaryTree (BinaryTreeNode<T> root, T data) {
    Queue<BinaryTreeNode<T>> queue = new LinkedList<>();
    queue.add(root);
    BinaryTreeNode<T> refToIncompleteNode = findIncompleteNode(queue);

    BinaryTreeNode<T> temp = new BinaryTreeNode<>(data);
    if (null == refToIncompleteNode.getLeft()) {
      refToIncompleteNode.setLeft(temp);
    } else {
      refToIncompleteNode.setRight(temp);
    }
  }

  public static <T> BinaryTreeNode<T> findIncompleteNode(Queue<BinaryTreeNode<T>> queue) {
    BinaryTreeNode<T> node = queue.remove();
    if (null == node.getLeft() || null == node.getRight()) {
      return node;
    }
    queue.add(node.getLeft());
    queue.add(node.getRight());
    return findIncompleteNode(queue);
  }

  public static <T> String getBreadthFirstIteration(BinaryTreeNode<T> root) {
    StringJoiner sj = new StringJoiner(" , ", "[", "]");
    Queue<BinaryTreeNode<T>> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      BinaryTreeNode<T> node = queue.remove();
      sj.add(node.getData().toString());
      if (null != node.getLeft()) {
        queue.add(node.getLeft());
      }
      if (null != node.getRight()) {
        queue.add(node.getRight());
      }
    }
    return sj.toString();
  }

  public static <T> String getZigZagIteration(BinaryTreeNode<T> root) {
    StringJoiner sj = new StringJoiner(" , ", "[", "]");
    ArrayDeque<BinaryTreeNode<T>> list1 = new ArrayDeque<>();
    ArrayDeque<BinaryTreeNode<T>> list2 = new ArrayDeque<>();
    boolean leftToRight = true;
    ArrayDeque<BinaryTreeNode<T>> refList = list1;
    ArrayDeque<BinaryTreeNode<T>> nextList = list2;
    list1.push(root);
    while (!refList.isEmpty()) {
      BinaryTreeNode<T> node = refList.pop();
      sj.add(node.getData().toString());
      if (leftToRight) {
        if (null != node.getLeft()) {
          nextList.push(node.getLeft());
        }
        if (null != node.getRight()) {
          nextList.push(node.getRight());
        }
      } else {
        if (null != node.getRight()) {
          nextList.push(node.getRight());
        }
        if (null != node.getLeft()) {
          nextList.push(node.getLeft());
        }
      }
      if (refList.isEmpty()) {
        ArrayDeque<BinaryTreeNode<T>> temp = refList;
        refList = nextList;
        nextList = temp;
        leftToRight = !leftToRight;
      }
    }
    return sj.toString();
  }



  public static void main(String[] args) {

    BinaryTreeNode<Integer> ten = new BinaryTreeNode<>(10);
    BinaryTreeNode<Integer> fifteen = new BinaryTreeNode<>(15);

    BinaryTreeNode<Integer> twentyNine = new BinaryTreeNode<>(29);
    twentyNine.setLeft(ten);
    twentyNine.setRight(fifteen);

    BinaryTreeNode<Integer> thirtyOne = new BinaryTreeNode<>(31);

    BinaryTreeNode<Integer> root = new BinaryTreeNode<>(7);

    root.setLeft(twentyNine);
    root.setRight(thirtyOne);

    TreeUtils.addToCompleteBinaryTree(root, 27);

    System.out.println(TreeUtils.getBreadthFirstIteration(root));

    System.out.println(" New Iteration => " + TreeUtils.getZigZagIteration(root));

  }
}
