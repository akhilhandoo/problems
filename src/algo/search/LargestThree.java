package algo.search;

public class LargestThree {

  public static void main(String[] args) {
//    int[] array = new int[]{10, 5, 9, 10, 12};
//    int[] answer = findThreeLargestNumbers(array);
//    System.out.println("[ " + answer[0] + ", " + answer[1] + ", " + answer[2] + " ]");

//    System.out.println("/opt/ampool/spark-sjs/logs/jobserver-MSSQL_Sink_context7082629575514216984/spark-job-server.out".substring(37));
//    System.out.println("/opt/ampool/spark-sjs/logs/jobserver-MSSQL_Source_context1189378394965751762/spark-job-server.out".substring(37));
//    System.out.println("/opt/ampool/spark-sjs/logs/jobserver-HDP_Source_context2730948474901293502/spark-job-server.out".substring(37));
//    System.out.println("/opt/ampool/spark-sjs/logs/jobserver-HDP_Sink_context5450731653455221742/spark-job-server.out".substring(37));
//    System.out.println("/opt/ampool/spark-sjs/logs/jobserver-HDP_context2577632698296479350/spark-job-server.out".substring(37));


//    String fromContext = "context7082629575514216984/spark-job-server.out";
//    System.out.println(fromContext.substring(7).substring(0, fromContext.substring(7).indexOf("/")));

    System.out.println("spark-job-server.log.3".substring(21));
    System.out.println("spark-job-server.log.493".substring(21));
    System.out.println("spark-job-server.log.17".substring(21));
  }

  public static int[] findThreeLargestNumbers(int[] array) {
    int max1 = array[0];
    int max2 = Integer.MIN_VALUE;
    int max3 = Integer.MIN_VALUE;
    for (int index = 1; index < array.length; index++) {
      if (array[index] > max1) {
        int temp = max1;
        max1 = array[index];
        max3 = max2;
        max2 = temp;
      } else if (array[index] > max2) {
        int temp = max2;
        max2 = array[index];
        max3 = temp;
      } else if (array[index] > max3) {
        max3 = array[index];
      }
    }
    return new int[]{max3, max2, max1};
  }

//  public static int[] findThreeLargestNumbers(int[] array) {
//    int greater = Integer.MIN_VALUE;
//    for (int index = 1; index < array.length; index++) {
//      if (array[index] > max1) {
//        int temp = max1;
//        max1 = array[index];
//        max3 = max2;
//        max2 = temp;
//      } else if (array[index] > max2) {
//        int temp = max2;
//        max2 = array[index];
//        max3 = temp;
//      } else if (array[index] > max3) {
//        max3 = array[index];
//      }
//    }
//    return new int[]{max1, max2, max3};
//  }
}
