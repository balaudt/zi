package zi.leet.easy;


import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/jewels-and-stones
public class JewelStones {
    public int numJewelsInStones(String J, String S) {
        Set<Character> jewels = new HashSet<>();
        for (char c : J.toCharArray()) {
            jewels.add(c);
        }
        int count = 0;
        for (char c : S.toCharArray()) {
            if (jewels.contains(c)) {
                count++;
            }
        }
        return count;
    }
}
