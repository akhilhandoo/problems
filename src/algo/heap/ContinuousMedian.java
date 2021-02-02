package algo.heap;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class ContinuousMedian {

    public static void main(String[] args) {
        int[] input = new int[]{5, 10, 100, 200, 6, 13, 14, 50, 51, 52, 1000, 10000, 10001, 10002, 10003, 10004, 75, 80};
        ContinuousMedianHandler medianHandler = new ContinuousMedianHandler();
        for (int x: input) {
            medianHandler.insert(x);
        }
        System.out.println(medianHandler.getMedian());
    }

    static class ContinuousMedianHandler {
        double median = 0;
        Queue<Integer> topHalf = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Integer> bottomHalf = new PriorityQueue<>();

        public void insert(int number) {
            if (topHalf.size() == 0) {
                topHalf.add(number);
                median = number;
            } else if (bottomHalf.size() == 0) {
                if (number < topHalf.peek()) {
                    topHalf.add(number);
                    bottomHalf.add(topHalf.remove());
                } else {
                    bottomHalf.add(number);
                }
                median = (topHalf.peek() + bottomHalf.peek()) / 2.0;
            } else {
                if (topHalf.size() > bottomHalf.size()) {
                    if (number < topHalf.peek()) {
                        topHalf.add(number);
                        bottomHalf.add(topHalf.remove());
                    } else {
                        bottomHalf.add(number);
                    }
                    median = (topHalf.peek() + bottomHalf.peek()) / 2.0;
                } else if (bottomHalf.size() > topHalf.size()) {
                    if (number < topHalf.peek()) {
                        topHalf.add(number);
                    } else {
                        bottomHalf.add(number);
                        topHalf.add(bottomHalf.remove());
                    }
                    median = (topHalf.peek() + bottomHalf.peek()) / 2.0;
                } else {
                    if (number < topHalf.peek()) {
                        topHalf.add(number);
                        median = topHalf.peek();
                    } else if (number > bottomHalf.peek()) {
                        bottomHalf.add(number);
                        median = bottomHalf.peek();
                    } else {
                        topHalf.add(number);
                        median = number;
                    }
                }
            }
        }

        public double getMedian() {
            return median;
        }
    }
}
