package datadef.prac;

import datadef.BinaryTreeNode;

import javax.management.InstanceAlreadyExistsException;
import java.util.*;

public class BinarySearchTree<T extends Comparable> {
  private BinaryTreeNode<T> tree;

  public boolean insert (T data) {
    if (null == data) {
      throw new RuntimeException("Cannot append null data to BST.");
    } else {
      try {
        tree = appendChild(tree, data);
        return true;
      } catch (InstanceAlreadyExistsException e) {
        return false;
      }
    }
  }

  public List<T> inorderIterative() {
    List<T> toReturn = new ArrayList<>();
    BinaryTreeNode<T> ref = tree;
    ArrayDeque<BinaryTreeNode> stack = new ArrayDeque<>();
    boolean comingUp = false;
    while (null != ref) {
      if (null != ref.getLeft() && !comingUp) {
        stack.push(ref);
        ref = ref.getLeft();
        comingUp = false;
      } else {
        toReturn.add(ref.getData());
        if (null != ref.getRight()) {
          ref = ref.getRight();
          comingUp = false;
        } else {
          try {
            ref = stack.pop();
          } catch (NoSuchElementException ne) {
            ref = null;
          }
          comingUp = true;
        }
      }
    }
    return toReturn;
  }

  public int size() {
    return tree.getCount();
  }
//
//  private int count(BinaryTreeNode<T> root) {
//    if (null == root) {
//      return 0;
//    } else {
//      return count(root.getLeft()) + 1 + count(root.getRight());
//    }
//  }

  public T minimum() throws IllegalStateException {
    if (null == tree) {
      throw new IllegalStateException("Empty tree.");
    } else {
      return getExtreme(true).getData();
    }
  }

  public T maximum() throws IllegalStateException {
    if (null == tree) {
      throw new IllegalStateException("Empty tree.");
    } else {
      return getExtreme(false).getData();
    }
  }

  public T floor(T base) {
    return _floor(base, tree, null, null, false).getData();
  }

  public T ceiling(T base) {
    return _ceiling(base, tree, null, null, false).getData();
  }

  private BinaryTreeNode<T> _floor(T base, BinaryTreeNode<T> root, BinaryTreeNode<T> parent, BinaryTreeNode<T> grandParent, boolean fromLeft) {
    if (null == root) {
      if (fromLeft) {
        return grandParent;
      } else {
        return parent;
      }
    } else {
      if (base.compareTo(root.getData()) == 0) {
        return root;
      } else if (base.compareTo(root.getData()) < 0) {
        return _floor(base, root.getLeft(), root, parent, true);
      } else {
        return _floor(base, root.getRight(), root, parent, false);
      }
    }
  }

  private BinaryTreeNode<T> _ceiling(T base, BinaryTreeNode<T> root, BinaryTreeNode<T> parent, BinaryTreeNode<T> lastLeft, boolean fromLeft) {
    if (null == root) {
      if (fromLeft) {
        return parent;
      } else {
        return lastLeft;
      }
    } else {
      if (base.compareTo(root.getData()) == 0) {
        return root;
      } else if (base.compareTo(root.getData()) < 0) {
        return _ceiling(base, root.getLeft(), root, root, true);
      } else {
        return _ceiling(base, root.getRight(), root, lastLeft, false);
      }
    }
  }

  private BinaryTreeNode<T> getExtreme(boolean extremeLeft) {
    BinaryTreeNode<T> ref = tree;
    if (extremeLeft) {
      while (null != ref.getLeft()) {
        ref = ref.getLeft();
      }
    } else {
      while (null != ref.getRight()) {
        ref = ref.getRight();
      }
    }
    return ref;
  }

  public String toString() {
    StringJoiner joiner = new StringJoiner(" , ", "[", "]");
    inOrder(tree, joiner);
    return joiner.toString();
  }

  private void inOrder(BinaryTreeNode<T> node, StringJoiner joiner) {
    if (null == node) {
      return;
    }
    if (null != node.getLeft()) {
      inOrder(node.getLeft(), joiner);
    }
    joiner.add(node.getData().toString());
    if (null != node.getRight()) {
      inOrder(node.getRight(), joiner);
    }
  }

  private BinaryTreeNode<T> appendChild(BinaryTreeNode<T> root, T data) throws InstanceAlreadyExistsException {
    if (null == root) {
      return new BinaryTreeNode<>(data);
    } else if (root.getData().equals(data)) {
      throw new InstanceAlreadyExistsException("Given data already present in the tree.");
    } else {
      if (data.compareTo(root.getData()) < 0) {
        root.setLeft(appendChild(root.getLeft(), data));
      } else {
        root.setRight(appendChild(root.getRight(), data));
      }
      int childrenCount = 0;
      if (null != root.getLeft()) {
        childrenCount += root.getLeft().getCount();

      }
      if (null != root.getRight()) {
        childrenCount += root.getRight().getCount();
      }
      root.setCount(1 + childrenCount);
      return root;
    }
  }

  public static void main(String[] args) {
    BinarySearchTree<Integer> intTree = new BinarySearchTree<>();
    boolean inserted = false;
    inserted = intTree.insert(35);
    if (inserted) System.out.println("Inserted " + 35);
    else System.out.println("Couldn't insert " + 35 + ". Already exists.");
    inserted = intTree.insert(23);
    if (inserted) System.out.println("Inserted " + 23);
    else System.out.println("Couldn't insert " + 23 + ". Already exists.");
    inserted = intTree.insert(49);
    if (inserted) System.out.println("Inserted " + 49);
    else System.out.println("Couldn't insert " + 49 + ". Already exists.");
    inserted = intTree.insert(0);
    if (inserted) System.out.println("Inserted " + 0);
    else System.out.println("Couldn't insert " + 0 + ". Already exists.");
    inserted = intTree.insert(12);
    if (inserted) System.out.println("Inserted " + 12);
    else System.out.println("Couldn't insert " + 12 + ". Already exists.");
    inserted = intTree.insert(27);
    if (inserted) System.out.println("Inserted " + 27);
    else System.out.println("Couldn't insert " + 27 + ". Already exists.");
    inserted = intTree.insert(52);
    if (inserted) System.out.println("Inserted " + 52);
    else System.out.println("Couldn't insert " + 52 + ". Already exists.");
    inserted = intTree.insert(16);
    if (inserted) System.out.println("Inserted " + 16);
    else System.out.println("Couldn't insert " + 16 + ". Already exists.");
    inserted = intTree.insert(0);
    if (inserted) System.out.println("Inserted " + 0);
    else System.out.println("Couldn't insert " + 0 + ". Already exists.");
    inserted = intTree.insert(10);
    if (inserted) System.out.println("Inserted " + 10);
    else System.out.println("Couldn't insert " + 10 + ". Already exists.");
    inserted = intTree.insert(5);
    if (inserted) System.out.println("Inserted " + 5);
    else System.out.println("Couldn't insert " + 5 + ". Already exists.");
    inserted = intTree.insert(11);
    if (inserted) System.out.println("Inserted " + 11);
    else System.out.println("Couldn't insert " + 11 + ". Already exists.");
    inserted = intTree.insert(20);
    if (inserted) System.out.println("Inserted " + 20);
    else System.out.println("Couldn't insert " + 20 + ". Already exists.");
    inserted = intTree.insert(22);
    if (inserted) System.out.println("Inserted " + 22);
    else System.out.println("Couldn't insert " + 22 + ". Already exists.");
    inserted = intTree.insert(18);
    if (inserted) System.out.println("Inserted " + 18);
    else System.out.println("Couldn't insert " + 18 + ". Already exists.");
    inserted = intTree.insert(29);
    if (inserted) System.out.println("Inserted " + 29);
    else System.out.println("Couldn't insert " + 29 + ". Already exists.");

    System.out.println("\n\n" + intTree.toString());

//    System.out.println("\nSize => " + intTree.size());
    System.out.println("\nMinimum element => " + intTree.minimum());
    System.out.println("\nMaximum element => " + intTree.maximum());
    System.out.println("\nFloor(25) => " + intTree.floor(25));
    System.out.println("\nCeiling(30) => " + intTree.ceiling(30));
    System.out.println("\nCeiling(28) => " + intTree.ceiling(28));
    System.out.println("\nCeiling(14) => " + intTree.ceiling(14));
    System.out.println("\nCeiling(19) => " + intTree.ceiling(19));

    System.out.println(" Size => " + intTree.size());

    List<Integer> inorder = intTree.inorderIterative();
    StringJoiner joiner = new StringJoiner(", ", "[", "]");
    for (Integer item: inorder) {
      joiner.add("" + item);
    }

    System.out.println("\n\n" + joiner.toString());
  }
}
