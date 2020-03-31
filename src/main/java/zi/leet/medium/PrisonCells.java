package zi.leet.medium;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;

//https://leetcode.com/problems/prison-cells-after-n-days
public class PrisonCells {
    private int[] prisonAfterNDays(int[] cells, int N) {
        int x = 0, p2 = 1;
        for (int i = cells.length - 1; i >= 0; i--) {
            if (cells[i] == 1)
                x |= p2;
            p2 <<= 1;
        }
        LinkedHashSet<Integer> visited = new LinkedHashSet<>();
        while (!visited.contains(x)) {
            System.out.println(Integer.toBinaryString(x));
            visited.add(x);
            int temp = x;
            x = 0;
            p2 = 2;
            int p5 = 5;
            for (int i = cells.length - 2; i > 0; i--) {
                int t = temp & p5;
                if (t == 0 || (t & t - 1) > 0) {
                    x |= p2;
                }
                p5 <<= 1;
                p2 <<= 1;
            }
        }
        if (visited.size() == 1)
            return cells;
        N += 1;
        N %= visited.size();
        int i = 0, result = 0;
        Iterator<Integer> it = visited.iterator();
        while (i < N) {
            result = it.next();
            i++;
        }
        char[] s = String.format("%8s", Integer.toBinaryString(result)).replace(' ', '0').toCharArray();
        int[] out = new int[8];
        for (i = 0; i < 8; i++)
            out[i] = s[i] - '0';
        return out;
    }

    public static void main(String[] args) {
        PrisonCells prisonCells = new PrisonCells();
        System.out.println(Arrays.toString(prisonCells.prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 7)));
        System.out.println(Arrays.toString(prisonCells.prisonAfterNDays(new int[]{1, 0, 0, 1, 0, 0, 1, 0}, 1000000000)));
    }
}
