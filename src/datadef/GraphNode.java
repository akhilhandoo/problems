package datadef;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GraphNode<T> extends Node<T> {
  private List<GraphNode<T>> children;

  public GraphNode (T data) {
    super(data);
    children = new ArrayList<>();
  }

  public List<GraphNode<T>> getChildren() {
    return children;
  }

  public void setChildren(List<GraphNode<T>> children) {
    this.children = children;
  }

  public void addChild(GraphNode<T> child) {
    this.children.add(child);
  }

  public static void main(String[] args) {

//    String[] columns = "saledate:date:::".split(",");
//    for (String column: columns) {
//      String[] tokens = column.split(":");
//      for (String token: tokens) {
//        System.out.println("[" + token + "]");
//      }
//    }

//    System.out.printf("%f", (new BigDecimal("113786854")).floatValue());

//    String oldNewColPair = "cast(wr_returned_date_sk";
//    System.out.println(oldNewColPair.substring(oldNewColPair.indexOf("(") + 1));


    //System.out.println(GraphNode.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));

    System.out.println(new BigDecimal("3725.231").intValue());
  }

  public static int trap(int[] height) {
    int[] maxLeft = new int[height.length];
    int[] maxRight = new int[height.length];
    int[] lesser = new int[height.length];

    int maxTillNow = 0;
    maxLeft[0] = 0;
    for (int index = 1; index < height.length; index++) {
      if (height[index - 1] > maxTillNow) {
        maxTillNow = height[index - 1];
      }
      maxLeft[index] = maxTillNow;
    }

    maxTillNow = 0;
    maxRight[height.length - 1] = 0;
    for (int index = height.length - 2; index >= 0; index--) {
      if (height[index + 1] > maxTillNow) {
        maxTillNow = height[index + 1];
      }
      maxRight[index] = maxTillNow;
    }

    int total = 0;
    for (int index = 0; index < height.length; index++) {
      lesser[index] = Math.min(maxLeft[index], maxRight[index]);
      total += (lesser[index] - height[index]) > 0 ? (lesser[index] - height[index]) : 0;
    }
    return total;
  }
}
