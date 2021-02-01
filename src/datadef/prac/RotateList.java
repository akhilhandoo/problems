package datadef.prac;

public class RotateList {

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
//    append(head, 2);
//    append(head, 3);
//    append(head, 4);
//    append(head, 5);
    printSerial(head);
    ListNode rotated = rotateRight(head, 1);
    printSerial(rotated);
  }

  public static ListNode rotateRight(ListNode head, int k) {
    int size = getListLength(head);
    if (size == 0) {
      return null;
    }
    ListNode traveller = head;
    int effectiveRotation = k % size;
    if (k == 0 || effectiveRotation == 0) {
      return head;
    }
    int moveBy = size - effectiveRotation;
    for (int index = 0; index < moveBy; index++) {
      traveller = traveller.next;
    }
    ListNode toReturn = new ListNode(traveller.val);
    traveller = traveller.next;
    for (int count = 1; count <= effectiveRotation - 1; count++) {
      append(toReturn, traveller.val);
      traveller = traveller.next;
    }
    traveller = head;
    for (int index = 0; index < moveBy; index++) {
      append(toReturn, traveller.val);
      traveller = traveller.next;
    }
    return toReturn;
  }

  public static void append(ListNode head, int n) {
    ListNode traveller = head;
    while (traveller.next != null) {
      traveller = traveller.next;
    }
    ListNode temp = new ListNode(n);
    traveller.next = temp;
  }

  public static int getListLength(ListNode head) {
    ListNode temp = head;
    int count = 0;
    while (null != temp) {
      temp = temp.next;
      count++;
    }
    return count;
  }

  public static void printSerial(ListNode head) {
    ListNode traveller = head;
    while (traveller != null) {
      System.out.print(traveller.val + "->");
      traveller = traveller.next;
    }
    System.out.println("null");
  }
}

class ListNode {
  int val;
  ListNode next;
  ListNode(int x) { val = x; }
}