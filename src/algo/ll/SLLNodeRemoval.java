package algo.ll;

public class SLLNodeRemoval {

  public static void main(String[] args) {
    LinkedList zero = new LinkedList(0);
    LinkedList one = new LinkedList(1);
//    LinkedList two = new LinkedList(2);
//    LinkedList three = new LinkedList(3);
//    LinkedList four = new LinkedList(4);
//    LinkedList five = new LinkedList(5);
//    LinkedList six = new LinkedList(6);
//    LinkedList seven = new LinkedList(7);
//    LinkedList eight = new LinkedList(8);
//    LinkedList nine = new LinkedList(9);

    zero.next = one;
//    one.next = two;
//    two.next = three;
//    three.next = four;
//    four.next = five;
//    five.next = six;
//    six.next = seven;
//    seven.next = eight;
//    eight.next = nine;

    System.out.println(zero);
    removeKthNodeFromEnd(zero, 2);
    System.out.println(zero);
  }

  public static void removeKthNodeFromEnd(LinkedList head, int k) {
    int length = length(head);
    int nodeIndexFromHead = length - k;
    boolean predecessorExists = !isHead(head, nodeIndexFromHead);
    boolean successorExists = !isLast(head, nodeIndexFromHead);
    if (predecessorExists && successorExists) {
      LinkedList predecessor = getPredecessor(head, nodeIndexFromHead);
      LinkedList successor = getSuccessor(head, nodeIndexFromHead);
      predecessor.next = successor;
    } else {
      if (predecessorExists) {
        LinkedList predecessor = getPredecessor(head, nodeIndexFromHead);
        predecessor.next = null;
      } else {
        LinkedList successor = getSuccessor(head, nodeIndexFromHead);
        head.value = successor.value;
        head.next = successor.next;
      }
    }
  }

  static LinkedList getPredecessor(LinkedList head, int index) {
    LinkedList traveller = head;
    for (int l_index = 1; l_index < index; l_index++) {
      traveller = traveller.next;
    }
    return traveller;
  }

  static LinkedList getSuccessor(LinkedList head, int index) {
    LinkedList traveller = head;
    for (int l_index = 0; l_index < index; l_index++) {
      traveller = traveller.next;
    }
    return traveller.next;
  }

  static boolean isHead(LinkedList head, int index) {
    return index == 0;
  }

  static boolean isLast(LinkedList head, int index) {
    return index == length(head) - 1;
  }

  static int length(LinkedList head) {
    LinkedList traveller = head;
    int count = 0;
    while (traveller != null) {
      traveller = traveller.next;
      count++;
    }
    return count;
  }

  static class LinkedList {
    int value;
    LinkedList next = null;

    public LinkedList(int value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return "LinkedList{" +
              "value=" + value +
              ", next=" + next +
              '}';
    }
  }

}
