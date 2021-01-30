package algo.greedy;

import java.util.Arrays;

public class MinimumWaitingTime {

    public int minimumWaitingTime(int[] queries) {
        Arrays.sort(queries);
        int cumulative = 0;
        int runningTotal = 0;
        for (int index = 0; index < queries.length - 1; index++) {
            runningTotal = runningTotal + queries[index];
            cumulative = cumulative + runningTotal;
        }
        return cumulative;
    }
}
