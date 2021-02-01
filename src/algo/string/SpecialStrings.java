package algo.string;

import java.util.*;

public class SpecialStrings {

  public static void main(String[] args) {
    String[] array = new String[]{"a", "aaa"};
    System.out.println(specialStrings(array));
  }

  public static List<String> specialStrings(String[] strings) {
    List<String> toReturn = new ArrayList<>();
    Set<String> lookup = new HashSet<>();
    lookup.addAll(Arrays.asList(strings));
    for (String item: strings) {
      if (isSpecial(item, lookup)) {
        toReturn.add(item);
      }
    }
    return toReturn;
  }

  public static boolean isSpecial(String source, Set<String> lookup) {
    if (source.length() > 1) {
      for (int index = 1; index < source.length(); index++) {
        if (lookup.contains(source.substring(0, index)) && isSpecial(source.substring(index), lookup)) {
          return true;
        }
      }
      return false;
    } else {
      return lookup.contains(source);
    }
  }
}
