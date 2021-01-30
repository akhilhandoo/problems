package algo.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskAssignment {

    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 5, 3, 1, 4};
        ArrayList<Integer> input = new ArrayList<>();
        for (int item: array) {
            input.add(item);
        }
        List<ArrayList<Integer>> answer = taskAssignment(3, input);
        System.out.println(answer);
    }

    static class Pair<T extends Comparable> implements Comparable {
        private T value;
        private T index;

        public Pair(T value, T index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Object o) {
            return this.value.compareTo(((Pair)o).value);
        }

        public T getValue() {
            return value;
        }

        public T getIndex() {
            return index;
        }
    }

    public static ArrayList<ArrayList<Integer>> taskAssignment(int k, ArrayList<Integer> tasks) {
        List<Pair<Integer>> taskIndexPair = new ArrayList<>();
        Pair<Integer> pair = null;
        for (int index = 0; index < tasks.size(); index++) {
            pair = new Pair<>(tasks.get(index), index);
            taskIndexPair.add(pair);
        }
        Collections.sort(taskIndexPair);
        ArrayList<ArrayList<Integer>> toReturn = new ArrayList<>();
        ArrayList<Integer> temp = null;
        for (int index = 1; index <= k; index++) {
            temp = new ArrayList<>(2);
            temp.add(taskIndexPair.get(index - 1).getIndex());
            temp.add(taskIndexPair.get(taskIndexPair.size() - index).getIndex());
            toReturn.add(temp);
        }
        return toReturn;
    }
}
