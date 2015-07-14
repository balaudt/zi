package zi.euler;

public class DoubleBasePalindromes36 {

	public static void main(String[] args) {
		long sum = 0;
		for (int i = 0; i < 1000000; i++) {
			if (isPalindrome(Integer.toString(i))
					&& isPalindrome(Integer.toBinaryString(i))) {
				sum += i;
			}
		}
		System.out.println(sum);
	}

	static boolean isPalindrome(String x) {
		int xl = x.length();
		int xB2 = x.length() / 2;
		for (int i = 0; i < xB2; i++) {
			if (x.charAt(i) != x.charAt(xl - i - 1)) {
				return false;
			}
		}
		return true;
	}
}
