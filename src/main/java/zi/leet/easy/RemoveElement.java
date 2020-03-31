package zi.leet.easy;

//https://leetcode.com/problems/remove-element
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int out = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[out++] = nums[i];
            }
        }
        return out;
    }
}
