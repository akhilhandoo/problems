package temp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestClass {

    public static void main(String[] args) {
//        String[] merged = uniqueNames(new String[]{"akhil", "akshay"}, new String[]{"akshay", "cheenu"});
//        for (String name: merged) {
//            System.out.println(name);
//        }

        TextInput input = new NumericInput();
        input.add('1');
        input.add('a');
        input.add('0');
        System.out.println(input.getValue());
    }

    public static String[] uniqueNames(String[] names1, String[] names2) {
        Set<String> setOfNames = new HashSet<>();
        setOfNames.addAll(Arrays.asList(names1));
        setOfNames.addAll(Arrays.asList(names2));
        return setOfNames.toArray(new String[setOfNames.size()]);
    }



    public static class TextInput {
        protected StringBuilder builder;

        TextInput() {
            builder = new StringBuilder();
        }

        public void add(char c) {
            builder.append(c);
        }

        public String getValue() {
            return builder.toString();
        }
    }

    public static class NumericInput extends TextInput {

        public void add(char c) {
            if (c >= 48 && c <= 57) {
              builder.append(c);
            }
        }
    }
}


