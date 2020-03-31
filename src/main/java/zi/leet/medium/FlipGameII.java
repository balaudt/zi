package zi.leet.medium;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/flip-game-ii
public class FlipGameII {
    private Map<Map.Entry<Long, Boolean>, Boolean> memo = new HashMap<>();
    private int n;

    public boolean canWin(String s) {
        if (s == null || s.isEmpty()) return false;
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++)
            c[i] = c[i] == '+' ? '1' : '0';
        n = c.length;
        long state = Long.parseLong(new String(c), 2);
        return canWin(state, true);
    }

    private boolean canWin(long state, boolean isFirst) {
        Map.Entry<Long, Boolean> entry = new AbstractMap.SimpleEntry<>(state, isFirst);
        if (memo.containsKey(entry)) {
            return memo.get(entry);
        }
        if (isFirst) {
            for (int i = 0; i < n - 1; i++) {
                int flag = 3 << i;
                if ((state & flag) == flag) {
                    state &= ~flag;
                    if (canWin(state, false)) {
                        memo.put(entry, true);
                        return true;
                    }
                    state |= flag;
                }
            }
            memo.put(entry, false);
            return false;
        } else {
            for (int i = 0; i < n - 1; i++) {
                int flag = 3 << i;
                if ((state & flag) == flag) {
                    state &= ~flag;
                    if (!canWin(state, true)) {
                        memo.put(entry, false);
                        return false;
                    }
                    state |= flag;
                }
            }
            memo.put(entry, true);
            return true;
        }
    }

    public static void main(String[] args) {
        FlipGameII game = new FlipGameII();
        System.out.println(game.canWin("++++++"));
    }
}
