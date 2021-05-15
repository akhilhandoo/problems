package algo.array;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class OverlappingIntervals {

    public static void main(String[] args) {
        int[][] data = new int[][] {
                {6, 8},
                {3, 5},
                {9, 10},
                {1, 2},
                {4, 7}
        };
        int[][] returned = mergeOverlappingIntervals(data);
        for (int[] pair: returned) {
            System.out.println("[" + pair[0] + ", " + pair[1] + "]");
        }
    }

    public static int[][] mergeOverlappingIntervals(int[][] intervals) {
        List<Integer[]> collection =  Arrays.stream(intervals)
                .map(array -> new Integer[]{array[0], array[1]})
                .collect(Collectors.toList());
        Collections.sort(collection, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[0].compareTo(o2[0]);
            }
        });
        List<Integer[]> collector = new ArrayList<>();
        collector.add(collection.get(0));
        int collectorIndex = 0;
        for (int index = 1; index < collection.size(); index++) {
            if (collection.get(index)[0] <= collector.get(collectorIndex)[1]) {
                if (collection.get(index)[1] > collector.get(collectorIndex)[1]) {
                    Integer[] removed = collector.remove(collectorIndex);
                    collector.add(new Integer[]{removed[0], collection.get(index)[1]});
                }
            } else {
                collector.add(collection.get(index));
                collectorIndex++;
            }
        }
        int[][] toReturn = new int[collectorIndex + 1][];
        for (int index = 0; index <= collectorIndex; index++) {
            toReturn[index] = new int[]{collector.get(index)[0], collector.get(index)[1]};
        }
        return toReturn;
    }
}
