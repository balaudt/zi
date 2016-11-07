package zi.chef.y16.feb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by balaudt on 9/4/16.
 */
public class CyclicWords {
    public static final int P = (int) (1e7 + 1);

    public int differentCW(String[] words) {
        Map<char[], Integer> uniqueWords = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            char[] w = words[i].toCharArray();
            char[] curWord = new char[w.length * 2];
            System.arraycopy(w, 0, curWord, 0, w.length);
            System.arraycopy(w, 0, curWord, w.length, w.length);
            boolean isUnique = true;
            for (Map.Entry<char[], Integer> entry : uniqueWords.entrySet()) {
                char[] uniqWord = entry.getKey();
                if (uniqWord.length * 2 != curWord.length) {
                    continue;
                }
                Integer hash = entry.getValue();
                int rollHash = hash(curWord, false);
                for (int j = w.length; j < curWord.length; j++) {
                    if (rollHash == hash)
                        break;

                }
            }
            if (isUnique) {
                uniqueWords.put(w, hash(w, true));
            }
        }
        return uniqueWords.size();
    }

    private int hash(char[] in, boolean isFull) {
        int length = isFull ? in.length : in.length / 2;
        int hash = 0;
        for (int i = 0; i < length; i++) {
            hash *= 31;
            hash %= P;
            hash += in[i];
            hash %= P;
        }
        return hash;
    }

    public static void main(String[] args) {

    }

}
