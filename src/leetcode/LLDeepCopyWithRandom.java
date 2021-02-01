package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class LLDeepCopyWithRandom {

  public static void main(String args[]) {

    Node one = new Node(1);
    one.next = null;

    Node ten = new Node(10);
    ten.next = one;

    Node eleven = new Node(11);
    eleven.next = ten;

    Node thirteen = new Node(13);
    thirteen.next = eleven;

    Node seven = new Node(7);
    seven.next = thirteen;

    seven.random = null;
    thirteen.random = seven;
    eleven.random = one;
    ten.random = eleven;
    one.random = seven;

    LLDeepCopyWithRandom instance = new LLDeepCopyWithRandom();
    System.out.println(instance.toString(seven));

    Node newList = instance.copyRandomList(seven);
    System.out.println(instance.toString(newList));

    System.out.println(instance.toString(seven));

  }

  public Node copyRandomList(Node head) {
    Node toReturn = null;
    Node traveller = head;
    Node lastCreated = toReturn;
    Map<Node, Node> originalMappings = new HashMap<>();
    while (null != traveller) {
      Node temp = new Node(traveller.val);
      if (lastCreated == null) {
        lastCreated = temp;
        toReturn = lastCreated;
      } else {
        lastCreated.next = temp;
      }
      originalMappings.put(traveller, traveller.next);
      Node justCopied = traveller;
      traveller = traveller.next;
      justCopied.next = temp;
      lastCreated = temp;
      lastCreated.random = justCopied;
    }

    if (null != toReturn) {
      Node pointer = toReturn;
      while (null != pointer) {
        Node newNext = pointer.random.random;
        pointer.random = null != newNext ? newNext.next : null;
        pointer = pointer.next;
      }
    }
    // Revert original mapping in the input.
    for (Map.Entry<Node, Node> mapping: originalMappings.entrySet()) {
      mapping.getKey().next = mapping.getValue();
    }
    return toReturn;
  }

  public String toString(Node n) {
    StringJoiner joiner = new StringJoiner(",", "[", "]");
    Node traveller = n;
    while (null != traveller) {
      joiner.add("[" + traveller.val + ", " + (null != traveller.random ? traveller.random.val : "null") + "]");
      traveller = traveller.next;
    }
    return joiner.toString();
  }
}

class Node {
  int val;
  Node next;
  Node random;

  public Node(int val) {
    this.val = val;
    this.next = null;
    this.random = null;
  }
}