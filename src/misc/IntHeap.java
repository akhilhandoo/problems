package misc;

import java.util.StringJoiner;

public class IntHeap {

  private int[] data;
  private int capacity;
  private int _size;

  public IntHeap(int capacity) {
    this.capacity = capacity;
    this._size = 1;
    data = new int[capacity + 1];
    data[0] = Integer.MIN_VALUE;
    for (int index = 1; index <= capacity; index++) {
      data[index] = new Integer(0);
    }
  }

  public int peek() {
    if (_size > 1) {
      return data[1];
    } else {
      throw new IllegalArgumentException("Queue empty. Cannot peek.");
    }
  }

  public void insert(int n) {
    if (_size == capacity + 1) {
      throw new UnsupportedOperationException("Queue full. Cannot insert.");
    } else {
      data[_size] = n;
      swim(_size);
      _size++;
    }
  }

  public int remove() {
    if (_size > 1) {
      int temp = data[1];
      exchange(1, _size - 1);
      _size--;
      sink(1);
      return temp;
    } else {
      throw new UnsupportedOperationException("Queue empty. Cannot remove.");
    }
  }

  public int size() {
    return _size - 1;
  }

  public String getSingleLineRepresentation() {
    StringJoiner joiner = new StringJoiner(",");
    for (int index = 1; index < _size; index++) {
      joiner.add("" + data[index]);
    }
    return joiner.toString();
  }

  private void swim(int index) {
    if (index/2 >= 1 && data[index] > data[index/2]) {
      exchange(index, index/2);
      swim(index/2);
    }
  }

  private void sink(int index) {
    if (2*index < _size) {
      int candidate1 = data[2*index];
      int candidateIndex = 2*index;
      if (2*index+1 < _size) {
        int candidate2 = data[(2*index) + 1];
        if (candidate1 >= candidate2) {
          candidateIndex = 2*index;
        } else {
          candidateIndex = 2*index + 1;
        }
      }
      if (data[index] < data[candidateIndex]) {
        exchange(index, candidateIndex);
        sink(candidateIndex);
      }
    }
  }

  private void exchange(int index1, int index2) {
    int temp = data[index1];
    data[index1] = data[index2];
    data[index2] = temp;
  }

  public int[] getData() {
    return data;
  }

  public static void main(String[] args) {
    IntHeap heap = new IntHeap(15);
    heap.insert(-13);
    // -13
    System.out.println(heap.getSingleLineRepresentation());
    heap.insert(29);
    // 29, -13
    System.out.println(heap.getSingleLineRepresentation());
    heap.insert(4);
    // 29, -13, 4
    System.out.println(heap.getSingleLineRepresentation());
    heap.insert(0);
    // 29, 0, 4, -13
    System.out.println(heap.getSingleLineRepresentation());
    heap.insert(30);
    // 30, 29, 4, -13, 0
    System.out.println(heap.getSingleLineRepresentation());
    heap.insert(-5);
    // 30, 29, 4, -13, 0, -5
    System.out.println(heap.getSingleLineRepresentation());

    System.out.println("*************************************");

    int head = heap.remove();
    // 30
    System.out.println("Removed => " + head);
    // 29, 0, 4, -13, -5
    System.out.println(heap.getSingleLineRepresentation());

    head = heap.remove();
    // 29
    System.out.println("Removed => " + head);
    // 4, 0, -5, -13
    System.out.println(heap.getSingleLineRepresentation());

    head = heap.remove();
    // 4
    System.out.println("Removed => " + head);
    // 0, -13, -5
    System.out.println(heap.getSingleLineRepresentation());

    head = heap.remove();
    // 0
    System.out.println("Removed => " + head);
    // -5, -13
    System.out.println(heap.getSingleLineRepresentation());

    head = heap.remove();
    // -5
    System.out.println("Removed => " + head);
    // -13
    System.out.println(heap.getSingleLineRepresentation());

    head = heap.remove();
    // -13
    System.out.println("Removed => " + head);
    //
    System.out.println(heap.getSingleLineRepresentation());

    // Exception
    try {
      head = heap.remove();
      System.out.println("Removed => " + head);
      System.out.println(heap.getSingleLineRepresentation());
    } catch (UnsupportedOperationException uoe) {
      System.out.println(uoe.getMessage());
    }

    StringJoiner dataJoiner = new StringJoiner(", ", "[", "]");
    for (int index = 1; index < 7; index++) {
      dataJoiner.add("" + heap.getData()[index]);
    }

    System.out.println("*************************************");
    System.out.println("*************************************\n");
    System.out.println(dataJoiner.toString());
    System.out.println("*************************************");
    System.out.println("*************************************\n");

  }

}
