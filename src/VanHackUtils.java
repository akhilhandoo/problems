import org.apache.commons.codec.digest.DigestUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class VanHackUtils {

  public static void main(String[] args) throws MalformedURLException {
//    BigDecimal decimal = new BigDecimal("234.50");
//    BigDecimal rounded = decimal.setScale(0, RoundingMode.HALF_EVEN);
//    System.out.println(rounded);
//    getTransactions(0, 0, 0, 0);

//    BigDecimal decimal = new BigDecimal(removeCommaFromDecimal("3,465.29"));
//    System.out.println(decimal);

//    String content = "{\"page\":\"37\",\"per_page\":10,\"total\":76,\"total_pages\":37,\"data\":[]}";
//    String totalPagesString = content.substring(content.indexOf("total_pages") + 13, content.indexOf(",\"data\""));
//    System.out.println("[" + totalPagesString + "]");

    //System.out.println(getTransactions(337, 8, 5, 50));

//    Scanner sc = new Scanner(System.in);
//
//    String[] count = sc.nextLine().split(" ");
//
//    EngineerFirm e = new EngineerFirm(Integer.parseInt(count[0]));
//    AccountantFirm a = new AccountantFirm(Integer.parseInt(count[1]));
//
//    count = sc.nextLine().split(" ");
//
//    int[] incomeEngineers = new int[count.length];
//    for (int i=0; i < count.length; i++) {
//      incomeEngineers[i] = Integer.parseInt(count[i]);
//    }
//    e.assignSalaries(incomeEngineers);
//
//    count = sc.nextLine().split(" ");
//    int[] incomeAccountants = new int[count.length];
//    for (int i=0; i < count.length; i++) {
//      incomeAccountants[i] = Integer.parseInt(count[i]);
//    }
//    a.assignSalaries(incomeAccountants);
//
//    e.averageSalary();
//    e.maxSalary();
//    e.minSalary();
//
//    a.averageSalary();
//    a.maxSalary();
//    a.minSalary();

//    List<Integer> stockData = Arrays.asList(new Integer[]{5, 6, 8, 4, 9, 10, 8, 3, 6, 4});
//    List<Integer> queries = Arrays.asList(new Integer[]{3, 1, 8}); // 2 4 -1
//    List<Integer> answers = predictAnswer(stockData, queries);
//    System.out.println(answers);

//    List<Integer> patience = Arrays.asList(new Integer[]{1, 2, 3, 4});
//    int busSize = 2;
//    List<Integer> queries = Arrays.asList(new Integer[]{1, 3, 4});
//    List<Integer> answers = kthPerson(busSize, patience, queries);
//    System.out.println(answers);

    System.out.println(DigestUtils.md5Hex("roshan                                                                          "));
    //System.out.println(DigestUtils.md5Hex("ashvin"));
  }


  public static List<Integer> kthPerson(int k, List<Integer> p, List<Integer> q) {
    List<Integer> toReturn = new ArrayList<>();
    for (int time : q) {
      toReturn.add(getLastIndexToBoard(k, p, time));
    }
    return toReturn;
  }

  private static int getLastIndexToBoard(int busSize, List<Integer> patience, int busTime) {
    int filled = 0;
    int index = 0;
    int maxIndex = 0;
    while (index < patience.size() && filled < busSize) {
      if (patience.get(index) >= busTime) {
        filled++;
        maxIndex = index;
      }
      index++;
    }
    if (filled < busSize) {
      return 0;
    } else {
      return maxIndex + 1;
    }
  }

//  private static class PatienceIndexPair {
//    private int patience;
//    private int originalIndex;
//
//    public PatienceIndexPair(int patience, int originalIndex) {
//      this.patience = patience;
//      this.originalIndex = originalIndex;
//    }
//
//    public int getPatience() {
//      return patience;
//    }
//
//    public int getOriginalIndex() {
//      return originalIndex;
//    }
//  }







  public static List<Integer> predictAnswer(List<Integer> stockData, List<Integer> queries) {
    List<Integer> toReturn = new ArrayList<>();
    for (Integer query: getZeroBasedIndices(queries)) {
      toReturn.add(getNearestNeighbourWithLessValue(stockData, query));
    }
    return getOneBasedIndices(toReturn);
  }

  public static List<Integer> getZeroBasedIndices(List<Integer> queryIndices) {
    return queryIndices.stream().map(x -> x-1).collect(Collectors.toList());
  }

  public static List<Integer> getOneBasedIndices(List<Integer> queryIndices) {
    return queryIndices.stream().map( x -> {
      if (x != -1) {
        return x + 1;
      } else {
        return x;
      }
    }).collect(Collectors.toList());
  }

  private static int getNearestNeighbourWithLessValue(List<Integer> data, int anchor) {
    int leftIndexWithLessValue = findFirstLessToLeft(data, anchor);
    int rightIndexWithLessValue = findFirstLessToRight(data, anchor);

    if (leftIndexWithLessValue != -1 && rightIndexWithLessValue != -1) {
      int distanceToLeft = Math.abs(anchor - leftIndexWithLessValue);
      int distanceToRight = Math.abs(anchor - rightIndexWithLessValue);
      return (distanceToLeft <= distanceToRight) ? leftIndexWithLessValue : rightIndexWithLessValue;
    } else {
      if (leftIndexWithLessValue == -1) {
        return rightIndexWithLessValue;
      } else {
        return leftIndexWithLessValue;
      }
    }
  }

  private static int findFirstLessToLeft(List<Integer> data, int anchor) {
    for (int index = anchor - 1; index >= 0; index--) {
      if (data.get(index) < data.get(anchor)) {
        return index;
      }
    }
    return -1;
  }

  private static int findFirstLessToRight(List<Integer> data, int anchor) {
    for (int index = anchor + 1; index < data.size(); index++) {
      if (data.get(index) < data.get(anchor)) {
        return index;
      }
    }
    return -1;
  }

  public static int getTransactions(int userId, int locationId, int netStart, int netEnd) {
    String baseURL = "https://jsonmock.hackerrank.com/api/transactions/search?userId=" + userId;
    SearchResults results = null;
    BigDecimal runningTotal = new BigDecimal("0.0");
    try {
      results = getSearchResults(baseURL);
      for (PaginatedResult resultPage: results.getResultPages()) {
        if (resultPage.pageNo <= resultPage.totalPages) {
          runningTotal = runningTotal.add(getSumTotalAmountInPage(resultPage.content, locationId, netStart, netEnd));
        }
      }
    } catch (IOException ie) {
      ie.printStackTrace();
    } catch (ScriptException se) {
      se.printStackTrace();
    }
    return runningTotal.setScale(0, RoundingMode.HALF_EVEN).intValue();
  }

  private static BigDecimal getSumTotalAmountInPage(String jsonMessage, int locationId, int netStart, int netEnd) throws ScriptException {
    ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
    Object ob = engine.eval("Java.asJSONCompatible(" + jsonMessage + ")");
    Map<String, Object> objectMap = null;
    if (! (ob instanceof Map)) {
      return BigDecimal.ZERO;
    } else {
      objectMap = (Map<String, Object>)ob;
    }
    List<Object> dataList = (List<Object>)objectMap.get("data");
    BigDecimal totalInPage = new BigDecimal("0.0");
    for (Object object: dataList) {
      Map<String, Object> transaction = (Map<String, Object>)object;
      String ip = (String)transaction.get("ip");
      int firstByte = Integer.parseInt(ip.substring(0, ip.indexOf(".")));
      if (firstByte >= netStart && firstByte <= netEnd) {
        Map<String, Object> locationObject = (Map<String, Object>)transaction.get("location");
        if (locationId == (Integer)locationObject.get("id")) {
          String transactionAmountString = (String)transaction.get("amount");
          transactionAmountString = transactionAmountString.substring(1);
          String sanitizedTransactionAmountString = removeCommaFromDecimal(transactionAmountString);
          totalInPage = totalInPage.add(new BigDecimal(sanitizedTransactionAmountString));
        }
      }
    }
    return totalInPage;
  }

  private static String removeCommaFromDecimal(String source) {
    StringBuilder toReturn = new StringBuilder();
    for (char ch: source.toCharArray()) {
      if (ch != ',') {
        toReturn.append(ch);
      }
    }
    return toReturn.toString();
  }

  private static SearchResults getSearchResults(String baseUrl) throws IOException {
    int pageNo = 1;
    SearchResults searchResults = new SearchResults();
    searchResults.withNextResultsPage(getPaginatedResults(baseUrl, pageNo));
    pageNo = 2;
    while (pageNo <= searchResults.getResultPages().get(0).totalPages) {
      searchResults.withNextResultsPage(getPaginatedResults(baseUrl, pageNo++));
    }
    return searchResults;
  }

  private static PaginatedResult getPaginatedResults(String baseUrl, int pageNo) throws IOException {
    URL url = new URL(baseUrl + "&page=" + pageNo);
    try (InputStream is = url.openStream();
         BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.defaultCharset()));) {
      StringBuilder responseBuilder = new StringBuilder();
      int nextChar;
      while ((nextChar = reader.read()) != -1) {
        responseBuilder.append((char)nextChar);
      }
      String content = responseBuilder.toString();
      int totalPages = Integer.parseInt(content.substring(content.indexOf("total_pages") + 13, content.indexOf(",\"data\"")));
      return new PaginatedResult(pageNo, totalPages, content);
    }
  }


  private static class SearchResults {

    private List<PaginatedResult> resultPages;

    public SearchResults() {
      resultPages = new ArrayList<>();
    }

    public SearchResults withNextResultsPage(PaginatedResult paginatedResult) {
      resultPages.add(paginatedResult);
      return this;
    }

    public List<PaginatedResult> getResultPages() {
      return resultPages;
    }
  }

  private static class PaginatedResult {
    private int pageNo;
    private int totalPages;
    private String content;

    public PaginatedResult(int pageNo, int totalPages, String content) {
      this.pageNo = pageNo;
      this.totalPages = totalPages;
      this.content = content;
    }

    public int getPageNo() {
      return pageNo;
    }

    public int getTotalPages() {
      return totalPages;
    }

    public String getContent() {
      return content;
    }
  }
}


interface Company {
  void assignSalaries(int[] salaries);
  void averageSalary();
  void maxSalary();
  void minSalary();
}

abstract class Firm implements Company {
  private int[] income;
  private int max;
  private int min;
  private float average;

  public Firm(int n) {
    income = new int[n];
    max = 0;
    min = Integer.MAX_VALUE;
    average = 0f;
  }

  @Override
  public void assignSalaries(int[] salaries) {
    int total = 0;
    for (int index = 0; index < income.length; index++) {
      if (index < salaries.length) {
        income[index] = salaries[index];
        if (income[index] > max) {
          max = income[index];
        }
        if (income[index] < min) {
          min = income[index];
        }
        total += income[index];
      } else {
        break;
      }
    }
    average = total / (float)Math.min(salaries.length, income.length);
    System.out.println("Incomes of " + getSpecializationToken() + " credited");
  }

  @Override
  public void averageSalary() {
    System.out.printf("Average salary of " + getSpecializationToken() + " is %.2f", average);
    System.out.println();
  }

  @Override
  public void maxSalary() {
    System.out.println("Maximum salary amongst " + getSpecializationToken() + " is " + max);
  }

  @Override
  public void minSalary() {
    System.out.println("Minimum salary amongst " + getSpecializationToken() + " is " + min);
  }

  public abstract String getSpecializationToken();
}

class EngineerFirm extends Firm {

  public EngineerFirm(int n) {
    super(n);
  }

  @Override
  public String getSpecializationToken() {
    return "engineers";
  }
}

class AccountantFirm extends Firm {

  public AccountantFirm(int n) {
    super(n);
  }

  @Override
  public String getSpecializationToken() {
    return "accountants";
  }
}