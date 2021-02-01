package algo.ll;

public class InvertedBisection {

  public static void main(String[] args) {
    LinkedList head = new LinkedList(0);
    LinkedList one = new LinkedList(1);
    LinkedList two = new LinkedList(2);
    LinkedList three = new LinkedList(3);
    LinkedList four = new LinkedList(4);
    LinkedList five = new LinkedList(5);
    LinkedList six = new LinkedList(6);

    head.next = one;
    one.next = two;
    two.next = three;
    three.next = four;
    four.next = five;
    five.next = six;

    LinkedList invertedBisected = bisectInverted(head);
    LinkedList temp = invertedBisected;
    while (temp != null) {
      System.out.println(temp.value + " => ");
      temp = temp.next;
    }
  }

  static class LinkedList {
    int value;
    LinkedList next = null;

    public LinkedList(int value) {
      this.value = value;
    }
  }

  private static LinkedList bisectInverted(LinkedList head) {
    if (null == head) {
      return null;
    } else if (length(head) == 1) {
      return head;
    }
    boolean even = length(head) % 2 == 0;
    LinkedList middle = null;
    if (!even) {
      middle = getMiddle(head, length(head));
    }
    LinkedList posterior = bisect(head, length(head), even);
    LinkedList reverseAnterior = reverse(head);
    LinkedList reversePosterior = reverse(posterior);
    if (null != middle) {
      middle.next = reversePosterior;
      last(reverseAnterior).next = middle;
    } else {
      last(reverseAnterior).next = reversePosterior;
    }
    return reverseAnterior;
  }

  private static LinkedList bisect(LinkedList head, int length, boolean isEven) {
    LinkedList posterior = null;
    LinkedList temp = head;
    int count = 0;
    while (count < (length / 2) - 1) {
      temp = temp.next;
      count++;
    }
    if (isEven) {
      posterior = temp.next;
    } else {
      posterior = temp.next.next;
    }
    temp.next = null;
    return posterior;
  }

  private static int length(LinkedList head) {
    int count = 0;
    LinkedList temp = head;
    while (temp != null) {
      count++;
      temp = temp.next;
    }
    return count;
  }

  private static LinkedList getMiddle(LinkedList head, int length) {
    int count = 0;
    LinkedList temp = head;
    while (count < length / 2) {
      temp = temp.next;
      count++;
    }
    return temp;
  }

  private static LinkedList last(LinkedList head) {
    LinkedList temp = head;
    while (temp.next != null) {
      temp = temp.next;
    }
    return temp;
  }

  private static LinkedList reverse(LinkedList head) {
    LinkedList prev = null;
    LinkedList current = head;
    while (null != current) {
      LinkedList next = current.next;
      current.next = prev;
      prev = current;
      current = next;
    }
    return prev;
  }
}
