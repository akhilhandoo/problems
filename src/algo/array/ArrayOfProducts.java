package algo.array;

public class ArrayOfProducts {

  public static void main(String[] args) {
    int[] array = new int[] {5, 1, 4, 2};
    int count = 0;
    for (int item: new ArrayOfProducts().arrayOfProducts(array)) {
      if (count == 0) {
        System.out.print("[" + item + ", ");
      } else if (count != array.length - 1) {
        System.out.print(item + ", ");
      } else {
        System.out.println(item + "]");
      }
      count++;
    }
  }

  public int[] arrayOfProducts(int[] array) {
    int[] productFromLeft = new int[array.length];
    int[] productFromRight = new int[array.length];
    int[] productExceptSelf = new int[array.length];

    int runningProduct = 1;
    for (int index = 0; index < array.length; index++) {
      productFromLeft[index] = runningProduct;
      runningProduct *= array[index];
    }
    runningProduct = 1;
    for (int index = array.length - 1; index >= 0; index--) {
      productFromRight[index] = runningProduct;
      runningProduct *= array[index];
    }
    for (int index = 0; index < array.length; index++) {
      productExceptSelf[index] = productFromLeft[index] * productFromRight[index];
    }
    return productExceptSelf;
  }
}
