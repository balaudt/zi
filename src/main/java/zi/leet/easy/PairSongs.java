package zi.leet.easy;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60
public class PairSongs {
    public int numPairsDivisibleBy60(int[] time) {
        Map<Integer, Integer> modCounts = new HashMap<>();
        for (int i = 0; i < time.length; i++) {
            int mod = time[i] % 60;
            if (modCounts.containsKey(mod)) {
                modCounts.put(mod, modCounts.get(mod) + 1);
            } else {
                modCounts.put(mod, 1);
            }
        }
        int result = 0;
        for (Map.Entry<Integer, Integer> entry : modCounts.entrySet()) {
            Integer lsum = entry.getKey();
            if (modCounts.containsKey(lsum)) {
                result += lsum * modCounts.get(lsum);
                entry.setValue(0);
            }
        }
        return result;
    }

}
