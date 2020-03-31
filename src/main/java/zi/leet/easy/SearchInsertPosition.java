package zi.leet.easy;

import java.util.Arrays;

//https://leetcode.com/problems/search-insert-position
public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int ip = Arrays.binarySearch(nums, target);
        return ip >= 0 ? ip : -ip - 1;
    }

}
