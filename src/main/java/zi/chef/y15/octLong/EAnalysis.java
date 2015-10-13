package zi.chef.y15.octLong;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.math3.primes.Primes;

public class EAnalysis {
	static final int MAX_VAL = (int) 5e6;
	public static final int PRIMES_LAST = 3671;
	static final Integer PRIMES[] = new Integer[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73,
			79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199,
			211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349,
			353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491,
			499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647,
			653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821,
			823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983,
			991, 997, 1009, 1013, 1019, 1021, 1031, 1033, 1039, 1049, 1051, 1061, 1063, 1069, 1087, 1091, 1093, 1097, 1103, 1109,
			1117, 1123, 1129, 1151, 1153, 1163, 1171, 1181, 1187, 1193, 1201, 1213, 1217, 1223, 1229, 1231, 1237, 1249, 1259, 1277,
			1279, 1283, 1289, 1291, 1297, 1301, 1303, 1307, 1319, 1321, 1327, 1361, 1367, 1373, 1381, 1399, 1409, 1423, 1427, 1429,
			1433, 1439, 1447, 1451, 1453, 1459, 1471, 1481, 1483, 1487, 1489, 1493, 1499, 1511, 1523, 1531, 1543, 1549, 1553, 1559,
			1567, 1571, 1579, 1583, 1597, 1601, 1607, 1609, 1613, 1619, 1621, 1627, 1637, 1657, 1663, 1667, 1669, 1693, 1697, 1699,
			1709, 1721, 1723, 1733, 1741, 1747, 1753, 1759, 1777, 1783, 1787, 1789, 1801, 1811, 1823, 1831, 1847, 1861, 1867, 1871,
			1873, 1877, 1879, 1889, 1901, 1907, 1913, 1931, 1933, 1949, 1951, 1973, 1979, 1987, 1993, 1997, 1999, 2003, 2011, 2017,
			2027, 2029, 2039, 2053, 2063, 2069, 2081, 2083, 2087, 2089, 2099, 2111, 2113, 2129, 2131, 2137, 2141, 2143, 2153, 2161,
			2179, 2203, 2207, 2213, 2221, 2237, 2239, 2243, 2251, 2267, 2269, 2273, 2281, 2287, 2293, 2297, 2309, 2311, 2333, 2339,
			2341, 2347, 2351, 2357, 2371, 2377, 2381, 2383, 2389, 2393, 2399, 2411, 2417, 2423, 2437, 2441, 2447, 2459, 2467, 2473,
			2477, 2503, 2521, 2531, 2539, 2543, 2549, 2551, 2557, 2579, 2591, 2593, 2609, 2617, 2621, 2633, 2647, 2657, 2659, 2663,
			2671, 2677, 2683, 2687, 2689, 2693, 2699, 2707, 2711, 2713, 2719, 2729, 2731, 2741, 2749, 2753, 2767, 2777, 2789, 2791,
			2797, 2801, 2803, 2819, 2833, 2837, 2843, 2851, 2857, 2861, 2879, 2887, 2897, 2903, 2909, 2917, 2927, 2939, 2953, 2957,
			2963, 2969, 2971, 2999, 3001, 3011, 3019, 3023, 3037, 3041, 3049, 3061, 3067, 3079, 3083, 3089, 3109, 3119, 3121, 3137,
			3163, 3167, 3169, 3181, 3187, 3191, 3203, 3209, 3217, 3221, 3229, 3251, 3253, 3257, 3259, 3271, 3299, 3301, 3307, 3313,
			3319, 3323, 3329, 3331, 3343, 3347, 3359, 3361, 3371, 3373, 3389, 3391, 3407, 3413, 3433, 3449, 3457, 3461, 3463, 3467,
			3469, 3491, 3499, 3511, 3517, 3527, 3529, 3533, 3539, 3541, 3547, 3557, 3559, 3571, 3581, 3583, 3593, 3607, 3613, 3617,
			3623, 3631, 3637, 3643, 3659, 3671 };

	public static void main(String[] args) {
		long ct = System.currentTimeMillis();
		int[][] a = new int[][] { { 1, -2, 2 }, { 2, -1, 2 }, { 2, -2, 3 } };
		int[][] b = new int[][] { { 1, 2, 2 }, { 2, 1, 2 }, { 2, 2, 3 } };
		int[][] c = new int[][] { { -1, 2, 2 }, { -2, 1, 2 }, { -2, 2, 3 } };
		Set<Integer> allHypos = new HashSet<Integer>(524288);
		List<int[]> lastGens = new ArrayList<>();
		lastGens.add(new int[] { 3, 4, 5 });
		allHypos.add(5);
		while (true) {
			List<int[]> nextGens = new ArrayList<>(lastGens.size() * 3);
			for (int[] vec : lastGens) {
				int[] nextVec = matMul(a, vec);
				if (nextVec[2] <= MAX_VAL) {
					nextGens.add(nextVec);
					allHypos.add(nextVec[2]);
				}
				nextVec = matMul(b, vec);
				if (nextVec[2] <= MAX_VAL) {
					nextGens.add(nextVec);
					allHypos.add(nextVec[2]);
				}
				nextVec = matMul(c, vec);
				if (nextVec[2] <= MAX_VAL) {
					nextGens.add(nextVec);
					allHypos.add(nextVec[2]);
				}
			}
			if (nextGens.isEmpty())
				break;
			lastGens = nextGens;
		}
		allHypos.removeAll(Arrays.asList(5,13,17,25,29,37,41,53,61,65,73,85,89,97,101));
		ArrayList<Integer> temp = new ArrayList<>(allHypos);
		for (Integer hypo : temp) {
			int cur = hypo;
			int count = 2;
			while (cur <= MAX_VAL) {
				allHypos.add(cur);
				cur = hypo * count++;
			}
		}
		System.out.println(allHypos.size());
		System.out.println(System.currentTimeMillis() - ct);
		System.out.println("-----");
	}

	public static void failed(String[] args) {
		int[][] a = new int[][] { { 1, -2, 2 }, { 2, -1, 2 }, { 2, -2, 3 } };
		int[][] b = new int[][] { { 1, 2, 2 }, { 2, 1, 2 }, { 2, 2, 3 } };
		int[][] c = new int[][] { { -1, 2, 2 }, { -2, 1, 2 }, { -2, 2, 3 } };
		Set<Integer> aHypos = new TreeSet<>();
		Set<Integer> bHypos = new TreeSet<>();
		Set<Integer> cHypos = new TreeSet<>();
		List<int[]> aLastGens = new ArrayList<>();
		aLastGens.add(new int[] { 3, 4, 5 });
		List<int[]> bLastGens = new ArrayList<>();
		bLastGens.add(new int[] { 3, 4, 5 });
		List<int[]> cLastGens = new ArrayList<>();
		cLastGens.add(new int[] { 3, 4, 5 });
		aHypos.add(5);
		bHypos.add(5);
		cHypos.add(5);
		while (true) {
			List<int[]> aNextGens = new ArrayList<>(aLastGens.size() * 3);
			for (int[] vec : aLastGens) {
				int[] nextVec = matMul(a, vec);
				if (nextVec[2] <= MAX_VAL) {
					aNextGens.add(nextVec);
					aHypos.add(nextVec[2]);
				}
			}
			if (aNextGens.isEmpty())
				break;
			aLastGens = aNextGens;
		}
		while (true) {
			List<int[]> bNextGens = new ArrayList<>(bLastGens.size() * 3);
			for (int[] vec : bLastGens) {
				int[] nextVec = matMul(b, vec);
				if (nextVec[2] <= MAX_VAL) {
					bNextGens.add(nextVec);
					bHypos.add(nextVec[2]);
				}
			}
			if (bNextGens.isEmpty())
				break;
			bLastGens = bNextGens;
		}
		while (true) {
			List<int[]> cNextGens = new ArrayList<>(cLastGens.size() * 3);
			for (int[] vec : cLastGens) {
				int[] nextVec = matMul(c, vec);
				if (nextVec[2] <= MAX_VAL) {
					cNextGens.add(nextVec);
					cHypos.add(nextVec[2]);
				}
			}
			if (cNextGens.isEmpty())
				break;
			cLastGens = cNextGens;
		}
		System.out.println(aHypos.size() + bHypos.size() + cHypos.size());
		//		allHypos.sort(null);
		Iterator<Integer> aIt = aHypos.iterator();
		Iterator<Integer> bIt = bHypos.iterator();
		Iterator<Integer> cIt = cHypos.iterator();
		for (int i = 0; i <= 5; i++) {
			System.out.println(aIt.next());
			System.out.println(bIt.next());
			System.out.println(cIt.next());
		}
	}

	static int[] matMul(int[][] left, int[] right) {
		int[] res = new int[3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				res[i] += left[i][j] * right[j];
			}
		}
		return res;
	}

	public static void stifelOzanam(String[] args) {
		Set<Integer> masterList = new TreeSet<>();
		for (int i = 1, j = 3;; i++, j += 2) {
			int hypo = i * (j + 1) + 1;
			if (hypo > MAX_VAL)
				break;
			masterList.add(hypo);
		}
		for (int i = 1;; i++) {
			int b = 4 * i + 3;
			int a = i * (b + 1) + b;
			int hypo = a + 2;
			if (hypo > MAX_VAL)
				break;
			masterList.add(hypo);
		}
		System.out.println(masterList);
	}

	public static void base(String[] args) {
		int m, n, m2, n2;
		Collection<Integer> masterList = new HashSet<>();
		long ct = System.currentTimeMillis();
		for (n = 1;; n++) {
			ArrayList<Integer> curList = new ArrayList<>();
			n2 = n * n;
			for (m = 2;; m++) {
				m2 = m * m;
				if (m2 + n2 > MAX_VAL) {
					break;
				}
				if (Primes.isPrime(m2 + n2))
					curList.add(m2 + n2);
			}
			curList.removeAll(masterList);
			if (!masterList.addAll(curList)) {
				System.out.println(n);
				break;
			}
		}
		System.out.println(masterList.size());
		System.out.println(System.currentTimeMillis() - ct);
		Set<Integer> primeFactors = new HashSet<>();
		for (Integer hypotenuse : masterList) {
			primeFactors.addAll(Primes.primeFactors(hypotenuse));
		}
		System.out.println(primeFactors.size());
		System.out.println(System.currentTimeMillis() - ct);
	}

	public static void gen(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("C:/Users/c0chaba/Desktop/E-gen.in"));
		String line = null;
		Set<Integer> pythHypos = new HashSet<Integer>();
		while ((line = reader.readLine()) != null)
			pythHypos.add(Integer.parseInt(line));
		int count = 0;
		for (int i = 1; i < 10000; i++) {
			if (!pythHypos.contains(i)) {
				System.out.println(i);
				count++;
			}
		}
		System.out.println(count);
		reader.close();
	}

	public static void pytha(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("/Users/balaudt/Temp/pytha.txt"));
		String line = null;
		Set<Integer> primePythaHypos = new HashSet<>();
		while ((line = reader.readLine()) != null) {
			int i = Integer.parseInt(line);
			if (Primes.isPrime(i))
				primePythaHypos.add(i);
			else {
				List<Integer> factors = Primes.primeFactors(i);
				boolean flag = false;
				for (Integer factor : factors) {
					if (primePythaHypos.contains(factor)) {
						flag = true;
						break;
					}
				}
				if (!flag)
					System.out.println(i);
			}

		}
		reader.close();
	}
}
