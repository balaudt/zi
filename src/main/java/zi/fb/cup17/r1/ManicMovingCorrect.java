package zi.fb.cup17.r1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by balamurugan on 1/15/17.
 */
public class ManicMovingCorrect {
    static final long INF = Long.MAX_VALUE / 4;
    static long[][] best, dist;
    static int[] from, to;

    // which: 0 same family, 1 previous family
    static long calcBest(int family, int which) {
        long ret = best[family][which];
        if (ret != -1) return ret;
        ret = 0;
        int city = which == 0 ? from[family] : to[family - 1];
        if (family == from.length - 1) {
            ret = dist[city][to[family]];
        } else {
            ret = dist[city][to[family]] + dist[to[family]][from[family + 1]] + calcBest(family + 1, 0);
            ret = Math.min(ret, dist[city][from[family + 1]] + dist[from[family + 1]][to[family]] + + calcBest(family + 1, 1));
        }
        ret = Math.min(ret, INF);
//		System.err.println(family + " " + which + ": " + ret);
        return best[family][which] = ret;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("/Users/balamurugan/Downloads/maniacs.in"));
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int caze = 1; caze <= cases; caze++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();
            dist = new long[n][n];
            for (int i = 0; i < dist.length; i++) {
                Arrays.fill(dist[i], INF);
                dist[i][i] = 0;
            }
            for (int i = 0; i < m; i++) {
                int a = sc.nextInt() - 1;
                int b = sc.nextInt() - 1;
                int c = sc.nextInt();
                dist[a][b] = Math.min(dist[a][b], c);
                dist[b][a] = Math.min(dist[b][a], c);
            }
            for (int p = 0; p < n; p++) for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) {
                dist[i][j] = Math.min(dist[i][j], dist[i][p]+dist[p][j]);
            }
            System.out.println(Arrays.deepToString(dist));

            from = new int[k];
            to = new int[k];
            for (int i = 0; i < to.length; i++) {
                from[i] = sc.nextInt() - 1;
                to[i] = sc.nextInt() - 1;
            }
            best = new long[k][2];
            for (int i = 0; i < best.length; i++) {
                best[i][0] = best[i][1] = -1;
            }
            long ans = dist[0][from[0]] + calcBest(0, 0);
            System.out.println(Arrays.deepToString(best));

            System.out.println("Case #" + caze + ": " + (ans >= INF ? -1 : ans));
        }
    }
}
