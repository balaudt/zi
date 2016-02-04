package zi.fb.cup15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.tuple.MutablePair;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

public class BoomerangTournament {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("/Users/balaudt/Temp/fb/D.in"));
		System.setOut(new PrintStream("/Users/balaudt/Temp/fb/D.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(reader.readLine());
			boolean w[][] = new boolean[n][n];
			for (int j = 0; j < n; j++) {
				char[] inStr = reader.readLine().toCharArray();
				for (int k = 0; k < n; k++) {
					w[j][k] = inStr[2 * k] == '1' ? true : false;
				}
			}
			Integer temp[] = new Integer[n];
			for (int j = 0; j < temp.length; j++) {
				temp[j] = j;
			}
			Generator<Integer> generator = Factory.createPermutationGenerator(Factory.createVector(temp));
			int min[] = new int[n];
			int max[] = new int[n];
			for (int j = 0; j < n; j++) {
				min[j] = Integer.MAX_VALUE;
				max[j] = Integer.MIN_VALUE;
			}
			for (ICombinatoricsVector<Integer> permutation : generator) {
				int[] gameRes = playGame(w, permutation.getVector());
				for (int j = 0; j < n; j++) {
					if (gameRes[j] < min[j])
						min[j] = gameRes[j];
					if (gameRes[j] > max[j])
						max[j] = gameRes[j];
				}
			}
			System.out.println("Case #" + (i + 1) + ": ");
			for (int j = 0; j < n; j++) {
				System.out.println(min[j] + " " + max[j]);
			}
		}
		reader.close();
	}

	static int[] playGame(boolean w[][], List<Integer> vector) {
		ArrayDeque<Integer> queue = new ArrayDeque<>(vector);
		int n = w.length;
		List<MutablePair<Integer, Integer>> res = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			res.add(new MutablePair<Integer, Integer>(i, 0));
		}
		while (queue.size() > 1) {
			Integer p1 = queue.poll();
			Integer p2 = queue.poll();
			if (w[p1][p2]) {
				res.get(p1).right++;
				queue.offer(p1);
			} else {
				res.get(p2).right++;
				queue.offer(p2);
			}
		}
		res.sort(new Comparator<MutablePair<Integer, Integer>>() {

			@Override
			public int compare(MutablePair<Integer, Integer> o1, MutablePair<Integer, Integer> o2) {
				return o2.right - o1.right;
			}
		});
		int out[] = new int[n];
		int lastScore = res.get(0).right, lastRes = 1;
		for (int j = 0; j < n; j++) {
			MutablePair<Integer, Integer> cur = res.get(j);
			if (cur.right == lastScore) {
				out[cur.left] = lastRes;
			} else {
				lastScore = cur.right;
				lastRes = j+1;
				out[cur.left] = lastRes;
			}
		}
		return out;
	}
}
