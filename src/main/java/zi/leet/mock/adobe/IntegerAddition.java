package zi.leet.mock.adobe;

import java.util.ArrayList;
import java.util.List;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/add-strings/
public class IntegerAddition {
	public String addStrings(String num1, String num2) {
		int n1 = num1.length() - 1, n2 = num2.length() - 1, carry = 0;
		List<Character> result = new ArrayList<>();
		while (n1 >= 0 || n2 >= 0) {
			int sum = carry;
			if (n1 >= 0) sum += num1.charAt(n1--) - '0';
			if (n2 >= 0) sum += num2.charAt(n2--) - '0';
			result.add((char) ((sum % 10) + '0'));
			carry = sum / 10;
		}
		if (carry > 0) result.add((char) (carry + '0'));
		int n = result.size();
		char[] out = new char[n];
		for (int i = 0; i < out.length; i++) {
			out[i] = result.get(n - 1 - i);
		}
		return new String(out);
	}
}
