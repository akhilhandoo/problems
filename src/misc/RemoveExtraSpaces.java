package misc;

public class RemoveExtraSpaces {

  public static void main(String[] args) {
    System.out.println("[" + RemoveExtraSpaces.
            removeExtraSpaces(
                    "  I   live   on     earth  "
            ) + "]");
  }

  public static String removeExtraSpaces(String source) {
    StringBuilder builder = new StringBuilder();
    for (char ch: source.toCharArray()) {
      if (ch == ' ') {
        if (builder.length() == 0 || builder.charAt(builder.length() - 1) != ' ') {
          builder.append(ch);
        } else {
          continue;
        }
      } else {
        builder.append(ch);
      }
    }
    if (builder.charAt(builder.length() - 1) == ' ') {
      builder.deleteCharAt(builder.length() - 1);
    }
    return builder.toString();
  }
}
