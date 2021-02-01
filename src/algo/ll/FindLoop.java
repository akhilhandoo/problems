package algo.ll;

public class FindLoop {

  public static void main(String[] args) {
    LinkedList root = new LinkedList(0);
    LinkedList one = new LinkedList(1);
    LinkedList two = new LinkedList(2);
    LinkedList three = new LinkedList(3);
    LinkedList four = new LinkedList(4);
    LinkedList five = new LinkedList(5);
    LinkedList six = new LinkedList(6);
    LinkedList seven = new LinkedList(7);
    LinkedList eight = new LinkedList(8);
    LinkedList nine = new LinkedList(9);
    nine.next = one;

    root.next = one;
    one.next = two;
    two.next = three;
    three.next = four;
    four.next = five;
    five.next = six;
    six.next = seven;
    seven.next = eight;
    eight.next = nine;

    System.out.println(findLoop(root).value);
  }

  public static LinkedList findLoop(LinkedList head) {
    LinkedList fast = head;
    LinkedList slow = head;
    LinkedList meetingPoint = null;

    do {
      slow = slow.next;
      fast = fast.next.next;
    } while (fast != slow);
    meetingPoint = fast;
    LinkedList temp = head;
    while (meetingPoint != temp) {
      meetingPoint = meetingPoint.next;
      temp = temp.next;
    }
    return temp;
  }

  static class LinkedList {
    int value;
    LinkedList next = null;

    public LinkedList(int value) {
      this.value = value;
    }
  }
}
