package algo.string;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        System.out.println(bruteForce(""));
    }

    public static String bruteForce(String source) {
        int maxLength = 0;
        String longestPalindromeSubstring = null;
        for (int bindex = 0; bindex < source.length(); bindex++) {
            for (int eindex = bindex + 1; eindex <= source.length(); eindex++) {
                if (isPalindrome(source.substring(bindex, eindex))) {
                    if (eindex - bindex > maxLength) {
                        maxLength = eindex - bindex;
                        longestPalindromeSubstring = source.substring(bindex, eindex);
                    }
                }
            }
        }
        return longestPalindromeSubstring;
    }

    public static boolean isPalindrome(String s) {
        for (int index = 0; index < s.length() / 2; index++) {
            if (s.charAt(index) != s.charAt(s.length() - index - 1)) {
                return false;
            }
        }
        return true;
    }
}
