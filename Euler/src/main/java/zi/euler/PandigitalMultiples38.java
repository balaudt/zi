package zi.euler;

import java.util.Arrays;

import org.apache.commons.lang3.text.StrBuilder;

public class PandigitalMultiples38 {

	public static void main(String[] args) {
		int max = -1;
		for (int n = 2;; n++) {
			int baseDigits = (int) 9 / n + 1;
			// System.out.println(n + "\t" + baseDigits);
			if (baseDigits <= 1)
				break;
			int start = (int) Math.pow(10, baseDigits - 1);
			int end = (int) Math.pow(10, baseDigits);
			// System.out.println(start + "\t" + end);
			for (int base = start; base < end; base++) {
				int mult = 2;
				StrBuilder builder = new StrBuilder().append(base);
				while (builder.toString().length() < 9) {
					builder.append(base * mult);
					mult++;
				}
				String str = builder.toString();
				if (isPandigital(str)) {
					int num = Integer.parseInt(str);
					if (num > max)
						max = num;
					// System.out.println(base + "\t" + num);
				}
			}
		}
		System.out.println(max);
	}

	static boolean isPandigital(String str) {
		if (str.length() != 9 || str.contains("0"))
			return false;
		char[] cs = str.toCharArray();
		Arrays.sort(cs);
		if (new String(cs).equals("123456789"))
			return true;
		return false;
	}
}
