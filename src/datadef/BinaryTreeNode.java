package datadef;

public class BinaryTreeNode<T> extends Node<T> {
  private BinaryTreeNode<T> left;
  private BinaryTreeNode<T> right;
  private int count;

  public BinaryTreeNode(T data) {
    super(data);
    count = 1;
  }

  public BinaryTreeNode<T> getLeft() {
    return left;
  }

  public void setLeft(BinaryTreeNode<T> left) {
    this.left = left;
  }

  public BinaryTreeNode<T> getRight() {
    return right;
  }

  public void setRight(BinaryTreeNode<T> right) {
    this.right = right;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }
}
