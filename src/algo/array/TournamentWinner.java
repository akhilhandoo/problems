package algo.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TournamentWinner {

    public static void main(String[] args) {
        ArrayList<ArrayList<String>> competitions = new ArrayList<>();

        ArrayList<String> temp = new ArrayList<>();
        temp.add("HTML");
        temp.add("Java");
        competitions.add(temp);

        temp = new ArrayList<>();
        temp.add("Java");
        temp.add("Python");
        competitions.add(temp);

        temp = new ArrayList<>();
        temp.add("Python");
        temp.add("HTML");
        competitions.add(temp);

        temp = new ArrayList<>();
        temp.add("C#");
        temp.add("Python");
        competitions.add(temp);

        temp = new ArrayList<>();
        temp.add("Java");
        temp.add("C#");
        competitions.add(temp);

        temp = new ArrayList<>();
        temp.add("C#");
        temp.add("HTML");
        competitions.add(temp);

        temp = new ArrayList<>();
        temp.add("SQL");
        temp.add("C#");
        competitions.add(temp);

        temp = new ArrayList<>();
        temp.add("HTML");
        temp.add("SQL");
        competitions.add(temp);

        temp = new ArrayList<>();
        temp.add("SQL");
        temp.add("Python");
        competitions.add(temp);

        temp = new ArrayList<>();
        temp.add("SQL");
        temp.add("Java");
        competitions.add(temp);

        ArrayList<Integer> results = new ArrayList<>();
        results.add(0);
        results.add(0);
        results.add(1);
        results.add(1);
        results.add(1);
        results.add(1);
        results.add(1);
        results.add(1);
        results.add(1);
        results.add(1);

        System.out.println(tournamentWinner(competitions, results));
    }

    public static String tournamentWinner(ArrayList<ArrayList<String>> competitions, ArrayList<Integer> results) {
        Map<String, Integer> scores = new HashMap<>();
        String toReturn = "";
        int mostPoints = 0;
        for (int index = 0; index < results.size(); index++) {
            int winnersIndex = -1;
            if (results.get(index) == 1) {
                winnersIndex = 0;
            } else {
                winnersIndex = 1;
            }
            String teamThatWon = competitions.get(index).get(winnersIndex);
            if (scores.containsKey(teamThatWon)) {
                scores.put(teamThatWon, scores.get(teamThatWon) + 3);
            } else {
                scores.put(teamThatWon, 3);
            }
            if (scores.get(teamThatWon) > mostPoints) {
                mostPoints = scores.get(teamThatWon);
                toReturn = teamThatWon;
            }
        }
        return toReturn;
    }
}
