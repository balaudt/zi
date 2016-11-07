package zi.euler;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by balaudt on 2/16/16.
 */
public class Solution {

    static int[][] cache, in;
    static int m, n, P = (int) (1e9 + 7);

    static int numberOfRoutes(int[][] a, int M, int N) {
        cache = new int[M][N];
        for (int i = 0; i < M; i++)
            Arrays.fill(cache[i], -1);
        in = a;
        m = M;
        n = N;
        cache[m-1][n-1]=1;
        return route(0, 0);
    }

    static int route(int r, int c) {
        if (cache[r][c] != -1)
            return cache[r][c];
        int ans = 0;
        if (r != m - 1 && in[r + 1][c] == 1)
            ans = route(r + 1, c);
        if (c != n - 1 && in[r][c + 1] == 1) {
            long temp = (long) ans + route(r, c + 1);
            ans = (int) (temp % P);
        }
        cache[r][c] = ans;
        return ans;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int _a_cnt = 0, _b_cnt = 0;
        int[][] _a = new int[1001][1001];
        try {
            _a_cnt = sc.nextInt();
            _b_cnt = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Here: " + e.getMessage());
        }

        for (int i = 0; i < _a_cnt; i++) {
            for (int j = 0; j < _b_cnt; j++) {
                int _a_tmp = 0;
                try {
                    _a_tmp = sc.nextInt();
                } catch (Exception e) {
                }
                _a[i][j] = _a_tmp;
            }
        }
        System.out.println(numberOfRoutes(_a, _a_cnt, _b_cnt));

    }

}
