package misc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Base {
  int field0=0;

  public static void main(String[] args) throws Throwable {
//    Base ob = new BaseY();
//    BaseY child = (BaseY)ob;
//    System.out.println(child.field2);

//    Pattern p = Pattern.compile("ambari\\-agent\\.log\\.*\\d*$");
//
//    String[] examples = new String[] {
//            "ambari-agent.log.22",
//            "ambari-agent.log",
//            "ambari-agent.out"
//    };
//
//    for (String s: examples) {
//      System.out.print(s + " => ");
//      if (p.matcher(s).matches()) {
//        System.out.println(" Matches !");
//      } else {
//        System.out.println(" Does NOT match !");
//      }
//    }

    InputStream is = Runtime.getRuntime().exec("hostname").getInputStream();
    String text = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
    System.out.println("[" + text + "]");
  }
}

class BaseX extends Base {
  int field1=1;
}

class BaseY extends BaseX {
  int field2=2;
}