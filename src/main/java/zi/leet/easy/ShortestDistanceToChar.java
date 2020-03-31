package zi.leet.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/shortest-distance-to-a-character
public class ShortestDistanceToChar {
    public int[] shortestToChar(String S, char C) {
        List<Integer> cPos = new ArrayList<>();
        int len = S.length();
        for (int i = 0; i < len; i++)
            if (S.charAt(i) == C) cPos.add(i);
        int prev = -1, next = cPos.get(0), cInd = 1;
        int[] out = new int[len];
        for (int i = 0; i < len; i++) {
            if (i == next) {
                prev = next;
                next = cInd == cPos.size() - 1 ? -1 : cPos.get(cInd++);
                out[i] = 0;
            } else {
                out[i] = prev == -1 ? next : (next == -1 ? i - prev : Math.min(i - prev, next - i));
            }
        }
        return out;
    }

    public static void main(String[] args) {
        ShortestDistanceToChar distance = new ShortestDistanceToChar();
        System.out.println(Arrays.toString(distance.shortestToChar("loveleetcode", 'e')));
    }
}
