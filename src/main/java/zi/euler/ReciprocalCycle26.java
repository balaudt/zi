package zi.euler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apfloat.Apfloat;

public class ReciprocalCycle26 {

	public static void main(String[] args) {
		int max = -1, maxNo = -1;
		for (int i = 2; i < 1000; i++) {
			Apfloat f = new Apfloat(1, 2000).divide(new Apfloat(i, 2000));
			String cycle = findCycle(f.toString(true));
//			System.out.println(i + "\t" + cycle);
			if (cycle != null && cycle.length() > max) {
				max = cycle.length();
				maxNo = i;
			}
		}
		System.out.println(maxNo);
	}

	public static String findCycle(String str) {
		// System.out.println(str);
		Pattern pattern = Pattern.compile("(.+)(\\1)+");
		Matcher matcher = pattern.matcher(str);
		if (!matcher.find()) {
			return null;
		}
		String group = null;
		group = matcher.group(1);
		while (matcher.find()) {
			String curMatch = matcher.group(1);
			// System.out.println(curMatch);
			if (curMatch.length() > group.length()) {
				group = curMatch;
			}
		}
		// System.out.println(group);
		matcher = pattern.matcher(group);
		while (matcher.find()) {
			String curMatch = matcher.group(1);
			String grpCmp = curMatch + group.substring(0, group.length() - curMatch.length());
			if (grpCmp.equals(group)) {
				// System.out.println(curMatch);
				matcher = pattern.matcher(curMatch);
				group = curMatch;
			}
		}
		return group;
	}
}
