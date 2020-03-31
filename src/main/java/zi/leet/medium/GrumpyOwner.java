package zi.leet.medium;

//https://leetcode.com/problems/grumpy-bookstore-owner
public class GrumpyOwner {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int grumpyRunningSum = 0;
        int nonGrumpyCustomers = 0;
        for (int i = 0; i < X; i++) {
            if (grumpy[i] == 1)
                grumpyRunningSum += customers[i];
            else
                nonGrumpyCustomers += customers[i];
        }
        int result = grumpyRunningSum;
        int n = customers.length;
        for (int i = X; i < n; i++) {
            grumpyRunningSum -= grumpy[i - X] == 1 ? customers[i - X] : 0;
            if (grumpy[i] == 1)
                grumpyRunningSum += customers[i];
            else
                nonGrumpyCustomers += customers[i];
            result = Math.max(grumpyRunningSum, result);
        }
        return result + nonGrumpyCustomers;
    }

    public static void main(String[] args) {
        System.out.println(new GrumpyOwner().maxSatisfied(new int[]{1, 0, 1, 2, 1, 1, 7, 5}, new int[]{0, 1, 0, 1, 0, 1, 0, 1}, 3));
    }
}
