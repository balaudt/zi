package zi.leet.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author balamurugan
 */
public class Knight {
	private static final int[][] NEIS = new int[][]{{-2, -1}, {-1, -2}, {-2, 1}, {-1, 2}, {2, -1}, {1, -2}, {1, 2}, {2, 1}};

	public int minKnightMoves(int x, int y) {
		List<List<Integer>> q = new LinkedList<>();
		q.add(Arrays.asList(0, 0));
		List<Integer> target = Arrays.asList(x, y);
		int moves = 0;
		Set<List<Integer>> visited = new HashSet<>();
		visited.add(Arrays.asList(0, 0));
		while (!q.isEmpty()) {
			System.out.println(q);

			List<List<Integer>> next = new LinkedList<>();
			for (List<Integer> pos : q) {
				if (pos.equals(target))
					return moves;
				for (int[] nei : NEIS) {
					List<Integer> perm = new ArrayList<>(pos);
					perm.set(0, perm.get(0) + nei[0]);
					perm.set(1, perm.get(1) + nei[1]);
					if (!visited.contains(perm)) {
						visited.add(perm);
						next.add(perm);
					}
				}
			}

			moves++;
			q = next;
		}
		return -1;
	}
}
