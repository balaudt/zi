package zi.leet.google;

/**
 * @author balamurugan
 */
public class UniqueCharacter {
	public int firstUniqChar(String s) {
		int[] index = new int[26];
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			int cur = chars[i] - 'a';
			if (index[cur] == 0) {
				index[cur] = i + 1;
			} else if (index[cur] > 0) {
				index[cur] = -1;
			}
		}
		int ans = Integer.MAX_VALUE;
		for (int cur : index) {
			if (cur > 0 && cur < ans) {
				ans = cur;
			}
		}
		return ans == Integer.MAX_VALUE ? -1 : ans - 1;
	}

	public static void main(String[] args) {
		UniqueCharacter uniqueCharacter = new UniqueCharacter();
		System.out.println(uniqueCharacter.firstUniqChar("leetcode"));
		System.out.println(uniqueCharacter.firstUniqChar("loveleetcode"));
	}
}
