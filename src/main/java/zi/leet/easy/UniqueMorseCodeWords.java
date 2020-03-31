package zi.leet.easy;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/unique-morse-code-words
public class UniqueMorseCodeWords {
    public int uniqueMorseRepresentations(String[] words) {
        String[] morseCodes = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-",
                ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        Set<String> wordCodes = new HashSet<>();
        for (String word : words) {
            StringBuffer buffer = new StringBuffer();
            for (char c : word.toCharArray()) {
                buffer.append(morseCodes[c - 'a']);
            }
            wordCodes.add(buffer.toString());
        }
        return wordCodes.size();
    }

    public static void main(String[] args) {
        UniqueMorseCodeWords words = new UniqueMorseCodeWords();
        System.out.println(words.uniqueMorseRepresentations(new String[]{"gin", "zen", "gig", "msg"}));
    }
}
