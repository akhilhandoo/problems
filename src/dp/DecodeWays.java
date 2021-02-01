package dp;

public class DecodeWays {

  public static void main(String[] args) {
    String number = "371250617";
    System.out.println(new DecodeWays().numDecodings2(number));
//    new DecodeWays().printOneToHundred();
  }

  public void printOneToTen() {
    printOneTo(10);
  }

  public void printOneToHundred() {
    printOneTo(100);
  }

  public void printOneTo(int number) {
    printNumbersWhileUnwinding(number, 1);
  }

  public void printNumbersWhileUnwinding(int number, int base) {
    if (number == base) {
      System.out.println(number);
      return;
    } else {
      printNumbersWhileUnwinding(number - 1, base);
      System.out.println(number);
    }
  }

  public int numDecodings(String s) {
    if (null == s || s.length() == 0) {
      return 0;
    } else if (s.length() == 1) {
      if (Integer.parseInt(s) > 0) {
        return 1;
      } else {
        return 0;
      }
    } else if (Integer.parseInt(s) == 0) {
      return 0;
    } else if (s.length() > 1 && s.startsWith("0")) {
      return 0;
    } else if (s.endsWith("0")) {
      String lastTwo = s.substring(s.length() - 2);
      if (Integer.parseInt(lastTwo) > 26 || Integer.parseInt(lastTwo) < 1) {
        return 0;
      }
    }
    int[] data = new int[s.length()];
    data[0] = 1;
    for (int index = 1; index < s.length(); index++) {
      boolean candidateDuo = false;
      if (Integer.parseInt(s.substring(index - 1, index + 1)) <= 26) {
        candidateDuo = true;
      }
      if (candidateDuo) {
        if (Integer.parseInt("" + s.charAt(index)) == 0) {
          data[index] = data[index - 1];
        } else if (Integer.parseInt("" + s.charAt(index - 1)) == 0) {
          if (index - 2 >= 0) {
            if (Integer.parseInt(s.substring(index - 2, index)) <= 26) {
              data[index] = data[index - 1] + 2;
            } else {
              data[index] = data[index - 1];
            }
          } else {
            data[index] = data[index - 1];
          }
        } else {
          data[index] = data[index - 1] + 1;
        }
      } else {
        data[index] = data[index - 1];
      }
    }
    return data[s.length() - 1];
  }


  public int numDecodings1(String s) {
    if (null == s || s.length() == 0 || s.startsWith("0")) {
      return 0;
    } else {
      int[] result = new int[s.length()];
      result[0] = 1;
      for (int index = 1; index < s.length(); index++) {
        int num = Integer.parseInt("" + s.charAt(index));
        int numTogether = Integer.parseInt("" + s.charAt(index - 1) + "" + s.charAt(index));
        boolean standsAlone = num > 0 && num <= 26;
        boolean standsTogether = s.charAt(index - 1) != '0' && numTogether > 0 && numTogether <= 26;
        if (standsAlone && standsTogether) {
          result[index] = result[index - 1] + 1;
        } else if (standsAlone && !standsTogether) {
          result[index] = result[index - 1];
        } else if (!standsAlone && standsTogether) {
          if ((index - 2) >= 0) {
            result[index] = result[index - 2];
          } else {
            result[index] = result[index - 1];
          }
        } else {
          return 0;
        }
      }
      return result[s.length() - 1];
    }
  }

  public int numDecodings2(String s) {
    int[] result = new int[s.length() +1];
    if(s.charAt(0) != '0')
      result[0] = 1;
    else
      result[0] = 0;
    for(int i=1; i<result.length; i++){
      int ways2 = 0, ways1 = 0;
      if(s.charAt(i-1) != '0'){
        ways1 = result[i-1];
      }
      if(i-2 >= 0 && s.charAt(i-2) != '0')
        if(Integer.parseInt(s.substring(i-2, i)) > 0 && Integer.parseInt(s.substring(i-2, i))<=26){
          ways2 = result[i-2];
        }
      result[i] = ways1 + ways2;
    }
    return result[result.length-1];
  }
}
