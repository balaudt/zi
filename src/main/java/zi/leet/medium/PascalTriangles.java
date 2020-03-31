package zi.leet.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//https://leetcode.com/problems/pascals-triangle
public class PascalTriangles {

    public List<List<Integer>> generate(int numRows) {
        if (numRows < 1) return Collections.emptyList();
        List<Integer> row = Collections.singletonList(1);
        if (numRows == 1) return Collections.singletonList(row);
        List<List<Integer>> results = new ArrayList<>();
        results.add(row);
        row = Arrays.asList(1, 1);
        results.add(row);
        if (numRows == 2) return results;
        for (int i = 3; i <= numRows; i++) {
            List<Integer> next = new ArrayList<>(row.size() + 1);
            next.add(1);
            for (int j = 0; j < row.size() - 1; j++)
                next.add(row.get(j) + row.get(j + 1));
            next.add(1);
            results.add(next);
            row = next;
        }
        return results;
    }

    public static void main(String[] args) {
        System.out.println(new PascalTriangles().generate(6));
    }
}
