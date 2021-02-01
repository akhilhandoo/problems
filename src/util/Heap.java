package util;

import java.lang.reflect.Array;
import java.util.StringJoiner;

public class Heap<T extends Comparable> {

  public static void main(String[] args) {
//    Heap<Integer> myHeap = new Heap<>(Integer.class, HeapType.MAX);
//    myHeap.insert(37);
//    System.out.println(myHeap.toString());
//    myHeap.insert(40);
//    System.out.println(myHeap.toString());
//    myHeap.insert(22);
//    System.out.println(myHeap.toString());
//    myHeap.insert(60);
//    System.out.println(myHeap.toString());
//    myHeap.insert(65);
//    System.out.println(myHeap.toString());
//    myHeap.insert(35);
//    System.out.println(myHeap.toString());
//    myHeap.insert(58);
//    System.out.println(myHeap.toString());
//    myHeap.insert(17);
//    System.out.println(myHeap.toString());
//    myHeap.insert(12);
//    System.out.println(myHeap.toString());
//    myHeap.insert(5);
//    System.out.println(myHeap.toString());
//
//    System.out.println("***********************************\n\tTesting removal now !\n***********************************");
//
//    System.out.println("Removed => " + myHeap.remove());
//    System.out.println(myHeap.toString());
//    System.out.println("Removed => " + myHeap.remove());
//    System.out.println(myHeap.toString());
//    System.out.println("Removed => " + myHeap.remove());
//    System.out.println(myHeap.toString());
//    System.out.println("Removed => " + myHeap.remove());
//    System.out.println(myHeap.toString());
//    System.out.println("Removed => " + myHeap.remove());
//    System.out.println(myHeap.toString());
//    System.out.println("Removed => " + myHeap.remove());
//    System.out.println(myHeap.toString());
//    System.out.println("Removed => " + myHeap.remove());
//    System.out.println(myHeap.toString());
//    System.out.println("Removed => " + myHeap.remove());
//    System.out.println(myHeap.toString());


    Heap<Integer> myHeap = new Heap<>(Integer.class, HeapType.MIN);
    myHeap.insert(3);
    System.out.println(myHeap.toString());
    myHeap.insert(4);
    System.out.println(myHeap.toString());
    myHeap.insert(5);
    System.out.println(myHeap.toString());

    System.out.println("Removed => " + myHeap.remove());
    System.out.println(myHeap.toString());
  }

  public enum HeapType {
    MIN,
    MAX;
  }

  private T[] data;
  private int defaultSize = 10;
  private int size;
  private Class clazz;
  private HeapType heapType;

  public Heap(Class<? extends Comparable> clazz, HeapType heapType) {
    this.clazz = clazz;
    this.heapType = heapType;
    data = (T[]) Array.newInstance(clazz, defaultSize);
    size = 0;
  }

  public int getSize() {
    return size;
  }

  public void insert(T item) {
    if (size == data.length - 1) {
      T[] newData = (T[]) Array.newInstance(clazz, data.length * 2);
      System.arraycopy(data, 0, newData, 0, data.length);
      data = newData;
    }
    data[++size] = item;
    swim(size);
  }

  public T remove() {
    if (size >= 1) {
      shrinkIfNecessary();
      T toRemove = data[1];
      data[1] = data[size];
      size--;
      sink(1);
      return toRemove;
    } else {
      return null;
    }
  }

  public T peek() {
    return data[1];
  }

  public T[] getData() {
    T[] toReturn = (T[])Array.newInstance(clazz, data.length);
    System.arraycopy(data, 0, toReturn, 0, size + 1);
    return toReturn;
  }

  private boolean shrinkIfNecessary() {
    if ((size * 2) + 1 < (data.length)) {
      T[] newData = (T[]) Array.newInstance(clazz, data.length / 2);
      System.arraycopy(data, 0, newData, 0, size + 1);
      data = newData;
      return true;
    } else {
      return false;
    }
  }

  private void swim(int index) {
    if (index > 1) {
      if (HeapType.MIN == heapType) {
        if (data[index].compareTo(data[index/2]) < 0) {
          exchange(index, index/2);
          swim(index/2);
        }
      } else {
        if (data[index].compareTo(data[index/2]) > 0) {
          exchange(index, index/2);
          swim(index/2);
        }
      }
    }
  }

  private void sink(int index) {
    if (2 * index <= size) {
      int indexToCompare = 0;
      if ((2 * index) + 1 <= size) {
        if (HeapType.MIN == heapType) {
          if (data[2 * index].compareTo(data[(2 * index) + 1]) <= 0) {
            indexToCompare = 2 * index;
          } else {
            indexToCompare = (2*index) + 1;
          }
        } else {
          if (data[2 * index].compareTo(data[(2 * index) + 1]) >= 0) {
            indexToCompare = 2 * index;
          } else {
            indexToCompare = (2*index + 1);
          }
        }
      } else {
        indexToCompare = 2 * index;
      }
      if (HeapType.MIN == heapType) {
        if (data[indexToCompare].compareTo(data[index]) <= 0) {
          exchange(index, indexToCompare);
          sink(indexToCompare);
        }
      } else {
        if (data[indexToCompare].compareTo(data[index]) >= 0) {
          exchange(index, indexToCompare);
          sink(indexToCompare);
        }
      }
    }
  }

  public void exchange(int i, int j) {
    T temp = data[i];
    data[i] = data[j];
    data[j] = temp;
  }

  public String toString() {
    T[] array = getData();
    StringJoiner joiner = new StringJoiner(",", "[", "](" + size + ")");
    for (T item: array) {
      if (null != item) {
        joiner.add(item.toString());
      } else {
        joiner.add("null");
      }
    }
    return joiner.toString();
  }
}
