package zi.leet.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//https://leetcode.com/problems/beautiful-arrangement
public class BeautifulArrangement {
    private Map<Integer, Set<Integer>> possibilities = new HashMap<>();
    private int ans = 0;
    private int n;

    public int countArrangement(int N) {
        n = N;
        for (int i = 1; i <= N; i++) {
            possibilities.put(i, new HashSet<>());
            int num = i;
            factors(i).forEach(f -> {
                possibilities.get(f).add(num);
                possibilities.get(num).add(f);
            });
        }
        traverse(1, 0);
        return ans;
    }

    private void traverse(int st, int visited) {
        if (st > n) {
            ans++;
            return;
        }
        for (Integer x : possibilities.get(st)) {
            int ind = x - 1;
            int bitSet = 1 << ind;
            if ((visited & bitSet) == 0) {
                visited |= bitSet;
                traverse(st + 1, visited);
                visited &= ~bitSet;
            }
        }
    }

    private Set<Integer> factors(int num) {
        int root = (int) Math.sqrt(num);
        Set<Integer> out = new HashSet<>();
        for (int i = 1; i <= root; i++) {
            if (num % i == 0) {
                out.add(i);
                out.add(num / i);
            }
        }
        out.add(num);
        return out;
    }

    public static void main(String[] args) {
        BeautifulArrangement arrangement = new BeautifulArrangement();
        System.out.println(arrangement.countArrangement(8));
    }
}
