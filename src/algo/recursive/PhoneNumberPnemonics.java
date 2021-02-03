package algo.recursive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneNumberPnemonics {

    public static void main(String[] args) {
        System.out.println(phoneNumberMnemonics("1230"));
    }

    public static ArrayList<String> phoneNumberMnemonics(String phoneNumber) {
        ArrayList<String> accumulator = new ArrayList<String>();
        Map<Integer, List<Character>> references = new HashMap<>();
        ArrayList<Character> temp = new ArrayList<>();
        temp.add('a'); temp.add('b'); temp.add('c');
        references.put(2, temp);
        temp = new ArrayList<>();
        temp.add('d'); temp.add('e'); temp.add('f');
        references.put(3, temp);
        temp = new ArrayList<>();
        temp.add('g'); temp.add('h'); temp.add('i');
        references.put(4, temp);
        temp = new ArrayList<>();
        temp.add('j'); temp.add('k'); temp.add('l');
        references.put(5, temp);
        temp = new ArrayList<>();
        temp.add('m'); temp.add('n'); temp.add('o');
        references.put(6, temp);
        temp = new ArrayList<>();
        temp.add('p'); temp.add('q'); temp.add('r'); temp.add('s');
        references.put(7, temp);
        temp = new ArrayList<>();
        temp.add('t'); temp.add('u'); temp.add('v');
        references.put(8, temp);
        temp = new ArrayList<>();
        temp.add('w'); temp.add('x'); temp.add('y'); temp.add('z');
        references.put(9, temp);
        collectAll(references, phoneNumber, accumulator);
        return accumulator;
    }

    public static void collectAll(Map<Integer, List<Character>> references, String phoneNumber, List<String> accumulator) {
        if (isRealized(phoneNumber)) {
            accumulator.add(phoneNumber);
            return;
        } else {
            int firstUnrealizedIndex = firstUnrealized(phoneNumber);
            List<Character> substitutes = references.get(Integer.parseInt("" + phoneNumber.charAt(firstUnrealizedIndex)));
            for (Character ch: substitutes) {
                collectAll(references, phoneNumber.substring(0, firstUnrealizedIndex) + ch + phoneNumber.substring(firstUnrealizedIndex + 1), accumulator);
            }
        }
    }

    public static int firstUnrealized(String input) {
        for (int index = 0; index < input.length(); index++) {
            if (input.charAt(index) >= 50 && input.charAt(index) <= 57) {
                return index;
            }
        }
        return -1;
    }

    public static final boolean isRealized(String s) {
        for (char ch: s.toCharArray()) {
            if (ch >= 50 && ch <= 57) {
                return false;
            }
        }
        return true;
    }
}
