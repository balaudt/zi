package zi.leet.easy;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/degree-of-an-array
public class DegreeArray {
    class Meta {
        int start, end, count;
    }

    public int findShortestSubArray(int[] nums) {
        Map<Integer, Meta> metaMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (metaMap.containsKey(nums[i])) {
                Meta meta = metaMap.get(nums[i]);
                meta.end = i;
                meta.count++;
            } else {
                Meta meta = new Meta();
                meta.start = i;
                meta.end = i;
                meta.count = 1;
                metaMap.put(nums[i], meta);
            }
        }
        int result = Integer.MAX_VALUE, maxFreq = Integer.MIN_VALUE;
        for (Meta meta : metaMap.values()) {
            if (meta.count > maxFreq) {
                maxFreq = meta.count;
                result = meta.end - meta.start + 1;
            } else if (meta.count == maxFreq) {
                int t = meta.end - meta.start + 1;
                if (t < result) {
                    result = t;
                }
            }
        }
        return result;
    }

}
