package algo.array;

public class FirstDuplicateValue {

    public static void main(String args[]) {
        int[] input = new int[]{2, 1, 5, 3, 4};
        System.out.println(firstDuplicateValue(input));
    }

    public static int firstDuplicateValue(int[] array) {
        int[] count = new int[array.length+1];
        int toReturn = -1;
        for (int item: array) {
            if (count[item] != 0) {
                toReturn = item;
                break;
            } else {
                count[item]++;
            }
        }
        return toReturn;
    }
}
