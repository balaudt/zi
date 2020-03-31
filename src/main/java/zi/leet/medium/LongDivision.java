package zi.leet.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * @author balamurugan
 */
public class LongDivision {
	public String fractionToDecimal(int numerator, int denominator) {
		if (numerator == 0) return "0";
		boolean numeratorSign = numerator > 0;
		boolean denominatorSign = denominator > 0;
		String resultSign = ((numeratorSign && denominatorSign)
				|| (!denominatorSign && !numeratorSign)) ? "" : "-";
		long n = Math.abs((long) numerator);
		long d = Math.abs((long) denominator);

		long decimal = n / d;
		long mod = (n % d) * 10;
		if (mod == 0) return decimal + "";
		char[] fractions = new char[500];
		Map<Long, Integer> remainderIndex = new HashMap<>();
		for (int i = 0; i < 500; i++) {
			if (remainderIndex.containsKey(mod)) {
				StringBuilder b = new StringBuilder();
				b.append(resultSign).append(decimal).append('.');
				Integer stInd = remainderIndex.get(mod);
				if (stInd > 0)
					b.append(new String(fractions, 0, stInd));
				String str = new String(fractions, stInd, i - stInd);
				if (str.length() != 1 || str.charAt(0) != '0')
					b.append('(').append(str).append(')');
				return b.toString();
			}
			remainderIndex.put(mod, i);
			fractions[i] = (char) ('0' + mod / d);
			mod = (mod % d) * 10;
		}
		return resultSign + decimal + "." + new String(fractions);
	}
}
