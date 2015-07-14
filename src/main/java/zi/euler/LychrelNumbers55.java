package zi.euler;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class LychrelNumbers55 {
	public static void main(String[] args) {
		Set<BigInteger> lychrelNumbers = new HashSet<BigInteger>();
		Set<BigInteger> nonLychrelNumbers = new HashSet<BigInteger>();
		for (int i = 10000; i >= 1; i--) {
			int opCount = 0;
			BigInteger res = new BigInteger(i + "");
			boolean isNonLychrelNo = false;
			do {
				res = res.add(new BigInteger(StringUtils.reverse(res.toString())));
				if (isPalindrome(res.toString())) {
					isNonLychrelNo = true;
					break;
				}
				if (++opCount > 50 || lychrelNumbers.contains(res)) {
					break;
				}
			} while (true);
			if (isNonLychrelNo) {
				nonLychrelNumbers.add(new BigInteger(i + ""));
			} else {
				lychrelNumbers.add(new BigInteger(i + ""));
			}
		}
		System.out.println(lychrelNumbers.size());
//		System.out.println(lychrelNumbers);
	}

	public static boolean isPalindrome(String str) {
		int left = 0;
		int right = str.length() - 1;

		while (left < right) {
			if (str.charAt(left) != str.charAt(right))
				return false;
			left++;
			right--;
		}
		return true;
	}
}
