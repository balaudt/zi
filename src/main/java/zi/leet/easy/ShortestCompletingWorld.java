package zi.leet.easy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/shortest-completing-word
public class ShortestCompletingWorld {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        Map<Character, Integer> license = getFrequencies(licensePlate.toLowerCase().replaceAll(" ", "").replaceAll("\\d", ""));
        Arrays.sort(words, Comparator.comparingInt(String::length));
        for (String word : words) {
            Map<Character, Integer> wordMap = getFrequencies(word);
            boolean found = true;
            for (Map.Entry<Character, Integer> entry : license.entrySet()) {
                Character letter = entry.getKey();
                if (!wordMap.containsKey(letter) || wordMap.get(letter) < entry.getValue()) {
                    found = false;
                    break;
                }
            }
            if (found)
                return word;
        }
        return null;
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
        ShortestCompletingWorld world = new ShortestCompletingWorld();
        System.out.println(world.shortestCompletingWord("1s3 PSt", new String[]{"step", "steps", "stripe", "stepple"}));
        System.out.println(world.shortestCompletingWord("1s3 456", new String[]{"looks", "pest", "stew", "show"}));
    }
}
