package zi.jam.y14.r1B;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class RepeaterSmall {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("A-small-attempt1.bin"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("A-small-attempt1.out"));
		int t = Integer.parseInt(reader.readLine());
		Pattern pattern = Pattern.compile("([a-z])\\1{1,}");
		for (int i = 0; i < t; i++) {
			System.out.println(i);
			reader.readLine();
			String str1 = reader.readLine();
			String str2 = reader.readLine();
			StringBuilder builder = new StringBuilder("Case #").append(i + 1).append(": ");
			if (pattern.matcher(str1).replaceAll("$1").equals(pattern.matcher(str2).replaceAll("$1"))) {
				int count = operate(str1, str2, 0);
				builder.append(count);
			} else {
				// System.out.println("Fegla Won");
				builder.append("Fegla Won");
			}
			builder.append('\n');
			writer.write(builder.toString());
		}
		reader.close();
		writer.close();
	}

	public static int operate(String str1, String str2, int count) {
		System.out.println(count+","+str1+","+str2);
		if (count > 100) {
			return -1;
		}
		if (str1.equals(str2)) {
			return count;
		}
		StringBuilder s1 = new StringBuilder(str1);
		StringBuilder s2 = new StringBuilder(str2);
		if (s1.length() < s2.length()) {
			StringBuilder temp = s2;
			s2 = s1;
			s1 = temp;
		}
		int j = 0;
		while (s1.charAt(j) == s2.charAt(j)) {
			j++;
		}
		s2.append(s1.charAt(j));
		int appendCount = operate(s1.toString(), s2.toString(), count + 1);
		s2.deleteCharAt(j);
		s1.deleteCharAt(j);
		int delCount = operate(s1.toString(), s2.toString(), count + 1);
		if (appendCount == -1) {
			return -1;
		} else {
			if (delCount == -1) {
				return appendCount;
			} else {
				if (appendCount > delCount) {
					return delCount;
				} else {
					return appendCount;
				}
			}
		}
	}
}
