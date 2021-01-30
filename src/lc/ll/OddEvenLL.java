package lc.ll;

public class OddEvenLL {

    public static void main(String[] args) {
        /*ListNode two = new ListNode(2);
        ListNode one = new ListNode(1);
        ListNode three = new ListNode(3);
        ListNode five = new ListNode(5);
        ListNode six = new ListNode(6);
        ListNode four = new ListNode(4);
        ListNode seven = new ListNode(7);

        two.next = one;
        one.next = three;
        three.next = five;
        five.next = six;
        six.next = four;
        four.next = seven;*/

        ListNode two = new ListNode(2);
        ListNode one = new ListNode(1);
        ListNode three = new ListNode(3);
        ListNode five = new ListNode(5);

        two.next = one;
        one.next = three;
        three.next = five;

        System.out.println(two);
        OddEvenLL manipulator = new OddEvenLL();
        two = manipulator.oddEvenList(two);
        System.out.println(two);
    }

    // 2->1->3->5->6->4->7->NULL
    // 2->3->1->5->6->4->7->NULL
    // 2->3->6->1->5->4->7->NULL
    // 2->3->6->7->1->5->4->NULL
    public ListNode oddEvenList(ListNode head) {
        ListNode toReturn = head;
        if (getCount(head) > 2) {
            ListNode nodeInOrder = head;
            ListNode nodeOutOfOrder = head.next.next;
            ListNode latestTrailingNode = head.next;
            while (nodeOutOfOrder != null) {
                ListNode nextNodeOutOfOrder = null;
                if (null != nodeOutOfOrder.next) {
                    nextNodeOutOfOrder = nodeOutOfOrder.next.next;
                }
                latestTrailingNode.next = nodeOutOfOrder.next;
                latestTrailingNode = nodeOutOfOrder.next;
                nodeOutOfOrder.next = nodeInOrder.next;
                nodeInOrder.next = nodeOutOfOrder;
                nodeInOrder = nodeOutOfOrder;
                nodeOutOfOrder = nextNodeOutOfOrder;
            }
        }
        return toReturn;
    }

    public int getCount(ListNode head) {
        ListNode temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}