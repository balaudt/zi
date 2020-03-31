package zi.leet.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author balamurugan
 */
public class SomePerm {
	public int slidingPuzzle(int[][] board) {
		List<Integer> state = new ArrayList<>();
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++)
				state.add(board[i][j]);
		int swapCt = 0;
		Set<List<Integer>> visited = new LinkedHashSet<>();
		visited.add(state);
		List<List<Integer>> q = new LinkedList<>();
		q.add(state);
		List<Integer> target = Arrays.asList(1, 2, 3, 4, 5, 0);

		while (!q.isEmpty()) {
			List<List<Integer>> next = new LinkedList<>();
			for (List<Integer> perm : q) {
				if (perm.equals(target))
					return swapCt;

				int zeroPos = -1;
				for (int i = 0; i < perm.size(); i++) {
					if (perm.get(i) == 0) {
						zeroPos = i;
						break;
					}
				}

				for (int i = 0; i < perm.size(); i++) {
					if (i == zeroPos)
						continue;
					List<Integer> nextPerm = new ArrayList<>(perm);
					nextPerm.set(zeroPos, nextPerm.get(i));
					nextPerm.set(i, 0);
					if (!visited.contains(nextPerm)) {
						visited.add(nextPerm);
						next.add(nextPerm);
					}
				}
			}

			q = next;
			swapCt++;
		}
		return -1;
	}
}
