package zi.euler;

import java.io.BufferedReader;
import java.io.FileReader;

public class CodeTriangle42 {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("io/p042_words.txt"));
		String line;
		int count = 0;
		while ((line = reader.readLine()) != null) {
			char[] cs = line.toCharArray();
			int wordValue = 0;
			for (char c : cs) {
				wordValue += c - 'A' + 1;
			}
			if (isTriangleNumber(wordValue)) {
				count++;
			}
		}
		System.out.println(count);
		reader.close();
	}

	static boolean isTriangleNumber(int n) {
		int nx2 = n * 2;
		int a = new Double(Math.floor(Math.sqrt(nx2))).intValue();
		if (a * (a + 1) == nx2) {
			return true;
		} else {
			return false;
		}
	}
}
