package zi.leet.easy;

import java.util.*;

//https://leetcode.com/problems/find-common-characters
public class CommonCharacters {
    public List<String> commonChars(String[] A) {
        Map<Character, Integer> commonFrequencies = getFrequencies(A[0]);
        for (int i = 1; i < A.length; i++) {
            Map<Character, Integer> strFrequency = getFrequencies(A[i]);
            Iterator<Map.Entry<Character, Integer>> it = commonFrequencies.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Character, Integer> entry = it.next();
                if (strFrequency.containsKey(entry.getKey()))
                    entry.setValue(Math.min(entry.getValue(), strFrequency.get(entry.getKey())));
                else
                    it.remove();
            }
        }
        List<String> out = new ArrayList<>();
        commonFrequencies.forEach((key, value) -> {
            for (int i = 0; i < value; i++)
                out.add(key.toString());
        });
        return out;
    }

    private Map<Character, Integer> getFrequencies(String str) {
        Map<Character, Integer> out = new HashMap<>();
        for (char c : str.toCharArray()) {
            if (out.containsKey(c)) {
                out.put(c, out.get(c) + 1);
            } else {
                out.put(c, 1);
            }
        }
        return out;
    }

    public static void main(String[] args) {
        CommonCharacters commonCharacters = new CommonCharacters();
        System.out.println(commonCharacters.commonChars(new String[]{"bella", "label", "roller"}));
        System.out.println(commonCharacters.commonChars(new String[]{"cool", "lock", "cook"}));
    }
}
