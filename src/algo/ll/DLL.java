package algo.ll;

public class DLL {

  public Node head;
  public Node tail;

  public void setHead(Node node) {
    //  Find and locate existing node with value node.value
    Node handle = getHandleTo(node);
    if (null != handle) {
      handle.prev.next = handle.next;
      if (null != handle.next) {
        handle.next.prev = handle.prev;
      }
    } else {
      handle = new Node(node.value);
    }
    handle.next = head;
    head.prev = handle;
    head = handle;
  }

  private Node getHandleTo(Node node) {
    Node temp = head;
    while (temp != null) {
      if (temp.value == node.value) {
        break;
      }
      temp = temp.next;
    }
    return temp;
  }

  public void setTail(Node node) {
    Node handle = getHandleTo(node);
    if (null != handle) {
      handle.prev.next = handle.next;
      if (null != handle.next) {
        handle.next.prev = handle.prev;
      }
    } else {
      handle = new Node(node.value);
    }
    handle.prev = tail;
    tail.next = handle;
    tail = handle;
  }

  public void insertBefore(Node node, Node nodeToInsert) {

  }

  public void insertAfter(Node node, Node nodeToInsert) {
    // Write your code here.
  }

  public void insertAtPosition(int position, Node nodeToInsert) {
    // Write your code here.
  }

  public void removeNodesWithValue(int value) {
    // Write your code here.
  }

  public void remove(Node node) {
    // Write your code here.
  }

  public boolean containsNodeWithValue(int value) {
    // Write your code here.
    return false;
  }
}


class Node {
  public int value;
  public Node prev;
  public Node next;

  public Node(int value) {
    this.value = value;
  }
}