package zi.leet.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//https://leetcode.com/problems/combination-sum-iii
public class CombinationSumIII {
    private Set<List<Integer>> results = new HashSet<>();
    private int n, k;

    public List<List<Integer>> combinationSum3(int k, int n) {
        this.n = n;
        this.k = k;
        traverse(new HashSet<>(), 0);
        return new ArrayList<>(results);
    }

    private void traverse(Set<Integer> visited, int sum) {
        if (visited.size() == k && sum == n) {
            results.add(new ArrayList<>(visited));
            return;
        }
        for (int i = 1; i <= 9; i++) {
            if (!visited.contains(i) && sum + i <= n) {
                visited.add(i);
                traverse(visited, sum + i);
                visited.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        CombinationSumIII sum = new CombinationSumIII();
        System.out.println(sum.combinationSum3(3, 9));
    }
}
