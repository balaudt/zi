package zi.jam.europython11;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.text.StrBuilder;

public class CentauriPrime {

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("C:/ft/7/A-small-practice-2.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("C:/ft/7/A-small-practice-2.out"));
		String line = null;
		List<Character> vowels = Arrays.asList(new Character[] { 'a', 'e', 'i', 'o', 'u' });
		int count = 1;
		reader.readLine();
		while ((line = reader.readLine()) != null) {
			char[] inArr = line.toLowerCase().toCharArray();
			char lastChar = inArr[inArr.length - 1];
			StrBuilder builder = new StrBuilder("Case #").append(count++).append(": ").append(line).append(" is ruled by ");
			if (lastChar == 'y') {
				builder.append("nobody.\n");
				writer.write(builder.toString());
				continue;
			}
			boolean isCons = true;
			for (Character cs : vowels) {
				if (lastChar == cs) {
					isCons = false;
					break;
				}
			}
			if (isCons) {
				builder.append("a king.\n");
			} else {
				builder.append("a queen.\n");
			}
			writer.write(builder.toString());
		}
		reader.close();
		writer.close();
	}
}
