package algo.ll;

public class Reversal {

  public static LinkedList reverseLinkedList(LinkedList head) {
     LinkedList current = head;
     LinkedList previous = null;
     while (current != null) {
       LinkedList next = current.next;
       current.next = previous;
       previous = current;
       current = next;
     }
     return previous;
  }

  static class LinkedList {
    int value;
    LinkedList next = null;

    public LinkedList(int value) {
      this.value = value;
    }
  }
}
