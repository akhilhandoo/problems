package misc;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WordBreak {

  public static void main(String[] args) {
//    System.out.println(WordBreak.wordBreak("catsandog", Arrays.asList(new String[]{
//            "cats", "dog", "sand", "and", "cat"
//    })));

    System.out.println(WordBreak.wordBreak("catsanddogsandcats", Arrays.asList(new String[]{
            "cats", "dog", "dogs", "and", "cat"
    })));
//
//    System.out.println(WordBreak.wordBreak("applepenapple", Arrays.asList(new String[]{
//            "apple", "pen"
//    })));
//
//    System.out.println(WordBreak.wordBreak("ab", Arrays.asList(new String[]{
//            "a", "b"
//    })));
//
//    System.out.println(WordBreak.wordBreak("shukriya", Arrays.asList(new String[]{
//            "shuk", "ya", "kriya", "sh", "i", "shukr", "uk"/*, "r"*/
//    })));


    System.out.println(WordBreak.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
            Arrays.asList(new String[]{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa", "b"})));

  }

  public static boolean wordBreak(String s, List<String> wordDict) {
    return wordBreak(s, wordDict.stream().collect(Collectors.toSet()));
    //return bottomUpWB(s, wordDict);
  }

  private static boolean wordBreak(String s, Set<String> dictionary) {
    if (null == s || s.length() == 0) {
      return true;
    }
    if (dictionary.contains(s)) {
      return true;
    }
    for (int index = 1; index < s.length(); index++) {
      if (dictionary.contains(s.substring(0, index)) && wordBreak(s.substring(index), dictionary)) {
        return true;
      }
    }
    return false;
  }

  private static boolean bottomUpWB(String s, List<String> wordDict) {
    boolean[] truthTable = new boolean[s.length() + 1];
    truthTable[0] = true;
    for (int index = 1; index <= s.length(); index++) {
      String substring = s.substring(0, index);
      for (String word: wordDict) {
        if (substring.endsWith(word)) {
          truthTable[index] = truthTable[index - word.length()];
          if (truthTable[index]) {
            break;
          }
        }
      }
    }
    return truthTable[s.length()];
  }
}
