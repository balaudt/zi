package zi.leet.google;

/**
 * @author balamurugan
 */
public class XPowerN {
	public double myPow(double x, int n) {
		double ans = 1.0;
		if (n == 0) {
			return ans;
		}
		boolean isNegative = false;
		if (n < 0) {
			n *= -1;
			isNegative = true;
		}

		char[] powBinary = Integer.toBinaryString(n).toCharArray();
		double pow = x;
		for (int i = powBinary.length - 1; i >= 0; i--) {
			if (powBinary[i] == '1') {
				ans *= pow;
			}
			pow *= pow;
		}

		if (isNegative) {
			ans = 1 / ans;
		}
		return ans;
	}

	public static void main(String[] args) {
		XPowerN xPowerN = new XPowerN();
		System.out.println(xPowerN.myPow(2.0, 10));
		System.out.println(xPowerN.myPow(2.1, 3));
		System.out.println(xPowerN.myPow(2.0, -2));
	}
}
