package zi.leet.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/perfect-squares
public class PerfectSquare {
    List<Integer> squares;
    Map<Integer, Integer> memo;

    public int numSquares(int n) {
        squares = new ArrayList<>();
        int i = 1;
        while (i * i <= n) {
            squares.add(i * i);
            i++;
        }
        memo = new HashMap<>();
        return minSqaures(n);
    }

    private int minSqaures(int n) {
        if (squares.contains(n)) return 1;
        if (memo.containsKey(n)) return memo.get(n);
        int result = Integer.MAX_VALUE;
        for (Integer i : squares) {
            if (i > n)
                break;
            result = Math.min(result, minSqaures(n - i) + 1);
        }
        System.out.println(memo);
        memo.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        PerfectSquare perfectSquare = new PerfectSquare();
//        System.out.println(perfectSquare.numSquares(12));
//        System.out.println(perfectSquare.numSquares(13));
//        System.out.println(perfectSquare.numSquares(1));
        System.out.println(perfectSquare.numSquares(22));
    }
}
