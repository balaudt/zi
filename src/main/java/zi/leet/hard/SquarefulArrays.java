package zi.leet.hard;

import java.util.*;

//https://leetcode.com/problems/number-of-squareful-arrays
public class SquarefulArrays {
    private Map<Integer, List<Integer>> pairs = new HashMap<>();
    private int n;
    private int[] out;
    private Map<List<Integer>, Integer> memo = new HashMap<>();

    public int numSquarefulPerms(int[] A) {
        int n = A.length;
        this.n = n;
        for (int i = 0; i < n; i++) {
            pairs.put(i, new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = A[i] + A[j];
                int sqrt = (int) Math.sqrt(sum);
                if (sqrt * sqrt == sum) {
                    pairs.get(i).add(j);
                    pairs.get(j).add(i);
                }
            }
        }
        out = new int[n];
        int result = 0;
        for (int i = 0; i < n; i++) {
            out[0] = i;
            result += backtrack(1, 1 << i);
        }
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : A) frequency.put(num, 1 + frequency.getOrDefault(num, 0));
        int[] fact = new int[12];
        fact[0] = 1;
        for (int i = 1; i < 12; i++) fact[i] = (i + 1) * fact[i - 1];
        for (Integer freq : frequency.values()) result /= fact[freq - 1];
        return result;
    }

    private int backtrack(int st, int visited) {
        if (st == n) {
            return 1;
        }
        List<Integer> key = Arrays.asList(st, visited, out[st - 1]);
        if (memo.containsKey(key))
            return memo.get(key);
        int count = 0;
        for (Integer pair : pairs.get(out[st - 1])) {
            int flag = 1 << pair;
            if ((visited & flag) == 0) {
                visited |= flag;
                out[st] = pair;
                count += backtrack(st + 1, visited);
                visited &= ~flag;
            }
        }
        memo.put(key, count);
        return count;
    }

    public static void main(String[] args) {
        SquarefulArrays arrays = new SquarefulArrays();
//        System.out.println(arrays.numSquarefulPerms(new int[]{1, 17, 8}));
        System.out.println(arrays.numSquarefulPerms(new int[]{2, 2, 2}));
    }
}
