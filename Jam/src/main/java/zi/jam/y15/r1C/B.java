package zi.jam.y15.r1C;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang3.text.StrBuilder;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

public class B {

	public static final String FOLDER_ROOT = "C:/ft/24/";

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(FOLDER_ROOT + "B-small-attempt0.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER_ROOT + "B-small-attempt0.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			String[] inStr = reader.readLine().split(" ");
			int k = Integer.parseInt(inStr[0]);
			Integer.parseInt(inStr[1]);
			int s = Integer.parseInt(inStr[2]);
			char[] keysC = reader.readLine().toCharArray();
			String targetStr = reader.readLine();
			Vector<Character> keys = new Vector<Character>(keysC.length);
			for (int j = 0; j < k; j++) {
				keys.add(keysC[j]);
			}
			Generator<Character> generator = Factory.createPermutationWithRepetitionGenerator(Factory.createVector(keys), s);
			char[] permChars = new char[s];
			int count = 0, ans = 0, max = Integer.MIN_VALUE;
			for (ICombinatoricsVector<Character> perm : generator) {
				List<Character> permVector = perm.getVector();
				count = 0;
				for (Character c : permVector) {
					permChars[count++] = c;
				}
				String genStr = new String(permChars);
				int curCount = getCount(genStr, targetStr);
				if (curCount > max)
					max = curCount;
				ans += curCount;
			}
			System.out.println(max);
			double expectedBanana = (double) ans / generator.getNumberOfGeneratedObjects();
			System.out.println(expectedBanana);
			StrBuilder builder = new StrBuilder("Case #").append(i + 1).append(": ").append(max - expectedBanana).append('\n');
			String builderStr = builder.toString();
			System.out.print(builderStr);
			writer.write(builderStr);
		}
		reader.close();
		writer.close();
	}

	public static void main1(String[] args) {
		System.out.println(getCount("ABABA", "AB"));
	}

	static int getCount(String str, String targetStr) {
		// System.out.println(str + "\t" + targetStr);
		int length = str.length();
		int tarLength = targetStr.length();
		int count = 0;
		for (int i = 0; i < length - tarLength + 1; i++) {
			if (str.substring(i, i + tarLength).equals(targetStr)) {
				count++;
			}
		}
		return count;
	}
}
