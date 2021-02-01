package misc;

public class ListNode {
  int val;
  ListNode next;
  public ListNode(int x) {
    val = x;
  }

  public static ListNode mergeKLists(ListNode[] lists) {
    ListNode[] refs = new ListNode[lists.length];
    for (int index = 0; index < lists.length; index++) {
      refs[index] = lists[index];
    }
    ListNode merged = null;
    ListNode tail = null;
    boolean shouldContinue = true;

    while (shouldContinue) {
      int baseLine = Integer.MAX_VALUE;
      int winningIndex = -1;
      for (int index = 0; index < refs.length; index++) {
        if (null != refs[index]) {
          if (refs[index].val < baseLine) {
            baseLine = refs[index].val;
            winningIndex = index;
          }
        }
      }
      if (winningIndex != -1) {
        ListNode temp = new ListNode(refs[winningIndex].val);
        temp.next = null;
        if (null == tail) {
          merged = temp;
        } else {
          tail.next = temp;
        }
        tail = temp;
        refs[winningIndex] = refs[winningIndex].next;
      } else {
        shouldContinue = false;
      }
    }
    return merged;
  }

  public static void main(String[] args) {

    ListNode one = new ListNode(1);
    ListNode four = new ListNode(4);
    ListNode five = new ListNode(5);

    one.next = four;
    four.next = five;

    ListNode oneDash = new ListNode(1);
    ListNode three = new ListNode(3);
    ListNode fourDash = new ListNode(4);
    oneDash.next = three;
    three.next = fourDash;

    ListNode two = new ListNode(2);
    ListNode six = new ListNode(6);
    two.next = six;

    ListNode[] input = new ListNode[]{one, oneDash, two};

    ListNode merged = ListNode.mergeKLists(input);

    ListNode temp = merged;
    while (temp != null) {
      System.out.println(temp.val);
      temp = temp.next;
    }

  }
}
