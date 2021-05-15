package simple;

public class StringUtils {

    public static void main(String[] args) {
        char[] data = "hello world".toCharArray();
        reverseCharArray(data, 0, data.length - 1);
        for (int index = 0; index < data.length; index++) {
            System.out.print(data[index]);
        }
    }

    public static void reverseCharArray(char[] data, int left, int right) {
        if (left >= right) {
            return;
        } else {
            char temp = data[left];
            data[left] = data[right];
            data[right] = temp;
            reverseCharArray(data, left + 1, right - 1);
        }
    }
}
