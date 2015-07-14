package zi.euler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

public class XorDecryption59 {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("io/p059_cipher.txt"));
		String[] in = reader.readLine().split(",");

		// This block confirms that the three letters of the password are g,o,d
		int[] count = new int[26];
		System.out.println(in.length);
		for (int i = 0; i < in.length; i++) {
			for (int j = 0; j < 26; j++) {
				if ((Integer.parseInt(in[i]) ^ ('a' + j)) == ' ') {
					count[j]++;
				}
			}
		}
		Map<Character, Integer> helper = new HashMap<Character, Integer>();
		for (int i = 0; i < count.length; i++) {
			helper.put((char) ('a' + i), count[i]);
		}
		System.out.println(helper);

		ICombinatoricsVector<Character> originalVector = Factory.createVector(new Character[] { 'd', 'o', 'g' });
		Generator<Character> gen = Factory.createPermutationGenerator(originalVector);
		char[] decrypted = new char[in.length];
		for (ICombinatoricsVector<Character> perm : gen) {
			Character[] current = new Character[3];
			perm.getVector().toArray(current);
			int sum = 0;
			for (int i = 0; i < in.length; i++) {
				decrypted[i] = (char) (Integer.parseInt(in[i]) ^ current[i % 3]);
				sum += decrypted[i];
			}
			System.out.println(Arrays.toString(current));
			System.out.println(new String(decrypted));
			System.out.println(sum);
		}

		reader.close();
	}
}
