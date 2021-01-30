package lc.array;

import java.util.*;

public class KFrequent {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        int[] result = topKFrequent(nums, 2);
        for (int i: result) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    static class NumAndFrequency implements Comparable<NumAndFrequency> {

        int num;
        int frequency;

        public NumAndFrequency(int num, int frequency) {
            this.num = num;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(NumAndFrequency o) {
            return this.frequency - o.frequency;
        }
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> numToCountMap = new HashMap<>();
        for (int number: nums) {
            numToCountMap.put(number, numToCountMap.getOrDefault(number, 0) + 1);
        }
        Queue<NumAndFrequency> heap = new PriorityQueue<>();
        for (int number: numToCountMap.keySet()) {
            heap.add(new NumAndFrequency(number, numToCountMap.get(number)));
            if (heap.size() == k + 1) {
                heap.remove();
            }
        }
        int[] toReturn = new int[k];
        int index = 0;
        while (heap.size() != 0) {
            toReturn[index++] = heap.remove().num;
        }
        return toReturn;
    }
}
