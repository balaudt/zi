package zi.euler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class NamesScores22 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("io/p022_names.txt"));
		String[] names = reader.readLine().split("\",\"");
		names[0] = names[0].substring(1);
		names[names.length - 1] = names[names.length - 1].substring(0, names[names.length - 1].length() - 1);
		Arrays.sort(names);
		long sum = 0;
		for (int i = 0; i < names.length; i++) {
			char[] cs = names[i].toCharArray();
			int interSum = 0;
			for (int j = 0; j < cs.length; j++) {
				interSum += cs[j] - 'A' + 1;
			}
			sum += (i + 1) * interSum;
		}
		System.out.println(sum);
		reader.close();
	}
}
