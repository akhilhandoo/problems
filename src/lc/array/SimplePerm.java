package lc.array;

import java.util.ArrayList;
import java.util.List;

public class SimplePerm {

    public static void main(String[] args) {
        SimplePerm perm = new SimplePerm();
        //perm.printPerms("akhil".toCharArray());
        List<List<Integer>> permutations = perm.permute(new int[]{1, 2, 3});
        for (List<Integer> singlePermuatation: permutations) {
            System.out.println(singlePermuatation);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> toReturn = new ArrayList<>();
        permuteHelper(toReturn, new int[]{}, nums);
        return toReturn;
    }

    public void permuteHelper(List<List<Integer>> accumulator, int[] prefix, int[] residue) {
        if (residue.length == 0) {
            List<Integer> baseCase = new ArrayList<>(prefix.length);
            for (int item: prefix) {
                baseCase.add(item);
            }
            accumulator.add(baseCase);
        } else if (residue.length == 1) {
            List<Integer> baseCase = new ArrayList<>(prefix.length);
            for (int item: prefix) {
                baseCase.add(item);
            }
            baseCase.add(residue[0]);
            accumulator.add(baseCase);
        } else if (residue.length == 2) {
            List<Integer> baseCase = new ArrayList<>(prefix.length);
            for (int item: prefix) {
                baseCase.add(item);
            }
            baseCase.add(residue[0]);
            baseCase.add(residue[1]);
            accumulator.add(baseCase);

            List<Integer> baseCaseAlt = new ArrayList<>(prefix.length);
            for (int item: prefix) {
                baseCaseAlt.add(item);
            }
            baseCaseAlt.add(residue[1]);
            baseCaseAlt.add(residue[0]);
            accumulator.add(baseCaseAlt);
        } else {
            for (int i=0; i < residue.length; i++) {
                int[] newPrefix = new int[prefix.length + 1];
                System.arraycopy(prefix, 0, newPrefix, 0, prefix.length);
                newPrefix[newPrefix.length - 1] = residue[i];

                int[] newResidue = new int[residue.length - 1];
                int k = 0;
                for (int j = 0; j < residue.length; j++) {
                    if (j != i) {
                        newResidue[k++] = residue[j];
                    }
                }
                permuteHelper(accumulator, newPrefix, newResidue);
            }
        }
    }

    public void printPerms(char[] input) {
        printPermsHelper("".toCharArray(), input);
    }

    public void printPermsHelper(char[] prefix, char[] residue) {
        if (residue.length == 0) {
            System.out.println(prefix);
        } else if (residue.length == 1) {
            String toPrint = new StringBuilder().append(prefix).append(residue[0]).toString();
            System.out.println(toPrint);
        } else if (residue.length == 2) {
            String toPrint = new StringBuilder().append(prefix).append(residue[0]).append(residue[1]).toString();
            System.out.println(toPrint);
            toPrint = new StringBuilder().append(prefix).append(residue[1]).append(residue[0]).toString();
            System.out.println(toPrint);
        } else {
            for (int i=0; i < residue.length; i++) {
                String newPrefix = new String(prefix) + residue[i];
                StringBuilder newResidueBuilder = new StringBuilder();
                newResidueBuilder.append(residue, 0, i);
                newResidueBuilder.append(residue, i+1, residue.length - i - 1);
                printPermsHelper(newPrefix.toCharArray(), newResidueBuilder.toString().toCharArray());
            }
        }
    }
}
