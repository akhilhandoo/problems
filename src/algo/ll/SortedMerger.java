package algo.ll;

public class SortedMerger {

  public static void main(String[] args) {

    LinkedList headOne = new LinkedList(2);
//    LinkedList six = new LinkedList(6);
//    LinkedList seven = new LinkedList(7);
//    LinkedList eight = new LinkedList(8);
//    seven.next = eight;
//    six.next = seven;
//    headOne.next = six;

    LinkedList headTwo = new LinkedList(1);
//    LinkedList three = new LinkedList(3);
//    LinkedList four = new LinkedList(4);
//    LinkedList five = new LinkedList(5);
//    LinkedList sixA = new LinkedList(6);
//    LinkedList nine = new LinkedList(9);
//    LinkedList ten = new LinkedList(10);
//    nine.next = ten;
//    sixA.next = nine;
//    five.next = sixA;
//    four.next = five;
//    three.next = four;
//    headTwo.next = three;

    LinkedList result = mergeLinkedLists(headOne, headTwo);
    System.out.println(result);

  }

  public static LinkedList mergeLinkedLists(LinkedList headOne, LinkedList headTwo) {
    LinkedList current = null;
    LinkedList c1 = null;
    LinkedList c2 = null;
    LinkedList restToAdd = null;
    if (headOne.value < headTwo.value) {
      current = headOne;
      c1 = current.next;
      c2 = headTwo;
      restToAdd = c2;
    } else {
      current = headTwo;
      c1 = current.next;
      c2 = headOne;
      restToAdd = c2;
    }
    LinkedList toReturn = current;
    while (c1 != null && c2 != null) {
      if (c1.value <= c2.value) {
        if (current.next == c1) {
          current = c1;
          c1 = c1.next;
        } else {
          LinkedList c1Next = c1.next;
          c1.next = c2;
          current.next = c1;
          current = c1;
          c1 = c1Next;
        }
        if (null == c1) {
          restToAdd = c2;
          break;
        }
      } else {
        if (current.next == c2) {
          current = c2;
          c2 = c2.next;
        } else {
          LinkedList c2Next = c2.next;
          c2.next = c1;
          current.next = c2;
          current = c2;
          c2 = c2Next;
        }
        if (null == c2) {
          restToAdd = c1;
          break;
        }
      }
    }
    if (null != restToAdd) {
      current.next = restToAdd;
    }
    return toReturn;
  }

  public static class LinkedList {
    int value;
    LinkedList next;

    LinkedList(int value) {
      this.value = value;
      this.next = null;
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
