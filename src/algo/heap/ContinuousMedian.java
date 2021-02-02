package algo.heap;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class ContinuousMedian {

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
                    }
                }
            }
        }

        public double getMedian() {
            return median;
        }
    }
}
