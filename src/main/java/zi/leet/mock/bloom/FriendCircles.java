package zi.leet.mock.bloom;

import zi.common.ds.tree.DSU;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/friend-circles/
public class FriendCircles {
	public int findCircleNum(int[][] M) {
		int n = M.length;
		DSU dsu = new DSU(n);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (M[i][j] == 1)
					dsu.union(i, j);
			}
		}
		int result = 0;
		for (int i = 0; i < n; i++) {
			if (dsu.findSet(i) == i) result++;
		}
		return result;
	}
}
