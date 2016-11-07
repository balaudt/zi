package zi.chef.y16.feb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by balaudt on 9/4/16.
 */
public class StrToPali {
    static char[] in;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());
        for (int i = 1; i <= t; i++) {
            in = reader.readLine().toCharArray();
            n = in.length;
            memo = new HashMap<>();
            System.out.println("Case " + i + ": " + score(0, n - 1));
        }
    }

    static Map<Integer, Integer> memo;

    static int n;

    static int score(int st, int end) {
        if (st >= end)
            return 0;
        int key = st * n + end;
        if (memo.containsKey(key))
            return memo.get(key);
        int out = 0;
        if (in[st] == in[end]) {
            out = score(st + 1, end - 1);
        } else {
            //Change in[st] as in[end] or vice versa
            out = 1 + score(st + 1, end - 1);
            //Delete char at in[st]
            out = Math.min(out, 1 + score(st + 1, end));
            //Delete char at in[end]
            out = Math.min(out, 1 + score(st, end - 1));
        }
        memo.put(key, out);
        return out;
    }
}
