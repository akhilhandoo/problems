package algo.stack;

import java.util.ArrayDeque;
import java.util.Queue;

public class MinMaxStackConstruction {

  static class MinMaxStack {

    private ArrayDeque<Integer> min;
    private ArrayDeque<Integer> max;
    private ArrayDeque<Integer> stack;

    public MinMaxStack() {
      min = new ArrayDeque<>();
      max = new ArrayDeque<>();
      stack = new ArrayDeque<>();
    }

    public int peek() {
      return stack.peek();
    }

    public int pop() {
      Integer toReturn = stack.pop();
      if (toReturn == min.peek()) {
        min.pop();
      }
      if (toReturn == max.peek()) {
        max.pop();
      }
      return toReturn;
    }

    public void push(Integer number) {
      stack.push(number);
      if (null == min.peek() || number <= min.peek()) {
        min.push(number);
      }
      if (null == max.peek() || number >= max.peek()) {
        max.push(number);
      }
    }

    public int getMin() {
      return min.peek();
    }

    public int getMax() {
      return max.peek();
    }
  }
}
