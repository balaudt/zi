package zi.leet.easy;

//https://leetcode.com/problems/partition-array-into-three-parts-with-equal-sum
public class PartitionArray {
    public boolean canThreePartsEqualSum(int[] A) {
        int[] runningSum = new int[A.length];
        runningSum[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            runningSum[i] = runningSum[i - 1] + A[i];
        }
        int total = runningSum[A.length - 1];
        boolean canPartition = false;
        for (int i = 0; i < runningSum.length && !canPartition; i++) {
            if (runningSum[i] == total / 3) {
                for (int j = i + 1; j < runningSum.length; j++) {
                    if (runningSum[j] == 2 * total / 3) {
                        canPartition = true;
                        break;
                    }
                }
            }
        }
        return canPartition;
    }

    public static void main(String[] args) {
        PartitionArray partitionArray = new PartitionArray();
        System.out.println(partitionArray.canThreePartsEqualSum(new int[]{0, 2, 1, -6, 6, -7, 9, 1, 2, 0, 1}));
        System.out.println(partitionArray.canThreePartsEqualSum(new int[]{0, 2, 1, -6, 6, 7, 9, -1, 2, 0, 1}));
        System.out.println(partitionArray.canThreePartsEqualSum(new int[]{3, 3, 6, 5, -2, 2, 5, 1, -9, 4}));
    }
}
