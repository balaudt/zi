package zi.chef.y15.decLong;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

public class CAnalysis {

	public static void simpleTest(String[] args) throws Exception {
		int n = 10;
		s1 = RandomStringUtils.random(n, "ab");
		s2 = RandomStringUtils.random(n, "ab");
		computeMatrix();
		HashSet<String> lcsS1S2 = backtrackAll(s1.length(), s2.length());
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(lcsS1S2);
	}

	public static void main(String[] args) {
		//		System.out.println(computeLcs(Arrays.asList(new String[] { "aabb", "abab", "baab" })));

		int k = 5, n = 10;
		String strs[] = new String[k];
		for (int i = 0; i < k; i++) {
			strs[i] = RandomStringUtils.random(n, "ab");
		}

		randomFindLcs(strs);
	}

	static void randomFindLcs(char[][] strs) {
		String[] strings = new String[strs.length];
		for (int i = 0; i < strings.length; i++) {
			strings[i] = new String(strs[i]);
		}
		randomFindLcs(strings);
	}

	static void randomFindLcs(String[] strs) {
		ArrayList<String> strList = new ArrayList<>(Arrays.asList(strs));
		int ms = -1;
		for (int i = 0; i < 10; i++) {
			System.out.println("Shuffle " + i);
			System.out.println(strList);
			HashSet<String> lcs = computeLcs(strList);
			int curMs = lcs.iterator().next().length();
			if (ms < curMs)
				ms = curMs;
			System.out.println(lcs);
			System.out.println(curMs);
			System.out.println();
			Collections.shuffle(strList);
		}

		System.out.println("On sorted");
		Collections.sort(strList);
		HashSet<String> lcs = computeLcs(strList);
		System.out.println(lcs);
		System.out.println(lcs.iterator().next().length() + "\t" + ms);
	}

	private static HashSet<String> computeLcs(List<String> strs) {
		s1 = strs.get(0);
		s2 = strs.get(1);
		HashSet<String> lastLcs = computeLcs();
		for (int i = 2; i < strs.size(); i++) {
			ArrayList<String> lastLcsList = new ArrayList<>(lastLcs);
			int maxLcs = -1;
			for (int j = 0; j < lastLcsList.size(); j++) {
				s1 = lastLcsList.get(j);
				s2 = strs.get(i);
				int lcsLen = computeMatrix();
				if (lcsLen > maxLcs)
					maxLcs = lcsLen;
			}
			HashSet<String> nextLcs = new HashSet<>();
			for (int j = 0; j < lastLcsList.size(); j++) {
				s1 = lastLcsList.get(j);
				s2 = strs.get(i);
				int lcsLen = computeMatrix();
				if (lcsLen == maxLcs) {
					nextLcs.addAll(backtrackAll(s1.length(), s2.length()));
				}
			}
			lastLcs = nextLcs;
		}
		return lastLcs;
	}

	private static HashSet<String> computeLcs() {
		computeMatrix();
		return backtrackAll(s1.length(), s2.length());
	}

	private static int[][] c;
	private static String s1;
	private static String s2;

	private static int computeMatrix() {
		c = new int[s1.length() + 1][s2.length() + 1];
		for (int i = 1; i < s1.length() + 1; i++) {
			for (int j = 1; j < s2.length() + 1; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					c[i][j] = c[i - 1][j - 1] + 1;
				} else {
					c[i][j] = Math.max(c[i][j - 1], c[i - 1][j]);
				}
			}
		}
		return c[s1.length()][s2.length()];
	}

	private static HashSet<String> backtrackAll(int i, int j) {
		if (i == 0 || j == 0) {
			return new HashSet<String>();
		} else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
			HashSet<String> lastLcses = backtrackAll(i - 1, j - 1);
			if (lastLcses.isEmpty())
				return new HashSet<>(Arrays.asList(new String(new char[] { s1.charAt(i - 1) })));
			HashSet<String> lcses = new HashSet<String>();
			for (String lastLcs : lastLcses) {
				lcses.add(lastLcs + s1.charAt(i - 1));
			}
			return lcses;
		} else {
			if (c[i][j - 1] > c[i - 1][j]) {
				return backtrackAll(i, j - 1);
			} else if (c[i][j - 1] < c[i - 1][j]) {
				return backtrackAll(i - 1, j);
			} else {
				HashSet<String> out = backtrackAll(i, j - 1);
				out.addAll(backtrackAll(i - 1, j));
				return out;
			}
		}
	}
}
