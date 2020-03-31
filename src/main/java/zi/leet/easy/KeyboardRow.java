package zi.leet.easy;

import java.util.*;

//https://leetcode.com/problems/keyboard-row
public class KeyboardRow {
    public String[] findWords(String[] words) {
        Map<Character, Integer> rowMap = new HashMap<Character, Integer>() {
            {
                put('q', 1);
                put('w', 1);
                put('e', 1);
                put('r', 1);
                put('t', 1);
                put('y', 1);
                put('u', 1);
                put('i', 1);
                put('o', 1);
                put('p', 1);

                put('a', 2);
                put('s', 2);
                put('d', 2);
                put('f', 2);
                put('g', 2);
                put('l', 2);
                put('k', 2);
                put('j', 2);
                put('h', 2);

                put('z', 3);
                put('x', 3);
                put('c', 3);
                put('b', 3);
                put('n', 3);
                put('m', 3);
                put('v', 3);
            }
        };
        List<String> out = new ArrayList<>();
        for (String word : words) {
            int row = rowMap.get(word.toLowerCase().charAt(0));
            boolean isSingleRow = true;
            for (char c : word.toLowerCase().toCharArray()) {
                if (rowMap.get(c) != row) {
                    isSingleRow = false;
                    break;
                }
            }
            if (isSingleRow) {
                out.add(word);
            }
        }
        return out.toArray(new String[0]);
    }

}
