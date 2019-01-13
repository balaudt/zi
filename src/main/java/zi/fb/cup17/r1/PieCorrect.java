package zi.fb.cup17.r1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by balamurugan on 1/15/17.
 */
public class PieCorrect {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("/Users/balamurugan/Downloads/pies.in"));
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int caze = 1; caze <= cases; caze++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] cost = new int[n][m];
            for (int i = 0; i < cost.length; i++) {
                for (int j = 0; j < cost[i].length; j++) {
                    cost[i][j] = sc.nextInt();
                }
                Arrays.sort(cost[i]);
            }
            int[] extraCost = new int[n];
            int[] cant = new int[n];
            for (int i = 0; i < extraCost.length; i++) {
                extraCost[i] = 1;
            }
            int ans = 0;
            for (int i = 0; i < n; i++) {
                int minJ = -1, best = Integer.MAX_VALUE;
                for (int j = 0; j <= i; j++)
                    if (cant[j] < cost[j].length) {
                        int val = cost[j][cant[j]] + extraCost[j];
                        if (val <= best) {
                            best = val;
                            minJ = j;
                        }
                    }
                ans += best;
                cant[minJ]++;
                extraCost[minJ] += 2;
            }
            System.out.println("Case #" + caze + ": " + ans);
        }
    }
}
