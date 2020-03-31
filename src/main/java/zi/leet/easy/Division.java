package zi.leet.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author balamurugan
 */
public class Division {
	public int divide(int dividend, int divisor) {
		if (divisor > dividend) return 0;
		List<Integer> divisorPowers = new ArrayList<>();
		int divisorPower = divisor;
		while (divisorPower < dividend) {
			divisorPowers.add(divisorPower);
			divisorPower = multiply(divisorPower, divisor);
		}
		int quotient = 0, ind = divisorPowers.size() - 1;
		do {
			divisorPower = divisorPowers.get(ind);
			if (dividend >= divisorPower) {
				dividend -= divisorPower;
				quotient |= 1;
			}
			quotient <<= 1;
			ind--;
		} while (dividend > 0 && ind >= 0);
		return quotient >> 1;
	}

	private int multiply(int a, int b) {
		int out = 0;
		while (b > 0) {
			if ((b & 1) == 1)
				out += a;
			a <<= 1;
			b >>= 1;
		}
		return out;
	}
}
