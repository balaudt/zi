package zi.jam.europython11;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.text.StrBuilder;

public class IrregularExpressions {

	static List<Character> vowels = Arrays.asList(new Character[] { 'a', 'e', 'i', 'o', 'u' });

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("C:/ft/7/C-large-practice.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("C:/ft/7/C-large-practice.out"));
		int t = Integer.parseInt(reader.readLine());
		Pattern searchPattern = Pattern.compile("(.+)(.+)\\1");
		for (int i = 0; i < t; i++) {
			String in = reader.readLine();
			int length = in.length();
			StrBuilder builder = new StrBuilder("Case #").append(i + 1).append(": ");
			boolean isNoSpell = true;
			for (int j = 0; j < length - 2 && isNoSpell; j++) {
				for (int k = 3; j + k <= length; k++) {
					String substring = in.substring(j, j + k);
					// System.out.println(substring);
					Matcher matcher = searchPattern.matcher(substring);
					while (matcher.find()) {
						// System.out.println(matcher.group(1) + "-" +
						// matcher.group(2) + "-" + matcher.group(1));
						if (canThisBeASpell(matcher.group(1), matcher.group(2))) {
							isNoSpell = false;
							break;
						}
					}
					if (!isNoSpell) {
						builder.append("Spell!\n");
						/*
						 * System.out.println(builder.toString() + "\t" +
						 * matcher.group(1) + "-" + matcher.group(2) + "-" +
						 * matcher.group(1));
						 */
						break;
					}

				}
			}
			if (isNoSpell) {
				builder.append("Nothing.\n");
			}
			writer.write(builder.toString());
		}
		reader.close();
		writer.close();
	}

	static boolean canThisBeASpell(String startStr, String middleStr) {
		if (hasAtleast(startStr, 2) && hasAtleast(middleStr, 1))
			return true;
		if (getCount(startStr) < 2) {
			return false;
		}
		if (startStr.length() < 3) {
			return false;
		}
		return canThisBeASpell(startStr.substring(1, startStr.length()), middleStr + startStr.charAt(0))
				|| canThisBeASpell(startStr.substring(0, startStr.length() - 1), middleStr);
	}

	static int getCount(String str) {
		char[] cs = str.toCharArray();
		int count = 0;
		for (Character c : cs) {
			if (vowels.contains(c)) {
				count++;
			}
		}
		return count;
	}

	static boolean hasAtleast(String str, int vowelCount) {
		char[] cs = str.toCharArray();
		int count = 0;
		for (Character c : cs) {
			if (vowels.contains(c)) {
				if (++count == vowelCount) {
					return true;
				}
			}
		}
		return false;
	}

	public static void main1(String[] args) {
		System.out.println(hasAtleast("t", 1));
	}
}
