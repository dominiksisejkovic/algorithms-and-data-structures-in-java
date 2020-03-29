package dsisejkovic.algortihms;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dsisejkovic on 24.7.2015..
 */
public class WordPermutation {

    public List<String> getPermutationList(String str) {
        List<String> permutationList = new LinkedList<>();
        doPermutation(permutationList, "", str);

        return permutationList;

    }

    // O(n!)
    private void doPermutation(List<String> permutationList, String prefix, String str) {
        if (str.length() == 0) {
            permutationList.add(prefix);
        } else {
            for (int i = 0; i < str.length(); i++) {
                doPermutation(permutationList, prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, str.length()));
            }
        }
    }

    public static void main(String[] args) {
        WordPermutation wp = new WordPermutation();

        List<String> list = wp.getPermutationList("123456");

        list.forEach(System.out::println);
        System.out.println("Size: " + list.size()); // n!
    }

}
