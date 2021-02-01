package algo.recursive;

import java.util.ArrayList;
import java.util.List;

public class ProductSum {

  public static void main(String[] args) {
    List list = new ArrayList();
    list.add(5);
    list.add(2);

    ArrayList temp = new ArrayList();
    temp.add(7);
    temp.add(-1);

    list.add(temp);
    list.add(3);

    temp = new ArrayList();
    temp.add(6);

    ArrayList temp2 = new ArrayList();
    temp2.add(-13);
    temp2.add(8);

    temp.add(temp2);
    temp.add(4);

    list.add(temp);

    System.out.println(ProductSum.productSum(list));
  }

  public static int productSum(List<Object> array) {
    return sumUpWithMultiplier(array, 1);
  }

  private static int sumUpWithMultiplier(List<Object> array, int multiplier) {
    int total = 0;
    for (Object o: array) {
      if (o instanceof Integer) {
        total += (multiplier * (Integer) o);
      } else if (o instanceof ArrayList) {
        total += multiplier * sumUpWithMultiplier((List)o, multiplier + 1);
      }
    }
    return total;
  }
}
