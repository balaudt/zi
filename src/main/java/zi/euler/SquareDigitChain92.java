package zi.euler;

import java.util.HashMap;
import java.util.Map;

public class SquareDigitChain92 {

	static Map<Long, Boolean> visitedNumbers = new HashMap<Long, Boolean>();

	public static void main(String[] args) {
		int sum = 0;
		for (int i = 1; i <= 10000000; i++) {
			if (!visit((long) i)) {
				sum++;
			}
		}
		System.out.println(sum);
	}

	static boolean visit(Long number) {
		if (visitedNumbers.containsKey(number)) {
			return visitedNumbers.get(number);
		}
		char[] cs = number.toString().toCharArray();
		long newNumber = 0;
		for (char c : cs) {
			int cur = c - '0';
			newNumber += cur * cur;
		}
		if (newNumber == 1) {
			visitedNumbers.put(number, true);
			return true;
		}
		if (newNumber == 89) {
			visitedNumbers.put(number, false);
			return false;
		}
		return visit(newNumber);
	}
}
