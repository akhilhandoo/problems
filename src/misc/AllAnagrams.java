package misc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class AllAnagrams {

  public static void main(String[] args) {

//    AllAnagrams aa = new AllAnagrams();
////    System.out.println(aa.findAnagrams("cbaebabacd", "abc"));
//    System.out.println(aa.findAnagrams("baa", "aa"));

//    String[] tokens = "hello:world".split(":");
//      for (String token: tokens) {
//        System.out.println(token);
//      }
//    if ("hello:world".startsWith("hello:")) {
//      System.out.println("Yes !");
//    } else {
//      System.out.println(" Oh No !");
//    }
//    String absolutePath = "/opt/ampool/spark-2.2.4/work/app-20200617034254-0001/0/stderr";
    String absolutePath = "/opt/ampool/spark-2.2.4/work/driver-20200617034251-0001/stdout";
    String subComponentPath = absolutePath.substring(absolutePath.indexOf("work/") + 5);
    String subComponentDetails = getSubComponentDetails(subComponentPath);
    System.out.println(subComponentDetails);
  }

  public static String getSubComponentDetails(String subComponentPath) {
    StringBuilder subComponentBuilder = new StringBuilder();
    if (subComponentPath.startsWith("driver-")) {
      subComponentBuilder.append("DRIVER:").append(subComponentPath.substring(0, subComponentPath.indexOf("/"))).append("|");
    } else if (subComponentPath.startsWith("app-")) {
      subComponentBuilder.append("APPLICATION:").append(subComponentPath.substring(0, subComponentPath.indexOf("/"))).append("|EXECUTOR:");
      String executorSectionOfSubcomponent = subComponentPath.substring(subComponentPath.indexOf("/") + 1);
      subComponentBuilder.append(executorSectionOfSubcomponent.substring(0, executorSectionOfSubcomponent.indexOf("/"))).append("|");
    }
    return subComponentBuilder.toString();
  }

  public List<Integer> findAnagrams(String s, String p) {
    List<Integer> toReturn = new ArrayList<>();
    List<Character> search = new ArrayList<>();
    List<Character> source = new ArrayList<>();
    char[] findThis = p.toCharArray();
    for (int index = 0; index < s.length() - findThis.length + 1; index++) {
      search.clear();
      source.clear();
      add(search, findThis);
      add(source, s.substring(index, index + findThis.length).toCharArray());
      if (cancelOut(source, search)) {
        toReturn.add(index);
      }
    }
    return toReturn;
  }

  private void add(List<Character> source, char[] additive) {
    for (char ch: additive) {
      source.add(ch);
    }
  }

  private boolean cancelOut(List<Character> source, List<Character> search) {
    Iterator<Character> searchIterator = search.iterator();
    while (searchIterator.hasNext()) {
      Character ch = searchIterator.next();
      source.remove(ch);
      searchIterator.remove();
    }
    return source.isEmpty() && search.isEmpty();
  }
}
