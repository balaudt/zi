package zi.leet.medium;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

//https://leetcode.com/problems/gray-code
public class GrayCode {
    public List<Integer> grayCode(int n) {
        Set<Integer> visited = new LinkedHashSet<>();
        visited.add(0);
        int sequenceLen = 1 << n, lastAdded = 0;
        while (visited.size() < sequenceLen) {
            for (int i = 0; i < n; i++) {
                int t = lastAdded ^ (1 << i);
                if (!visited.contains(t)) {
                    visited.add(t);
                    lastAdded = t;
                    break;
                }
            }
        }
        return new ArrayList<>(visited);
    }

    public static void main(String[] args) {
        GrayCode grayCode = new GrayCode();
        System.out.println(grayCode.grayCode(2));
    }
}
