package zi.leet.medium;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/letter-tile-possibilities
public class LetterTile {
    private int result = 0, n;
    private char[] in;
    private Set<String> found;

    public int numTilePossibilities(String tiles) {
        in = tiles.toCharArray();
        n = in.length;
        found = new HashSet<>();
        for (int i = 1; i <= n; i++)
            generate(0, 0, new char[i]);
        return result;
    }

    private void generate(int st, int cur, char[] sel) {
        if (cur == sel.length) {
            String curStr = new String(sel);
            if (!found.contains(curStr)) {
                found.add(curStr);
                result++;
            }
            System.out.println(curStr);
            return;
        }
        for (int i = st; i <= n - sel.length + cur; i++) {
            sel[cur] = in[i];
            generate(i + 1, cur + 1, sel);
        }
    }

    public static void main(String[] args) {
        LetterTile letterTile = new LetterTile();
        System.out.println(letterTile.numTilePossibilities("AAB"));
    }
}
