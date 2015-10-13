package chef.octlong;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.text.StrBuilder;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import com.google.common.primitives.Chars;

public class FAnalysis {

	public static void lemma(String[] args) {
		/*String t1 = rotate("154632", 4, 0);
		System.out.println(t1);
		String t2 = rotate(t1, 4, 2);
		System.out.println(t2);
		String t3 = rotate(t2, 4, 2);
		System.out.println(t3);
		String t4 = rotate(t3, 4, 1);
		System.out.println(t4);
		
		int n = 7;
		System.out.println(Collections.disjoint(visitAll("1234567", 3), visitAll("1235467", 3)));
		System.out.println(getAllPerms(n));*/

		List<String> allPerms = getAllPerms(5);
		List<String> g1 = visitAll(allPerms.get(0), 3);
		//		System.out.println(g1);
		List<String> g2 = visitAll(allPerms.get(1), 3);
		//		System.out.println(g2);
		for (String g1Str : g2) {
			System.out.println(allPerms.indexOf(g1Str));
		}
	}

	private static List<String> visitAll(String initalStr, int k) {
		Set<String> visistedPerms = new TreeSet<>();
		visistedPerms.add(initalStr);
		List<String> inStrs = new ArrayList<>(visistedPerms);
		int n = initalStr.length();
		while (true) {
			List<String> nextStrs = new ArrayList<>();
			for (String inStr : inStrs) {
				for (int i = 0; i <= n - k; i++) {
					String rotString = rotate(inStr, k, i);
					if (visistedPerms.contains(rotString))
						continue;
					visistedPerms.add(rotString);
					nextStrs.add(rotString);
				}
			}
			if (nextStrs.isEmpty())
				break;
			inStrs = nextStrs;
		}
		//		System.out.println(visistedPerms);
		//		System.out.println(visistedPerms.size());
		return new ArrayList<String>(visistedPerms);
	}

	static final int P = (int) (1e9 + 7);

	public static void basicTests(String[] args) throws Exception {
		BufferedWriter inWriter = new BufferedWriter(new FileWriter("C:/ft/F-gen3.in"));
		BufferedWriter outWriter = new BufferedWriter(new FileWriter("C:/ft/F-gen3-cor.out"));
		int n = 2;
		List<String> allPerms = getAllPerms(n);
		int nFact = allPerms.size();
		inWriter.write(nFact * 2 + "\n");
		List<String> g1 = visitAll(allPerms.get(0), 3);
		List<String> g2 = visitAll(allPerms.get(1), 3);
		char cs[];
		for (int i = 0; i < nFact; i++) {
			String perm = allPerms.get(i);
			cs = perm.toCharArray();
			inWriter.write(n + " 2\n");
			String str = Chars.join(" ", cs);
			inWriter.write(str + "\n");
			inWriter.write(str + "\n");
			outWriter.write((i + 1) + "\n");
			inWriter.write(n + " 3\n");
			inWriter.write(str + "\n");
			inWriter.write(str + "\n");
			int ind = g1.indexOf(perm);
			if (ind < 0)
				ind = g2.indexOf(perm);
			outWriter.write((ind + 1) + "\n");
		}
		inWriter.close();
		outWriter.close();
	}

	public static void main(String[] args) throws Exception {
		BufferedWriter inWriter = new BufferedWriter(new FileWriter("C:/ft/F-gen-large.in"));
		inWriter.write("10\n");
		Random random = new Random();
		int n = 100000;
		ArrayList<Integer> perm = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			perm.add(i + 1);
		}
		for (int t = 0; t < 10; t++) {
			boolean isOdd = random.nextBoolean();
			inWriter.write(n + " " + (isOdd ? 3 : 2) + "\n");
			Collections.shuffle(perm);
			String str = perm.toString().replaceAll(",", "");
			str = str.substring(1, str.length() - 2) + "\n";
			inWriter.write(str);
			Collections.shuffle(perm);
			str = perm.toString().replaceAll(",", "");
			str = str.substring(1, str.length() - 2) + "\n";
			inWriter.write(str);
		}
		inWriter.close();
	}

	public static void generateTests(String[] args) throws Exception {
		BufferedWriter inWriter = new BufferedWriter(new FileWriter("C:/ft/F-gen.in"));
		BufferedWriter outWriter = new BufferedWriter(new FileWriter("C:/ft/F-gen-cor.out"));
		inWriter.write("1000\n");
		Random random = new Random();
		for (int t = 0; t < 1000; t++) {
			int n = 4 + random.nextInt(2);
			boolean isOdd = random.nextBoolean();
			inWriter.write(n + " " + (isOdd ? 3 : 2) + "\n");
			Object[] genA = generateRandomWithIndex(n);
			inWriter.write((String) genA[0]);
			Object[] genB = generateRandomWithIndex(n);
			inWriter.write((String) genB[0]);
			/*System.out.println(ansInd);
			if (!(ansInd % 2 == 0) == (c[n - 1] > c[n - 2])) {
				System.out.println("Bullshit!");
			}*/
			long aAns = (long) genA[1];
			long bAns = (long) genB[1];
			if (!isOdd) {
				bAns++;
				bAns %= P;
				outWriter.write(bAns + "\n");
			} else {
				if ((aAns % 4 == 0 || aAns % 4 == 3) != (bAns % 4 == 0 || bAns % 4 == 3)) {
					outWriter.write("-1\n");
				} else {
					bAns /= 2;
					bAns++;
					bAns %= P;
					outWriter.write(bAns + "\n");
				}
			}
		}
		inWriter.close();
		outWriter.close();
	}

	static Object[] generateRandomWithIndex(int n) {
		long facts[] = new long[n];
		facts[0] = 1;
		for (int i = 1; i < facts.length; i++) {
			facts[i] = facts[i - 1] * (i + 1);
		}
		ArrayList<Character> cList = new ArrayList<>();
		cList.add('1');
		for (int i = 1; i < facts.length; i++) {
			cList.add((char) ('1' + i));
		}
		Collections.shuffle(cList);
		char[] c = new char[n];
		StrBuilder builder = new StrBuilder();
		for (int i = 0; i < c.length; i++) {
			c[i] = cList.get(i);
			builder.append(c[i] - '1' + 1).append(' ');
		}
		builder.deleteCharAt(builder.length() - 1).append('\n');
		long ansInd = 0;
		int smallerNos = 0;
		ArrayList<Integer> sorted = new ArrayList<>(n);
		for (int i = 0; i < n - 1; i++) {
			int ele = c[i] - '0';
			int pos = Collections.binarySearch(sorted, ele);
			pos = -pos - 1;
			sorted.add(pos, ele);
			smallerNos = ele - pos - 1;
			long t1 = smallerNos * (facts[n - i - 2]);
			ansInd += t1;
		}
		return new Object[] { builder.toString(), ansInd };
	}

	public static void correctIndBase(String[] args) {
		int facts[] = new int[10];
		facts[0] = 1;
		for (int i = 1; i < facts.length; i++) {
			facts[i] = facts[i - 1] * (i + 1);
		}
		int n = 5;
		List<String> allPerms = getAllPerms(n);
		// System.out.println(allPerms.size() == facts[n - 1]);
		Random random = new Random();
		int testInd = random.nextInt(facts[n - 1]);
		System.out.println(testInd);
		String testStr = allPerms.get(testInd);
		System.out.println(testStr);
		char[] c = testStr.toCharArray();
		int ansInd = 1;
		ArrayList<Integer> sorted = new ArrayList<>(n);
		for (int i = 0; i < n - 1; i++) {
			int ele = c[i] - '0';
			int pos = Collections.binarySearch(sorted, ele);
			pos = -pos - 1;
			sorted.add(pos, ele);
			int smallerNos = ele - pos - 1;
			ansInd += smallerNos * (facts[n - i - 2]);
		}
		System.out.println(ansInd);
	}

	static String rotate(String str, int k, int i) {
		String out = str.substring(0, i);
		char[] arr = str.substring(i, i + k).toCharArray();
		char lastChar = arr[arr.length - 1];
		for (int j = arr.length - 1; j > 0; j--) {
			arr[j] = arr[j - 1];
		}
		arr[0] = lastChar;
		out += new String(arr);
		out += str.substring(i + k);
		return out;
	}

	private static List<String> getAllPerms(int n) {
		Set<String> allPerms = new TreeSet<>();
		Integer[] ind = new Integer[n];
		for (int i = 0; i < ind.length; i++) {
			ind[i] = i + 1;
		}
		ICombinatoricsVector<Integer> vector = Factory.createVector(ind);
		Generator<Integer> generator = Factory.createPermutationGenerator(vector);
		char[] c = new char[n];
		for (ICombinatoricsVector<Integer> v : generator) {
			List<Integer> rawV = v.getVector();
			for (int i = 0; i < c.length; i++) {
				c[i] = (char) (ind[rawV.get(i) - 1] + '0');
			}
			allPerms.add(new String(c));
		}
		//		System.out.println(allPerms);
		return new ArrayList<String>(allPerms);
	}

	static List<String> getAllPermsE(int n) {
		Permutation permutation = new Permutation(n, n);
		StringBuilder builder = new StringBuilder();
		List<String> out = new ArrayList<String>();
		while (permutation.hasNext()) {
			int[] next = permutation.next();
			builder.setLength(0);
			for (int i = 0; i < next.length; i++) {
				builder.append(next[i] + 1);
			}
			out.add(builder.toString());
		}
		return out;
	}
}
