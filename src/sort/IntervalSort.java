package sort;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class IntervalSort {

  public static void main(String[] args) {

    final int meetingTimeInMinitues = 25;
    String calendar1String = "{1000, 1130}; {1155, 1300}; {1330, 1415}; {1415, 1545}; {1705, 1830}";
    String calendar2String = "{0900, 1005}; {1200, 1315}; {1400, 1420}; {1605, 1830}";
    List<Interval> calendar1 = Interval.parse(calendar1String);
    List<Interval> calendar2 = Interval.parse(calendar2String);

    List<Interval> merged = new ArrayList<>();

    int index1 = 0;
    int index2 = 0;
    while ( index1 < calendar1.size() || index2 < calendar2.size() ) {

      if (index1 == calendar1.size()) {
        merged.add(calendar2.get(index2++));
      } else if (index2 == calendar2.size()) {
        merged.add(calendar1.get(index1++));
      } else {
        int compareToResult = calendar1.get(index1).compareTo(calendar2.get(index2));
        if ( compareToResult < 0) {
          merged.add(calendar1.get(index1++));
        } else if (compareToResult > 0) {
          merged.add(calendar2.get(index2++));
        } else {
          merged.add(new Interval(calendar1.get(index1).getBegin() <= calendar2.get(index2).getBegin() ? calendar1.get(index1).getBegin() : calendar2.get(index2).getBegin(),
                  calendar1.get(index1).getEnd() < calendar2.get(index2).getEnd() ? calendar2.get(index2).getEnd() : calendar1.get(index1).getEnd()));
          index1++;
          index2++;
        }
      }
    }

    List<Interval> simplifiedIntervals = Interval.removeOverlaps(merged);

    boolean found = false;
    for (int index = 0; index < simplifiedIntervals.size() - 1; index++) {
      if (simplifiedIntervals.get(index).intervalInMinutes(simplifiedIntervals.get(index + 1)) >= meetingTimeInMinitues) {
        System.out.println("We have an empty slot between " + simplifiedIntervals.get(index).getEnd() + " & " + simplifiedIntervals.get(index + 1).getBegin());
        found = true;
      }
    }

    if (!found) {
      System.out.println("We don't have a big enough empty slot.");
    }

//    String inputString = "{1000, 1130}; {1145, 1300}; {1230, 1500}; {1345, 1545}; {1315, 1700}; {1605, 1830}";
//    List<Interval> inputIntervals = Interval.parse(inputString);
//    List<Interval> disjoint = Interval.removeOverlaps(inputIntervals);
//    System.out.println(disjoint);

  }

}

class Interval implements Comparable {
  private int begin;
  private int end;

  public Interval(int begin, int end) {
    this.begin = begin;
    this.end = end;
  }

  public int getBegin() {
    return begin;
  }

  public int getEnd() {
    return end;
  }

  @Override
  public int compareTo(Object o) {
    if (null == o) {
      throw new IllegalArgumentException("Provided object is null.");
    }
    if (o instanceof Interval) {
      if (this.end < ((Interval) o).begin) {
        return -1;
      } else if (this.begin > ((Interval) o).end) {
        return 1;
      } else {
        return 0;
      }
    } else {
      throw new IllegalArgumentException("Provided object not an instance of class Interval.");
    }
  }

  //  {1000, 1130} {1145, 1300} - 15 - 0 hr & 15 mins - 15
  //  {1230, 1250} {1310, 1330} - 20 - 1 hr & -40 mins - 60-40 - 20 mins
  //  {0810, 0855} {1210, 1230} - 195 - 4 hr & -45 mins - 3 hr & 15 mins - 180+15 - 195
  //  {1320, 1345} {1755, 1800} - 250 - 4 hr & 10 mins - 240+10 - 250
  public int intervalInMinutes(Interval another) {
    if (this.compareTo(another) < 0) {
      return minus(another.begin, this.end);
    } else if (this.compareTo(another) > 0) {
      return minus(this.begin, another.end);
    } else {
      return 0;
    }
  }

  private int minus(int a, int b) {
    int aa = a / 100;
    int aa_min = a % 100;
    int bb = b / 100;
    int bb_min = b % 100;

    int possibleHrs = aa - bb;
    int possibleMins = aa_min - bb_min;

    if (possibleMins > 0) {
      return 60*possibleHrs + possibleMins;
    } else {
      return 60*(possibleHrs-1) + (60-Math.abs(possibleMins));
    }
  }

  //  {1000, 1130}; {1145, 1300}; {1430, 1500}; {1615, 1645}; {1700, 1830}
  public static List<Interval> parse(String intervalList) {
    List<Interval> toReturn = new ArrayList<>();
    String[] intervalTokens = intervalList.split("\\;");
    intervalTokens = Arrays.stream(intervalTokens)
            .map(x -> {
              String trimmed = x.trim();
              return trimmed.substring(1, 11);
            }).collect(Collectors.toList())
            .toArray(new String[0]);
    for (String token: intervalTokens) {
      String[] beginAndEnd = token.split(",");
      int begin = Integer.parseInt(beginAndEnd[0].trim());
      int end = Integer.parseInt(beginAndEnd[1].trim());
      toReturn.add(new Interval(begin, end));
    }
    return toReturn;
  }

  //  {1000, 1130}; {1145, 1300}; {1230, 1500}; {1345, 1545}; {1315, 1700} {1705, 1830}
  //  {1000, 1130}; {1145, 1700} {1705, 1830}
  public static List<Interval> removeOverlaps(List<Interval> input) {
    List<Interval> toReturn = new ArrayList<>();
    Interval temp = input.get(0);
    boolean ongoingInterval = false;
    for (int index = 1; index < input.size(); index++) {
      if (input.get(index).getBegin() <= input.get(index - 1).getEnd()) {
        ongoingInterval = true;
        continue;
      } else {
        toReturn.add(new Interval(temp.getBegin(), input.get(index - 1).getEnd()));
        temp = input.get(index);
        ongoingInterval = false;
      }
    }
    if (ongoingInterval) {
      toReturn.add(new Interval(temp.getBegin(), input.get(input.size() - 1).getEnd()));
    } else {
      toReturn.add(input.get(input.size() - 1));
    }
    return toReturn;
  }

  @Override
  public String toString() {
    return "Interval{" +
            "begin=" + begin +
            ", end=" + end +
            '}';
  }
}
