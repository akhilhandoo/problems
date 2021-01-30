package algo.ll;

public class LinkedListShift {

    public static void main(String[] args) {
        LinkedList head = new LinkedList(0);
        LinkedList one = new LinkedList(1);
        LinkedList two = new LinkedList(2);
        LinkedList three = new LinkedList(3);
        LinkedList four = new LinkedList(4);
        LinkedList five = new LinkedList(5);

        head.next = one;
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;

        System.out.println(head.toString());

        LinkedList modified = shiftLinkedList(head, 14);
        System.out.println(modified.toString());
    }


    public static LinkedList shiftLinkedList(LinkedList head, int k) {
        int length = countNodes(head);
        int newHead = k < 0 ? Math.abs(k) : ((k > 0) ? ((length - k) > 0 ? (length - k) : (length - ((k - length) % length))) : k);
        newHead = newHead % length;
        if (0 == newHead) {
            return head;
        } else {
            LinkedList toReturn = head;
            LinkedList newTail = head;
            int counter = 0;
            while (counter < newHead) {
                toReturn = toReturn.next;
                if (0 != counter) {
                    newTail = newTail.next;
                }
                counter++;
            }
            joinTailToHead(head);
            newTail.next = null;
            return toReturn;
        }
    }

    public static int countNodes(LinkedList head) {
        int count = 0;
        LinkedList temp = head;
        while (null != temp) {
            temp = temp.next;
            count++;
        }
        return count;
    }

    public static void joinTailToHead(LinkedList head) {
        int counter = 0;
        LinkedList temp = head;
        while (counter < countNodes(head) - 1) {
            temp = temp.next;
            counter++;
        }
        temp.next = head;
    }

    static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            next = null;
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
