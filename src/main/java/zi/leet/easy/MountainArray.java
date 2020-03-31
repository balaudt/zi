package zi.leet.easy;

//https://leetcode.com/problems/peak-index-in-a-mountain-array
public class MountainArray {
    public int peakIndexInMountainArray(int[] A) {
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1] && A[i] > A[i + 1])
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new MountainArray().peakIndexInMountainArray(new int[]{0, 2, 1, 0}));
    }
}
