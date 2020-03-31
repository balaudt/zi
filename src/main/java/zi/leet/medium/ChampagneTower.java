package zi.leet.medium;

//https://leetcode.com/problems/champagne-tower
public class ChampagneTower {
    public static double champagneTower(int poured, int query_row, int query_glass) {
        double overflow[] = new double[]{poured - 1};
        int row = 0;
        while (row < query_row) {
            double next[] = new double[overflow.length + 1];
            for (int i = 0; i < overflow.length; i++) {
                next[i] += overflow[i] < 0 ? 0 : overflow[i] / 2;
                next[i + 1] += overflow[i] < 0 ? 0 : overflow[i] / 2;
            }
            for (int i = 0; i < next.length; i++) {
                next[i] -= 1;
            }
            row++;
            overflow = next;
        }
        double out = overflow[query_glass] + 1;
        if (out < 0)
            return 0;
        else if (out > 1)
            return 1;
        else
            return out;
    }

    public static void main(String[] args) {
//        System.out.println(champagneTower(2, 2, 0));
//        System.out.println(champagneTower(2, 1, 1));
//        System.out.println(champagneTower(2, 0, 0));
        System.out.println(champagneTower(6, 3, 1));
    }
}
