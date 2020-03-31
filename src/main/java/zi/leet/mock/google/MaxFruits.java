package zi.leet.mock.google;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/fruit-into-baskets/
public class MaxFruits {

	public int totalFruit(int[] tree) {
		int result = Integer.MIN_VALUE, current = 1;
		int f1Last = 0, f1Type = tree[0], f2Last = -1, f2Type = -1;
		for (int i = 1; i < tree.length; i++) {
			if (tree[i] == f1Type) {
				f1Last = i;
				current++;
				continue;
			}
			if (f2Type == -1) {
				f2Type = tree[i];
				f2Last = i;
				current++;
				continue;
			}
			if (tree[i] == f2Type) {
				f2Last = i;
				current++;
				continue;
			}
			result = Math.max(current, result);
			if (f1Last < f2Last) {
				current = i - f1Last;
				f1Type = tree[i];
				f1Last = i;
			} else {
				current = i - f2Last;
				f2Type = tree[i];
				f2Last = i;
			}
		}
		result = Math.max(current, result);
		return result;
	}
}
