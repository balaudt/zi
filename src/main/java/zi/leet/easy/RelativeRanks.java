package zi.leet.easy;

import java.util.Arrays;

//https://leetcode.com/problems/relative-ranks
public class RelativeRanks {
    class Athelete implements Comparable<Athelete> {
        int index;
        int score;

        private Athelete(int index, int score) {
            this.index = index;
            this.score = score;
        }

        @Override
        public int compareTo(Athelete o) {
            return o.score - score;
        }
    }

    public String[] findRelativeRanks(int[] nums) {
        String[] out = new String[nums.length];
        Athelete[] atheletes = new Athelete[nums.length];
        for (int i = 0; i < nums.length; i++)
            atheletes[i] = new Athelete(i, nums[i]);
        Arrays.sort(atheletes);
        out[atheletes[0].index] = "Gold Medal";
        out[atheletes[1].index] = "Silver Medal";
        out[atheletes[2].index] = "Bronze Medal";
        for (int i = 3; i < atheletes.length; i++) {
            out[atheletes[i].index] = (i + 1) + "";
        }
        return out;
    }

}
