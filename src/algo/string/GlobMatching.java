package algo.string;

public class GlobMatching {

  public static void main(String[] args) {
    String fileName = "abcdefg";
    String pattern = "a*e?g";
    System.out.println(globMatching(fileName, pattern));
  }

  /*
      abcdefg
      a*e?g
   */
  public static boolean globMatching(String fileName, String pattern) {
    String fileNameToSend = new StringBuilder(fileName).reverse().toString();
    String patternToSend = new StringBuilder(pattern).reverse().toString();
    return matches(fileNameToSend, patternToSend);
  }

  private static boolean matches(String source, String pattern) {
    int patternIndex = 0;
    int candidateIndex = 0;
    while (candidateIndex < source.length()) {
      Pattern patternMatcher = Pattern.getByMatchCharacter("" + pattern.charAt(patternIndex));
      if (patternMatcher.matcher.matches(pattern.charAt(patternIndex), source.charAt(candidateIndex))) {
        candidateIndex++;
        patternIndex = patternMatcher.matcher.nextIndex(patternIndex);
      } else {
        return false;
      }
    }
    return true;
  }

  static enum Pattern {
    STAR("*", new StarPatternMatcher()),
    QUESTION("?", new QuestionMarkPatternMatcher()),
    LITERAL("", new LiteralPatternMatcher());

    private String matchCharacter;
    private Matcher matcher;
    Pattern(String matchCharacter, Matcher matcher) {
      this.matchCharacter = matchCharacter;
      this.matcher = matcher;
    }

    public static Pattern getByMatchCharacter(String matchCharacter) {
      for (Pattern pattern: Pattern.values()) {
        if (pattern.matchCharacter.equals(matchCharacter)) {
          return pattern;
        }
      }
      return LITERAL;
    }
  }
}

interface Matcher {
  public boolean matches(char ch, char toCompare);
  public int nextIndex(int index);
}

class StarPatternMatcher implements Matcher {
  @Override
  public boolean matches(char ch, char toCompare) {
    return true;
  }
  @Override
  public int nextIndex(int index) {
    return index;
  }
}

class QuestionMarkPatternMatcher implements Matcher {

  @Override
  public boolean matches(char ch, char toCompare) {
    return true;
  }

  @Override
  public int nextIndex(int index) {
    return index + 1;
  }
}

class LiteralPatternMatcher implements Matcher {

  @Override
  public boolean matches(char ch, char toCompare) {
    return ch == toCompare;
  }

  @Override
  public int nextIndex(int index) {
    return index + 1;
  }
}