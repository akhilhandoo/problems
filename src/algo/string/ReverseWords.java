package algo.string;

public class ReverseWords {

    public static void main(String[] args) {
//        String source = "math  is good, but    is tedious :-P";
        //String source = "god is good";
//        String source = "";
//        String source = "test   ";
        String source = " ";
        System.out.println("[" + reverseWordsInString(source) + "]");
    }


    public static String reverseWordsInString(String string) {
        //  "math  is good, but    is tedious :-P"
        //  "P-: suoidet si    tub ,doog si  htam"
        //  ":-P tedious is    but good, is  math"

        //  "akhil"
        //  ""
        char[] input = string.toCharArray();
        for (int index = 0; index < input.length / 2; index++) {
            char temp = input[index];
            input[index] = input[input.length - index - 1];
            input[input.length - index - 1] = temp;
        }
        int wordStart = 0;
        boolean wasWhitespace = false;
        for (int index = 0; index < input.length; index++) {
            if (input[index] == ' ') {
                if (index != 0 && !wasWhitespace) {
                    int endIndex = index - 1;
                    for (int i = wordStart; i <= (wordStart + (endIndex - wordStart) / 2); i++) {
                        char temp = input[i];
                        input[i] = input[wordStart + endIndex - i];
                        input[wordStart + endIndex - i] = temp;
                    }
                }
                wasWhitespace = true;
            } else if (wasWhitespace) {
                wordStart = index;
                wasWhitespace = false;
            }
        }
        if (string.length() > 0 && !wasWhitespace) {
            int endIndex = input.length - 1;
            for (int i = wordStart; i <= (wordStart + (endIndex - wordStart) / 2); i++) {
                char temp = input[i];
                input[i] = input[wordStart + endIndex - i];
                input[wordStart + endIndex - i] = temp;
            }
        }
        return new String(input);
    }
}
