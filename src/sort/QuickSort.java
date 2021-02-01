package sort;

import java.util.Arrays;

public class QuickSort {

  public static void main(String[] args) {
    Integer[] array = new Integer[]{3, 7, 9, 2, 4, 6, 5};
    //String[] array = new String[]{"mumbai", "jimmy", "kanpur", "lucknow", "pune", "chandigarh", "jammu"};
//    QuickSort.quickSort(array);
//    System.out.println(Arrays.toString(array));
    System.out.println(QuickSort.quickSelect(array, 6));

    //Integer[] array = new Integer[]{3, 2, 4, 5, 9, 6, 7};

    //System.out.println(QuickSort._QuickPartition(array, 0, 3));
  }

  public static <T extends Comparable> void quickSort(T[] array) {
    _QuickSort(array, 0, array.length);
  }

  private static <T extends Comparable> void _QuickSort(T[] array, int begin, int end) {
    if (begin < end) {
      //System.out.println(Arrays.toString(array));
      int pivot = _QuickPartition(array, begin, end);
      _QuickSort(array, begin, pivot - 1);
      _QuickSort(array, pivot + 1, end);
    }
  }

  public static <T extends Comparable> T quickSelect(T[] array, int n) {
    return _QuickSelect(array, n, 0, array.length);
  }

  private static <T extends Comparable> T _QuickSelect(T[] array, int n, int begin, int end) {
      int pivotedIndex = _QuickPartition(array, begin, end);
      if (pivotedIndex < n) {
        return _QuickSelect(array, n,pivotedIndex + 1, end);
      } else if (pivotedIndex == n) {
        return array[pivotedIndex];
      } else {
        return _QuickSelect(array, n, begin, pivotedIndex);
      }
  }

  private static <T extends Comparable> int _QuickPartition(T[] array, int begin, int last) {
    int end = last - 1;
    T pivot = array[end];
    int lmarker = begin - 1;
    for (int index = begin; index < end; index++) {
      if (array[index].compareTo(pivot) < 0) {
        if (lmarker >= begin) {
          T temp = array[index];
          array[index] = array[lmarker];
          array[lmarker] = temp;
          lmarker++;
        }
      } else if (array[index].compareTo(pivot) > 0) {
        if (lmarker == begin - 1) {
          lmarker = index;
        }
      }
    }
    if (lmarker >= begin) {
      T temp = array[lmarker];
      array[lmarker] = array[end];
      array[end] = temp;
      return lmarker;
    } else {
      return end;
    }
  }
}
