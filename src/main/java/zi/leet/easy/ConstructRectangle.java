package zi.leet.easy;

//https://leetcode.com/problems/construct-the-rectangle
public class ConstructRectangle {
    public int[] constructRectangle(int area) {
        int f = (int) Math.floor(Math.sqrt(area));
        while (f >= 1) {
            if (area % f == 0)
                return new int[]{Math.max(f, area / f), Math.min(f, area / f)};
        }
        return null;
    }
}
