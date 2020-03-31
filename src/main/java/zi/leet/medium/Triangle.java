package zi.leet.medium;

import java.util.List;

//https://leetcode.com/problems/triangle/
public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        List<Integer> last = triangle.get(triangle.size() - 1);
        for (int i = triangle.size() - 2; i >= 0; i--) {
            List<Integer> current = triangle.get(i);
            for (int j = 0; j < current.size(); j++) {
                current.set(j, current.get(j) + Math.min(last.get(j), last.get(j + 1)));
            }
        }
        return last.get(0);
    }
}
