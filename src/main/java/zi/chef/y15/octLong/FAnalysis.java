package zi.chef.y15.octLong;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

public class FAnalysis {

	public static void base(String[] args) {
		String t1 = rotate("154632", 4, 0);
		System.out.println(t1);
		String t2 = rotate(t1, 4, 2);
		System.out.println(t2);
		String t3 = rotate(t2, 4, 2);
		System.out.println(t3);
		String t4 = rotate(t3, 4, 1);
		System.out.println(t4);

		Set<String> visistedPerms = new TreeSet<>();
		String initalStr = "12345";
		visistedPerms.add(initalStr);
		List<String> inStrs = new ArrayList<>(visistedPerms);
		int n = initalStr.length();
		int k = 3;
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
		System.out.println(visistedPerms);
		System.out.println(visistedPerms.size());
		System.out.println((visistedPerms.containsAll(Arrays.asList(new String[] { t1, t2, t3, t4 }))));

		Set<String> allPerms = new TreeSet<>();
		Integer[] ind = new Integer[] { 1, 2, 3, 4, 5 };
		ICombinatoricsVector<Integer> vector = Factory.createVector(ind);
		Generator<Integer> generator = Factory.createPermutationGenerator(vector);
		char[] c = new char[5];
		System.out.println(c.length);
		for (ICombinatoricsVector<Integer> v : generator) {
			List<Integer> rawV = v.getVector();
			for (int i = 0; i < c.length; i++) {
				c[i] = (char) (ind[rawV.get(i) - 1] + '0');
			}
			allPerms.add(new String(c));
		}
		System.out.println(allPerms);
	}

	public static void main(String[] args) {
		System.out.println("0123456789:;<=>?".length());
		int n = 16;
		long facts[] = new long[n];
		facts[0] = 1;
		ArrayList<Character> cList = new ArrayList<>();
		cList.add('0');
		for (int i = 1; i < facts.length; i++) {
			facts[i] = facts[i - 1] * (i + 1);
			cList.add((char) ('0' + i));
		}
		Collections.shuffle(cList);
		char[] c = new char[n];
		for (int i = 0; i < c.length; i++) {
			c[i] = cList.get(i);
		}
		System.out.println(new String(c));
		long ansInd = 0;
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

	public static void baseRevisited(String[] args) {
		int facts[] = new int[10];
		facts[0] = 1;
		for (int i = 1; i < facts.length; i++) {
			facts[i] = facts[i - 1] * (i + 1);
		}
		Collection<String> allPerms = new TreeSet<>();
		int n = 5;
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
		allPerms = new ArrayList<>(allPerms);
		//		System.out.println(allPerms.size() == facts[n - 1]);
		Random random = new Random();
		int testInd = random.nextInt(facts[n - 1]);
		System.out.println(testInd);
		String testStr = ((ArrayList<String>) allPerms).get(testInd);
		System.out.println(testStr);
		c = testStr.toCharArray();
		int ansInd = 0;
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
}
