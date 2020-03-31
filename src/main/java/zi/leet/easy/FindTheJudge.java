package zi.leet.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//https://leetcode.com/problems/find-the-town-judge
public class FindTheJudge {
    public int findJudge(int N, int[][] trust) {
        boolean[] trusts = new boolean[N];
        Map<Integer, Set<Integer>> trustedMap = new HashMap<>();
        for (int[] t : trust) {
            trusts[t[0] - 1] = true;
            if (trustedMap.containsKey(t[1] - 1)) {
                trustedMap.get(t[1] - 1).add(t[0] - 1);
            } else {
                HashSet<Integer> np = new HashSet<>();
                trustedMap.put(t[1] - 1, np);
                np.add(t[0] - 1);
            }
        }
        boolean foundJudge = false;
        int judge = -1;
        for (int i = 0; i < N; i++) {
            if (!trusts[i] && trustedMap.get(i) != null && trustedMap.get(i).size() == N - 1)
                if (foundJudge)
                    return -1;
                else {
                    foundJudge = true;
                    judge = i + 1;
                }
        }
        return judge;
    }

}
