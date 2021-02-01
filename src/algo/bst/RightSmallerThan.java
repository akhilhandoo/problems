package algo.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RightSmallerThan {

  public static void main(String[] args) {
    List<Integer> array = Arrays.asList(new Integer[]{8, 3, 7, 2, 11, 5, 1, 9, 0, 6, 4});
    System.out.println(RightSmallerThan.rightSmallerThan(array));
  }

  public static List<Integer> rightSmallerThan(List<Integer> array) {
    List<Integer> toReturn = new ArrayList<>(array.size());
    List<Integer> inputCopy = new ArrayList<>(array.size());
    inputCopy.addAll(array);
    Collections.reverse(inputCopy);
    MyBST temp = null;
    MyBST root = null;
    for (Integer item: inputCopy) {
      temp = RightSmallerThan.insert(root, item, 0);
      if (null == root) {
        root = temp;
      }
      toReturn.add(temp.keyCount);
    }
    Collections.reverse(toReturn);
    return toReturn;
  }

  public static MyBST insert(MyBST node, int value, int keyCount) {
    if (null == node) {
      return new MyBST(value, 0, keyCount);
    } else {
      MyBST newNode = null;
      if (value < node.value) {
        newNode = insert(node.left, value, keyCount);
        if (null == node.left) {
          node.left = newNode;
        }
        node.leftStrength++;
      } else if (value > node.value) {
        newNode = insert(node.right, value, keyCount + node.leftStrength + 1);
        if (null == node.right) {
          node.right = newNode;
        }
      }
      return newNode;
    }
  }
}

class MyBST {
  int value;
  MyBST left;
  MyBST right;
  int leftStrength;
  int keyCount;

  public MyBST(int value, int leftStrength, int keyCount) {
    this.value = value;
    this.leftStrength = leftStrength;
    this.keyCount = keyCount;
  }
}