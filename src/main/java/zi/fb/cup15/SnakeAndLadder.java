package zi.fb.cup15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.psjava.ds.array.MutableArray;
import org.psjava.ds.array.MutableArrayFromVarargs;
import org.psjava.ds.tree.segmenttree.SegmentTree;
import org.psjava.goods.GoodSegmentTreeFactory;

import com.google.common.collect.ArrayListMultimap;

public class SnakeAndLadder {
	private static SegmentTree<Integer> maxTree;
	private static final int P = (int) (1e9 + 7);

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("C:/ft/fb/3.in"));
		System.setOut(new PrintStream("C:/ft/fb/C.out"));
		int t = Integer.parseInt(reader.readLine());
		String[] inStr;
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(reader.readLine());
			Pair<Integer, Integer>[] ladders = new Pair[n];
			for (int j = 0; j < n; j++) {
				inStr = reader.readLine().split(" ");
				ladders[j] = new ImmutablePair<Integer, Integer>(Integer.parseInt(inStr[0]), Integer.parseInt(inStr[1]));
			}
			Arrays.sort(ladders, (l, r) -> l.getLeft() - r.getLeft());
			//			System.out.println(Arrays.toString(ladders));
			Integer heightArr[] = new Integer[n];
			Arrays.fill(heightArr, 0);
			MutableArray<Integer> heights = MutableArrayFromVarargs.create(heightArr);
			maxTree = GoodSegmentTreeFactory.getInstance().create(heights, (a, b) -> Math.max(a, b));
			maxTree.update(0, ladders[0].getRight());
			ArrayListMultimap<Integer, Integer> heightToPos = ArrayListMultimap.<Integer, Integer> create();
			ArrayListMultimap<Integer, Long> runningSum = ArrayListMultimap.<Integer, Long> create();
			ArrayListMultimap<Integer, Long> runningSquare = ArrayListMultimap.<Integer, Long> create();
			heightToPos.put(ladders[0].getRight(), 0);
			runningSum.put(ladders[0].getRight(), 0l);
			runningSum.put(ladders[0].getRight(), (long) ladders[0].getLeft() % P);
			runningSquare.put(ladders[0].getRight(), 0l);
			runningSquare.put(ladders[0].getRight(), ((long) ladders[0].getLeft() * ladders[0].getLeft()) % P);

			long ans = 0l;
			for (int j = 1; j < n; j++) {
				Pair<Integer, Integer> currentLadder = ladders[j];
				Integer curLadHgt = currentLadder.getRight();
				Integer curLadPos = currentLadder.getLeft();

				List<Integer> laddersOfSameHeight = heightToPos.get(curLadHgt);
				List<Long> runSumLis = runningSum.get(curLadHgt);
				List<Long> runSquLis = runningSquare.get(curLadHgt);
				int size = laddersOfSameHeight.size();
				int ip = 0;
				if (size > 0) {
					int lad = getNearTallLad(j, curLadHgt);
					if (lad > 0) {
						ip = Collections.binarySearch(laddersOfSameHeight, lad);
						if (ip < 0)
							ip = -ip - 1;
						else
							ip = lad + 1;
					}
					/*for (int k = ip; k < size; k++) {
						long diff = curLadPos - ladders[laddersOfSameHeight.get(k)].getLeft();
						ans += diff * diff;
						ans %= P;
					}*/
					int m = size - ip;
					ans += runSquLis.get(size) - runSquLis.get(ip);
					ans %= P;
					ans += m * (long) curLadPos * curLadPos;
					ans %= P;
					ans -= 2 * curLadPos * (runSumLis.get(size) - runSumLis.get(ip));
					ans %= P;
					//					System.out.println(ans);
				}

				maxTree.update(j, curLadHgt);
				heightToPos.put(curLadHgt, j);
				if (size > 0) {
					Long prevSum = runSumLis.get(size);
					prevSum += curLadPos;
					prevSum %= P;
					runningSum.put(curLadHgt, prevSum);

					prevSum = runSquLis.get(size);
					prevSum += (long) curLadPos * curLadPos;
					prevSum %= P;
					runningSquare.put(curLadHgt, prevSum);
				} else {
					runningSum.put(curLadHgt, 0l);
					runningSum.put(curLadHgt, (long) curLadPos % P);
					runningSquare.put(curLadHgt, 0l);
					runningSquare.put(curLadHgt, ((long) curLadPos * curLadPos) % P);
				}
			}
			if (ans < 0)
				ans += P;
			System.out.println("Case #" + (i + 1) + ": " + ans);
		}
		reader.close();
	}

	static int getNearTallLad(int j, int height) {
		if (maxTree.query(0, j) <= height)
			return -1;
		return getNearTallLad(height, 0, j - 1);
	}

	static int getNearTallLad(int height, int l, int r) {
		if (l == r)
			return l;
		int mid = (l + r) / 2;
		Integer tallest = maxTree.query(mid + 1, r + 1);
		if (tallest > height)
			return getNearTallLad(height, mid + 1, r);
		else
			return getNearTallLad(height, l, mid);
	}
}
